package geometry;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Drawing extends JPanel {

	public static void main(String args[]) {
		JFrame frame = new JFrame("Drawing");
		frame.setSize(800, 600);
		Drawing drawingPanel = new Drawing();
		frame.getContentPane().add(drawingPanel);
		frame.setVisible(true);

	}

	public void paint(Graphics g) {
		/*
		 * Point p = new Point(300, 300); p.draw(g); g.setColor(Color.red); Line l = new
		 * Line(new Point(100, 200), new Point(300, 400));// ovo je // laksi nacin da se
		 * direktno kreira tacka, ne mora odvojeno l.draw(g); g.setColor(Color.black);
		 * Donut d = new Donut(new Point(350, 450), 70, 50, true); d.draw(g);
		 * g.setColor(Color.magenta);
		 * 
		 * Rectangle k1 = new Rectangle(new Point(400, 200), 40, 40); int a =
		 * k1.getHeight(); Donut d1 = new Donut(); d1.setInnerRadius((int) ((a *
		 * Math.sqrt(2)) / 2)); d1.setRadius(80); d1.setCenter(new
		 * Point((k1.getUpperLeftPoint().getX() + a / 2), (k1.getUpperLeftPoint().getY()
		 * + a / 2))); d1.draw(g); k1.draw(g);
		 */

		Point p = new Point(300, 300);

		ArrayList<Shape> oblici = new ArrayList<Shape>();
		oblici.add(p);
		System.out.println(oblici.contains(p));
		Iterator<Shape> it = oblici.iterator();
		while (it.hasNext()) {
			Shape sh = it.next();
			System.out.println(sh);
			System.out.println(sh);
		}
		it = oblici.iterator();

		// Zadatak 1
		ArrayList<Shape> shapes = new ArrayList<Shape>();
		Line l = new Line(new Point(200, 200), new Point(250, 250));
		Line l1 = new Line(new Point(520, 240), new Point(290, 280));
		Circle c = new Circle(new Point(450, 200), 80);
		Donut d = new Donut(new Point(150, 200), 80, 50, true);
		Rectangle r = new Rectangle(new Point(400, 400), 60, 40);
		shapes.add(p);
		shapes.add(l);
		shapes.add(c);
		shapes.add(d);
		shapes.add(r);
		Iterator<Shape> it1 = shapes.iterator();
		while (it1.hasNext()) {
			it1.next().moveBy(10, 0);
		}

		// Zadatak 2

		shapes.get(3).draw(g);
		shapes.get(shapes.size() - 1).draw(g);
		shapes.remove(1);
		shapes.get(1).draw(g);
		shapes.get(3).draw(g);
		shapes.add(3, l1);

		try {
			c.setRadius(-10);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}

		System.out.println("Sve je ok");

		// Zadatak 4
		Circle c1 = new Circle(new Point(500, 300), 50);
		shapes.add(c1);
		it = shapes.iterator();
		while (it.hasNext()) {
			Shape sh = it.next();
			sh.setSelected(true);
			sh.draw(g);
		}

		// Zadatak 5

		HashMap<String, Shape> hmShapes = new HashMap<String, Shape>();

	}
}
