package geometry;

import java.awt.Color;
import java.awt.Graphics;

public class Point extends Shape {

	// public int x;
	// public int y;
	// public boolean selected;

	private int x;
	private int y;

	// konstruktori
	// posle propertija ali pre svih metoda

	public Point() {

	}

	public Point(int x, int y) {
		this.x = x;
		setY(y);
	}

	public Point(int x, int y, boolean selected) {
		/*
		 * this.x = x; setY(10);
		 */
		// this uvek prva naredba
		this(x, y);
		setSelected(selected);
	}

	public Point(int x, int y, boolean selected, Color color) {
		this(x, y, selected);
		setColor(color);
	}

	// konstruktori mogu da se zovu isto ali da imaju razlicite parametre, ako
	// imaju
	// iste parametre ne mogu da se zovu isto

	// overloading...

	//

	public Point(int x, int y, Color color) {
		this(x, y);
		setColor(color);
	}

	public boolean equals(Object obj) {
		if (obj instanceof Point) {
			Point pomocna = (Point) obj;
			if (this.x == pomocna.x && this.y == pomocna.y) {
				return true;
			}
		}
		return false;
	}

	public boolean contains(int x, int y) {
		return this.distance(x, y) <= 2;
	}

	public boolean contains(Point clickPoint) {
		return this.distance(clickPoint.x, clickPoint.getY()) <= 2;
	}

	// metoda instance-nad objektom klase point
	public double distance(int xPoint2, int yPoint2) {
		double dx = this.x - xPoint2;
		double dy = this.y - yPoint2;
		double d = Math.sqrt(dx * dx + dy * dy);
		return d;
	}

	public void draw(Graphics g) {
		g.setColor(getColor());
		g.drawLine(x - 2, y, x + 2, y);
		g.drawLine(x, y - 2, x, y + 2);

		if (isSelected()) {
			g.setColor(Color.BLUE);
			g.drawRect(x - 2, y - 2, 4, 4);
			g.setColor(Color.black);
		}
	}

	@Override
	public void moveTo(int x, int y) {
		this.x = x;
		setY(y);

	}

	@Override
	public void moveBy(int byX, int byY) {
		x += byX;
		setY(y + byY);

	}

	@Override
	public int compareTo(Object obj) {
		if (obj instanceof Point) {
			Point shapeToCompare = (Point) obj;
			return (int) (this.distance(0, 0) - shapeToCompare.distance(0, 0));
		}
		return 0;
	}
	
	public Point clone() {
		Point point = new Point();

		point.setX(this.getX());
		point.setY(this.getY());
		
		point.setColor(this.getColor());

		return point;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getX() {
		return x;
	}

	// redefinisana metoda
	public String toString() {
		// nije pravilno
		// return x;
		// return String.valueOf(x); - jeste tacno ali ne zelimo tako da
		// zapisemo tacku
		return "(" + x + "," + y + ")";

	}
	/*
	 * nije redefinisana, nego neka nova public String toString() { return "x"; }
	 */

}
