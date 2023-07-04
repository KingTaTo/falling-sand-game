/**
 *  The simulator. This tracks the elements in a grid
 *  and coordinates that with the display.
 *  
 *  @author Dave Feinberg, K. Raven Russell, and [Your Name Here]
 */
public class Simulation {
	
	//******************************************************
	//*******  DO NOT EDIT ANYTHING IN THIS SECTION  *******
	//*******        But do read this section!       *******
	//******************************************************
	
	/**
	 *  The default number of rows the grid should have.
	 */
	public static final int INIT_ROWS = 120;
	
	/**
	 *  The default number of columns the grid should have.
	 */
	public static final int INIT_COLS = 80;

	/**
	 *  The grid that represents the location of each element.
	 */
	private final DynamicArray<DynamicArray<Element>> grid;
	
	/**
	 *  The GUI display.
	 */
	private final Display display;
	
	//******************************************************
	//* END DO-NOT-EDIT SECTION -- DO NOT ADD MORE FIELDS! *
	//******************************************************
	
	/**
	 *  Sets up the instance variables (grid and display).
	 *  
	 *  @param withDisplay whether or not the display should be created (for testing purposes)
	 */
	public Simulation(boolean withDisplay) {
		grid = new DynamicArray<DynamicArray<Element>>(INIT_ROWS);
		DynamicArray<Element> columns;

		for(int i = 0; i < INIT_ROWS; i++) {
			columns = new DynamicArray<Element>(INIT_COLS);
			for(int j = 0; j < INIT_COLS; j++)
				columns.add(new Empty());
			grid.add(columns);
		}
		display = new Display("Falling-Sand Game", INIT_ROWS, INIT_COLS);
	}

	/**
	 *  This is called when the user clicks on a location using the given tool.
	 *  
	 *  @param row the row where the action happened
	 *  @param col the column where the action happened
	 *  @param newElem the element the user has created to put there
	 */
	public void locationClicked(int row, int col, Element newElem) {
		// Put the new element in the grid at the row and column indicated.
		grid.get(row).set(col, newElem);
	}

	/**
	 *  Copies each element of grid's color into the display.
	 */
	public void updateDisplay() {
		// Update each cell of the display (see above) with
		// the correct color for that cell. The display has one
		// cell per grid element (one-to-one) and the color of
		// the cell is just the color of the element from the grid.
		
		// Remember: Display has a setColor(row, col, color) method,
		// and elements have a getColor() method.

		// Go through each cell of the grid, finding the color using getColor(),
		// then update the color using setColor()
		for (int row = 0; row < grid.size(); row++)
			for (int column = 0; column < grid.get(row).size(); column++)
				display.setColor(row, column, grid.get(row).get(column).getColor());
	}
	
	/**
	 *  Resizes the grid (if needed) to a bigger or smaller size.
	 *  
	 *  @param numRows the new number of rows the grid should have
	 *  @param numCols the new number of columns the grid should have
	 *  @return whether or not anything was changed
	 */
	public boolean resize(int numRows, int numCols) {
		// This method might be called when there is no change... careful!
		
		// Parameter values are validated by display, but you
		// should double check the numbers of rows/columns is
		// at least 1 here. Ignore the resize request if an
		// invalid value is given (don't crash the program...).
		
		// Add/remove all of _one_ column or row at a time until
		//    the correct size is achieved. For example, if you
		//    need to add 6 columns, DO NOT add six elements to
		//    row 1, then 6 elements to row 2. Instead, add one
		//    element to each row to complete the column, then
		//    start on the next column.
		// Multiple things might change! (Happens when dragging
		//    the corners of the window...). In that case,
		//    add/remove all rows before any columns
		
		// Rows are added to (and removed from) the top, while
		// columns are added to (and removed from) on the right.
		
		// Added space is just filled with empty void.
		
		// Removed rows are just removed.
		
		// Removed columns work as follows:
		//    If the removed element is as heavy (or heavier)
		//        than the element to the left, it will try
		//        to push that element to the left. If it
		//        can push it, then it takes its place.
		//        Remember: the push() method will move the
		//        element if possible, don't reinvent the wheel!
		//    If the removed element is lighter or can't
		//        be pushed, the removed element is just lost.
		
		// It would be very, very wise to make yourself some private
		// helper methods to do this part of the project.
		return false;
	}
	
	/**
	 *  Indicates the private post where you have shown off your
	 *  new element(s).
	 *  
	 *  @return the post where you describe your new element
	 */
	public static String newElementPost() {
		//[GUI:Advanced] change this to return the FULL URL of
		//the post where the grader can find your new element
		//demo, but ONLY if you completed the [GUI:Advanced] part
		//of the project.
		return null;
	}
	
	//******************************************************
	//*******  DO NOT EDIT ANYTHING BELOW THIS LINE  *******
	//*******        But do read this section!       *******
	//******************************************************

	/**
	 *  Causes one random particle to maybe do something. Called
	 *  repeatedly.
	 */
	public void step() {
		int row = (int)(Math.random()*grid.size());
		int col = (int)(Math.random()*grid.get(row).size()) ;
		
		grid.get(row).get(col).fall(grid, row, col);
	}
	
	/**
	 *  Game loop of the program (step, redraw, handlers, etc.).
	 */
	public void run() {
		while (true) {
			//step
			for (int i = 0; i < display.getSpeed(); i++) step();
			
			//redraw everything
			updateDisplay();
			display.repaint();
			
			//wait for redrawing and for mouse
			display.pause(1);
			
			//handle person actions (resize and tool usage)
			if(display.handle(this) && display.pauseMode()) {
				//for debugging
				updateDisplay();
				display.repaint();
				display.pause(5000);
			}
		}
	}
	
	/**
	 *  Convenience method for GTA testing. Do not use this in
	 *  your code... for testing purposes only.
	 *  
	 *  @return the private grid element
	 */
	public DynamicArray<DynamicArray<Element>> getGrid() {
		return grid;
	}
	
	/**
	 *  Main method that kicks off the simulator.
	 *  
	 *  @param args not used
	 */
	public static void main(String[] args) {
		(new Simulation(true)).run();
	}
}
