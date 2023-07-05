package elements;

public abstract class Liquid extends Element{
	@Override
	public void fall(DynamicArray<DynamicArray<Element>> grid, int row, int col) {
		if (row == grid.size() - 1)
			return;

		DynamicArray<Element> below = grid.get(row+1);

		// Fall if the sand is heavier than the element below
		if (below.get(col) != null && getWeight() > below.get(col).getWeight()) {
			// Displacing element below to above the sand
			swapElement(grid, new Coordinate(row, col), new Coordinate(row+1, col));
		}
		// Checking the element below and to the left/right
		if (below.get(col+1) != null && getWeight() > below.get(col+1).getWeight()) {
			swapElement(grid, new Coordinate(row, col), new Coordinate(row+1, col+1));
		}
		if (col - 1 >= 0  && below.get(col-1) != null && getWeight() > below.get(col-1).getWeight())
			swapElement(grid, new Coordinate(row, col), new Coordinate(row+1, col-1));

		// Pushing the element left/right to simulate liquid flow
		int random = (int) Math.round(Math.random());
		if (random == 1 && grid.get(row).get(col+1) != null && grid.get(row).get(col+1).getWeight() < this.getWeight())
			swapElement(grid, new Coordinate(row, col), new Coordinate(row, col+1));
		if (random == 0 && col > 0 && grid.get(row).get(col-1).getWeight() < this.getWeight()) {
			swapElement(grid, new Coordinate(row, col), new Coordinate(row, col-1));
		}
	}
}
