package geometry;

import java.awt.Color;
import java.awt.Graphics;

public class Circle extends ShapeArea {
	private Point center = new Point();
	protected int radius;

	public Circle() {

	}

	public Circle(Point center, int radius) {
		this.center = center;
		this.radius = radius;
	}

	public Circle(Point center, int radius, boolean selected) {
		this(center, radius);
		super.setSelected(selected);// ili setSelected(selected) i to je poziv
									// metode a ne konstruktora
	}

	public Circle(Point center, int radius, boolean selected, Color color) {
		this(center, radius, selected);
		setColor(color);
	}

	public Circle(Point center, int radius, boolean selected, Color color, Color innerColor) {
		this(center, radius, selected, color);
		setInnerColor(innerColor);
	}
	
	public Circle(Point center, int radius, Color color, Color innerColor) {
		this(center, radius);
		setColor(color);
		setInnerColor(innerColor);
	}

	public boolean equals(Object obj) {
		if (obj instanceof Circle) {
			Circle pomocna = (Circle) obj;
			if (this.center.equals(pomocna.center) && this.radius == pomocna.radius) {
				return true;
			} else
				return false;
		}
		return false;
	}

	public boolean contains(int x, int y) {
		return center.distance(x, y) <= radius;
	}

	public boolean contains(Point clickPoint) {
		return center.distance(clickPoint.getX(), clickPoint.getY()) <= radius;
	}

	public double area() {
		return radius * getRadius() * Math.PI;
	}

	public double circumference() {
		return 2 * radius * Math.PI;
	}

	@Override
	public void fill(Graphics g) {
		g.setColor(getInnerColor());
		g.fillOval(this.getCenter().getX() - radius + 1, this.getCenter().getY() - radius + 1, radius * 2 - 2, radius * 2 - 2);
	}

	public void draw(Graphics g) {
		g.setColor(getColor());
		g.drawOval(center.getX() - radius, center.getY() - radius, 2 * radius, 2 * radius);

		this.fill(g);

		if (isSelected()) {
			g.setColor(Color.blue);
			g.drawRect(getCenter().getX() - 2, getCenter().getY() - 2, 4, 4);
			g.drawRect(getCenter().getX() - radius - 2, getCenter().getY() - 2, 4, 4);
			g.drawRect(getCenter().getX() + radius - 2, getCenter().getY() - 2, 4, 4);
			g.drawRect(getCenter().getX() - 2, getCenter().getY() - radius - 2, 4, 4);
			g.drawRect(getCenter().getX() - 2, getCenter().getY() + radius - 2, 4, 4);
			g.setColor(Color.black);
		}
	}

	@Override
	public void moveTo(int x, int y) {
		this.center.moveTo(x, y);

	}

	@Override
	public void moveBy(int byX, int byY) {
		center.moveBy(byX, byY);
	}

	@Override
	public int compareTo(Object obj) {
		if (obj instanceof Circle) {
			Circle shapeToCompare = (Circle) obj;
			return (int) (this.area() - shapeToCompare.area());
		}
		return 0;
	}

	public Point getCenter() {
		return center;
	}

	public void setCenter(Point center) {
		this.center = center;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) throws Exception {
		this.radius = radius;
		if (radius < 0) {
			throw new Exception("Radius mora biti veci od 0!");
		}
	}

	public String toString() {
		return "Center: " + center + ", radius = " + radius;
	}

}
