package adapter;

import java.awt.Color;
import java.awt.Graphics;

import geometry.Point;
import geometry.Rectangle;
import hexagon.Hexagon;
import geometry.Shape;

public class HexagonAdapter extends Shape {

	private Hexagon hexagon = new Hexagon(0, 0, 0);

	public HexagonAdapter(Hexagon hexagon) {
		this.hexagon = hexagon;
	}

	public HexagonAdapter(Point point, int r, boolean selected, Color background, Color background2) {
		this.setX(point.getX());
		this.setY(point.getY());
		this.setR(r);
		this.setColor(background);
		this.setInnerColor(background2);
		/*
		 * System.out.println(this.hexagon.getX());
		 * System.out.println(this.hexagon.getY());
		 * System.out.println(this.hexagon.getR());
		 * System.out.println(this.hexagon.getAreaColor());
		 * System.out.println(this.hexagon.getBorderColor());
		 */
	}

	public HexagonAdapter() {
	}

	@Override
	public boolean contains(int x, int y) {
		if (this.hexagon.doesContain(x, y)) {
			return true;
		} else {
			return false;
		}

	}

	@Override
	public void draw(Graphics g) {
		this.hexagon.paint(g);

		if (isSelected()) {
			g.setColor(Color.BLUE);
			g.drawRect(getX() - 2, getY() - 2, 4, 4);
            g.drawRect(getX() - getR() - 2, getY() - 2, 4, 4);
            g.drawRect(getX() + getR() - 2, getY() - 2, 4, 4);
            g.drawRect(getX() - getR() / 2 - 2, getY() - (int) (getR() / 2 * Math.sqrt(3)) - 2, 4, 4);
            g.drawRect(getX() + getR() / 2 - 2, getY() - (int) (getR() / 2 * Math.sqrt(3)) - 2, 4, 4);
            g.drawRect(getX() - getR() / 2 - 2, getY() + (int) (getR() / 2 * Math.sqrt(3)) - 2, 4, 4);
            g.drawRect(getX() + getR() / 2 - 2, getY() + (int) (getR() / 2 * Math.sqrt(3)) - 2, 4, 4);
            g.setColor(Color.BLACK);
		}
	}

	public Hexagon getHexagon() {
		return hexagon;
	}

	public void setHexagon(Hexagon hexagon) {
		this.hexagon = hexagon;
	}

	public Color getColor() {
		return hexagon.getBorderColor();
	}

	public void setColor(Color color) {
		this.hexagon.setBorderColor(color);
	}

	public Color getInnerColor() {
		return hexagon.getAreaColor();
	}

	public void setInnerColor(Color color) {
		this.hexagon.setAreaColor(color);
	}

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Shape clone() {
		HexagonAdapter hexagon = new HexagonAdapter();

		hexagon.setX(this.hexagon.getX());
		hexagon.setY(this.hexagon.getY());

		hexagon.setR(this.hexagon.getR());

		hexagon.setColor(this.getColor());
		hexagon.setInnerColor(this.getInnerColor());

		return hexagon;
	}

	public void setR(int r) {
		this.hexagon.setR(r);
	}

	public void setY(int y) {
		this.hexagon.setY(y);
	}

	public void setX(int x) {
		this.hexagon.setX(x);
	}
	
	public int getR() {
		return hexagon.getR();
	}

	public int getX() {
		return hexagon.getX();
	}

	public int getY() {
		return hexagon.getY();
	}

	@Override
	public void moveTo(int x, int y) {
	}

	@Override
	public void moveBy(int byX, int byY) {
	}

}
