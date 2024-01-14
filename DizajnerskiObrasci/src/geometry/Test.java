package geometry;

import java.awt.Color;

public class Test {

	public static void main(String[] args) {
		Point p1 = new Point(10, 10, Color.BLACK);
		Point p2 = new Point(20, 20, Color.BLACK);
		Point pc = p1.clone();
		
		System.out.println(p1); 
		System.out.println(pc);

		// Testiranje shallow copy kreiranja protitpa

		Line l1 = new Line(p1, p2, Color.BLACK);

		Line l2 = l1.clone();

		System.out.println(l1); 
		System.out.println(l2);

		l1.getStartPoint().setX(500);
		System.out.println(l1); // --> Startpoint: 500, 50
		System.out.println(l2); // --> Startpoint: 500, 50


		// Testiranje deep copy kreiranje prototipa
		Point p3 = new Point(50, 50, Color.BLACK);
		Point p4 = new Point(60, 60, Color.BLACK);

		/*LineDeep l3 = new LineDeep(p3, p4, Color.BLACK);
		LineDeep l4 = l3.clone();

		System.out.println(l3);
		System.out.println(l4);

		l3.getStartPoint().setX(5000);
		System.out.println(l3);  // --> Startpoint: 5000, 50
		System.out.println(l4);  // --> Startpoint: 50, 50*/

	}

}
