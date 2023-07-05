package elements;

import elements.Element;

import java.awt.*;

public class BlackHole extends Element {

	@Override
	public Color getColor() {
		return null;
	}

	@Override
	public int getWeight() {
		return 0;
	}

	@Override
	public boolean push(DynamicArray<DynamicArray<Element>> grid, int row, int col) {
		return false;
	}



}
