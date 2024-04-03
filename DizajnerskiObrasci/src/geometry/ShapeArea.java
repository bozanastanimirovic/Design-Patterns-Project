package geometry;

import java.awt.Color;
import java.awt.Graphics;

public abstract class ShapeArea extends Shape {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Color innerColor;
	
	public abstract double area();
	public abstract void fill(Graphics g);

	public Color getInnerColor() {
		return innerColor;
	}

	public void setInnerColor(Color innerColor) {
		this.innerColor = innerColor;
	}
	
}
