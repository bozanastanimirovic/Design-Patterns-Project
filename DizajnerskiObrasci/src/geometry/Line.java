package geometry;

import java.awt.Color;
import java.awt.Graphics;

public class Line extends Shape {
	private Point startPoint = new Point();
	private Point endPoint = new Point();
	

	public Line() {

	}

	public Line(Point startPoint, Point endPoint) {
		this.startPoint = startPoint;
		setEndPoint(endPoint);
	}

	public Line(Point startPoint, Point endPoint, boolean selected) {
		// prvi nacin
		// this(startPoint, endPoint);
		// setSelected(selected);
		// drugi nacin
		super(selected);
		this.startPoint = startPoint;
		this.endPoint = endPoint;
	}
	
	public Line(Point startPoint, Point endPoint, boolean selected, Color color) {
		this(startPoint, endPoint, selected);
		setColor(color);
	}
	
	public Line(Point startPoint, Point endPoint, Color color) {
		this(startPoint, endPoint);
		setColor(color);
	}

	public boolean equals(Object obj) {
		if (obj instanceof Line) {
			Line pomocna = (Line) obj;
			if (this.startPoint.equals(pomocna.startPoint) && this.endPoint.equals(pomocna.endPoint)) {
				return true;
			} else
				return false;
		}
		return false;
	}

	public boolean contains(int x, int y) {
		return this.startPoint.distance(x, y) + this.endPoint.distance(x, y) - length() <= 2;
	}

	public boolean contains(Point clickPoint) {
		return this.startPoint.distance(clickPoint.getX(), clickPoint.getY())
				+ this.endPoint.distance(clickPoint.getX(), clickPoint.getY()) - length() <= 2;

	}

	public double length() {
		return this.startPoint.distance(endPoint.getX(), getEndPoint().getY());
	}

	public void draw(Graphics g) {
		g.setColor(getColor());
		g.drawLine(startPoint.getX(), startPoint.getY(), endPoint.getX(), endPoint.getY());

		if (isSelected()) {
			g.setColor(Color.blue);
			g.drawRect(getStartPoint().getX() - 2, getStartPoint().getY() - 2, 4, 4);
			g.drawRect(getEndPoint().getX() - 2, getEndPoint().getY() - 2, 4, 4);
			g.setColor(Color.black);
		}
	}
	
	public Line clone() {
		Line line = new Line();

		line.getStartPoint().setX(this.getStartPoint().getX());
		line.getStartPoint().setY(this.getStartPoint().getY());

		line.getEndPoint().setX(this.getEndPoint().getX());
		line.getEndPoint().setY(this.getEndPoint().getY());
		line.setColor(this.getColor());

		return line;
	}

	@Override
	public void moveTo(int x, int y) {
		this.startPoint.moveTo(x, y);
		this.endPoint.moveTo(x, y);

	}

	@Override
	public void moveBy(int byX, int byY) {
		this.startPoint.moveBy(byX, byY);
		this.endPoint.moveBy(byX, byY);

	}

	@Override
	public int compareTo(Object obj) {
		if (obj instanceof Line) {
			Line shapeToCompare = (Line) obj;
			return (int) (this.length() - shapeToCompare.length());
		}
		return 0;
	}

	public Point getStartPoint() {
		return startPoint;
	}

	public void setStartPoint(Point startPoint) {
		this.startPoint = startPoint;
	}

	public Point getEndPoint() {
		return endPoint;
	}

	public void setEndPoint(Point endPoint) {
		this.endPoint = endPoint;
	}

	public String toString() {
		return "Line: " + startPoint + "--> " + endPoint + ", Border Color: " + 
				getColor();
	}

}
