package adapter;

import java.awt.Color;
import java.awt.Graphics;

import geometry.Point;
import hexagon.Hexagon;
import geometry.Shape;

public class HexagonAdapter extends Shape{
	
	private Hexagon hexagon;

	public HexagonAdapter(Hexagon hexagon) {
		this.hexagon = hexagon;
	}

	public HexagonAdapter(Point point, int r, boolean selected, Color background, Color background2) {
		this.hexagon = new Hexagon(point.getX(), point.getY(), r);
		this.hexagon.setAreaColor(background2);
		this.hexagon.setBorderColor(background);
	}

	@Override
	public boolean contains(int x, int y) {
		if (hexagon.doesContain(x, y)) {
			return true;
		} else {
			return false;
		}
		
	}

	@Override
	public void draw(Graphics g) {
		hexagon.paint(g);
	}

	public Hexagon getHexagon() {
		return hexagon;
	}

	public void setHexagon(Hexagon hexagon) {
		this.hexagon = hexagon;
	}

	@Override
	public void moveTo(int x, int y) {
		
	}

	@Override
	public void moveBy(int byX, int byY) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Shape clone() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	

	
}
