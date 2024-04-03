package geometry;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

public abstract class Shape implements Moveable, Comparable<Object>, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean selected;
	private Color color;

	public Shape() {
	}

	public Shape(boolean selected) {
		this.selected = selected;
	}

	public abstract boolean contains(int x, int y);// nije implementirana, u ostalim klasama je implementiramo
	// redefinisanje-vec postoji implementacija samo je prilagodimo

	public abstract void draw(Graphics g);
	
	public abstract Shape clone();
	

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

}
