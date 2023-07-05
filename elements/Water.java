package elements;

import java.awt.*;

public class Water extends Liquid {

	@Override
	public Color getColor() {
		return Color.BLUE;
	}

	@Override
	public int getWeight() {
		return 3;
	}

	@Override
	public boolean push(DynamicArray<DynamicArray<Element>> grid, int row, int col) {
		return false;
	}
}
