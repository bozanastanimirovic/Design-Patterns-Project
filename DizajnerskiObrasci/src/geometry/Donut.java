package geometry;

import java.awt.Color;
import java.awt.Graphics;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.io.Serializable;

public class Donut extends Circle implements Serializable {
	private static final long serialVersionUID = 1L;

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

	public Donut(Point center, int radius, int innerRadius, Color color, Color innerColor) {
		this(center, radius, innerRadius);
		setColor(color);
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
		Graphics2D g2D = (Graphics2D) g.create();

		g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2D.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

		Shape outerCircle = new Ellipse2D.Double(getCenter().getX() - getRadius(), getCenter().getY() - getRadius(),
				2 * getRadius(), 2 * getRadius());

		Shape innerCircle = new Ellipse2D.Double(getCenter().getX() - innerRadius, getCenter().getY() - innerRadius,
				2 * innerRadius, 2 * innerRadius);

		Area donutArea = new Area(outerCircle);

		donutArea.subtract(new Area(innerCircle));

		g2D.setColor(getInnerColor());

		g2D.fill(donutArea);

		g2D.dispose();

		g2D.setColor(getInnerColor());
		super.fill(g2D);
		g2D.setColor(Color.white);
		g2D.fillOval(getCenter().getX() - this.innerRadius, getCenter().getY() - this.innerRadius, this.innerRadius * 2,
				this.innerRadius * 2);

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

	public Donut clone() {
		Donut donut = new Donut();

		donut.getCenter().setX(this.getCenter().getX());
		donut.getCenter().setY(this.getCenter().getY());

		try {
			donut.setRadius(this.getRadius());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		donut.setInnerRadius(this.getInnerRadius());

		donut.setColor(this.getColor());
		donut.setInnerColor(this.getInnerColor());

		return donut;
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
		return "Donut: Center:" + super.getCenter().getX() + "," + super.getCenter().getY() + ", radius=" + radius
				+ ", innerRadius=" + innerRadius + ", Border Color:" + getColor() + ", Inner color:" + getInnerColor();
	}

}
