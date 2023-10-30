package geometry;

import java.awt.Color;
import java.awt.Graphics;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

public class Donut extends Circle {
	private int innerRadius;

	public Donut() {

	}

	public Donut(Point center, int radius, int innerRadius, boolean selected) {
		/*
		 * setCenter(center);//ostalo je private this.radius=radius;//moze jer je
		 * protected selected setSelected(selected); this.innerRadius=innerRadius;
		 */
		super(center, radius, selected);
		this.innerRadius = innerRadius;
	}

	public Donut(Point center, int radius, int innerRadius) {
		setCenter(center);
		this.radius = radius;
		this.innerRadius = innerRadius;
	}

	public Donut(Point center, int radius, int innerRadius, boolean selected, Color color) {
		this(center, radius, innerRadius, selected);
		setColor(color);
	}

	public Donut(Point center, int radius, int innerRadius, boolean selected, Color color, Color innerColor) {
		this(center, radius, innerRadius, selected, color);
		setInnerColor(innerColor);
	}

	public boolean equals(Object obj) {
		if (obj instanceof Donut) {
			Donut pomocna = (Donut) obj;
			if (super.equals(pomocna) && this.innerRadius == pomocna.innerRadius) {
				return true;
			} else
				return false;
		}
		return false;
	}

	public double area() {
		return super.area() - innerRadius * innerRadius * Math.PI;
	}
	
	public void fill(Graphics g) {
		g.setColor(getInnerColor());
		super.fill(g);
		g.setColor(Color.white);
		g.fillOval(getCenter().getX() - this.innerRadius, getCenter().getY() - this.innerRadius, this.innerRadius * 2, this.innerRadius * 2);
	}

	public void draw(Graphics g) {
		super.draw(g);
		g.setColor(getColor());
		g.drawOval(getCenter().getX() - innerRadius, getCenter().getY() - innerRadius, 2 * innerRadius,
				2 * innerRadius);

		if (isSelected()) {
			g.setColor(Color.blue);
			g.drawRect(getCenter().getX() - 2, getCenter().getY() - 2, 4, 4);
			g.drawRect(getCenter().getX() - innerRadius - 2, getCenter().getY() - 2, 4, 4);
			g.drawRect(getCenter().getX() + innerRadius - 2, getCenter().getY() - 2, 4, 4);
			g.drawRect(getCenter().getX() - 2, getCenter().getY() - innerRadius - 2, 4, 4);
			g.drawRect(getCenter().getX() - 2, getCenter().getY() + innerRadius - 2, 4, 4);
			g.setColor(Color.black);
		}
	}

	@Override
	public int compareTo(Object obj) {
		if (obj instanceof Donut) {
			Donut shapeToCompare = (Donut) obj;
			return (int) (this.area() - shapeToCompare.area());
		}
		return 0;
	}

	public boolean contains(int x, int y) {
		
		return super.contains(x, y) && getCenter().distance(x, y) >= innerRadius;
	}

	public boolean contains(Point clickPoint) {
		return super.contains(clickPoint.getX(), clickPoint.getY())
				&& getCenter().distance(clickPoint.getX(), clickPoint.getY()) >= innerRadius;
	}

	public int getInnerRadius() {
		return innerRadius;
	}

	public void setInnerRadius(int innerRadius) {
		this.innerRadius = innerRadius;
	}

	public String toString() {
		return super.toString() + ", innerRadius = " + innerRadius;
	}

}
