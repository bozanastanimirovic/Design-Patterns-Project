package geometry;

import java.awt.Color;
import java.awt.Graphics;

public class Rectangle extends ShapeArea {
	private Point upperLeftPoint;
	private int width;
	private int height;

	public Rectangle() {

	}

	public Rectangle(Point upperLeftPoint, int height, int width) {
		this.upperLeftPoint = upperLeftPoint;
		setWidth(width);// moze ovako ili ->
		this.height = height;
	}

	public Rectangle(Point upperLeftPoint, int height, int width, boolean selected) {
		this(upperLeftPoint, height, width);
		setSelected(selected);
	}

	public Rectangle(Point upperLeftPoint, int height, int width, boolean selected, Color color) {
		this(upperLeftPoint, height, width, selected);
		setColor(color);
	}

	public Rectangle(Point upperLeftPoint, int height, int width, boolean selected, Color color, Color innerColor) {
		this(upperLeftPoint, height, width, selected, color);
		setInnerColor(innerColor);
	}

	public boolean equals(Object obj) {
		if (obj instanceof Rectangle) {
			Rectangle pomocna = (Rectangle) obj;
			if (this.upperLeftPoint.equals(pomocna.upperLeftPoint) && this.width == pomocna.width
					&& this.height == pomocna.height)
				return true;
			else
				return false;

		}
		return false;

	}

	public boolean contains(int x, int y) {
		return x >= upperLeftPoint.getX() && x <= this.upperLeftPoint.getX() + width && y >= upperLeftPoint.getY()
				&& y <= getUpperLeftPoint().getY() + height;
	}

	public boolean contains(Point clickPoint) {
		return clickPoint.getX() >= upperLeftPoint.getX() && clickPoint.getX() <= this.upperLeftPoint.getX() + width
				&& clickPoint.getY() >= upperLeftPoint.getY()
				&& clickPoint.getY() <= getUpperLeftPoint().getY() + height;
	}

	public double area() {
		return width * height;
	}

	public int circumference() {
		return 2 * (width + height);
	}

	public void draw(Graphics g) {
		g.setColor(getColor());
		g.drawRect(upperLeftPoint.getX(), upperLeftPoint.getY(), width, height);

		this.fill(g);

		if (isSelected()) {
			g.setColor(Color.BLUE);
			g.drawRect(getUpperLeftPoint().getX() - 2, getUpperLeftPoint().getY() - 2, 4, 4);
			g.drawRect(getUpperLeftPoint().getX() + width - 2, getUpperLeftPoint().getY() - 2, 4, 4);
			g.drawRect(getUpperLeftPoint().getX() - 2, getUpperLeftPoint().getY() + height - 2, 4, 4);
			g.drawRect(getUpperLeftPoint().getX() + width - 2, getUpperLeftPoint().getY() + height - 2, 4, 4);
			g.setColor(Color.black);
		}
	}

	@Override
	public void moveTo(int x, int y) {
		this.upperLeftPoint.moveTo(x, y);
	}

	@Override
	public void moveBy(int byX, int byY) {
		this.upperLeftPoint.moveBy(byX, byY);
	}

	@Override
	public int compareTo(Object obj) {
		if (obj instanceof Rectangle) {
			Rectangle shapeToCompare = (Rectangle) obj;
			return (int) (this.area() - shapeToCompare.area());
		}
		return 0;
	}

	@Override
	public void fill(Graphics g) {
		g.setColor(getInnerColor());
		g.fillRect(this.upperLeftPoint.getX() + 1, this.getUpperLeftPoint().getY() + 1, width - 1, height - 1);
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setUpperLeftPoint(Point upperLeftPoint) {
		this.upperLeftPoint = upperLeftPoint;
	}

	public Point getUpperLeftPoint() {
		return upperLeftPoint;
	}

	public String toString() {
		return "Upper left point: " + upperLeftPoint + ", width= " + width + ", height=" + height;
	}

}
