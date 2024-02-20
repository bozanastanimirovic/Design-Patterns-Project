package command;

import java.awt.Color;

import geometry.Circle;
import geometry.Donut;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Shape;
import mvc.DrawingModel;

public class TestCommand {

	public static void main(String[] args) throws Exception {
		DrawingModel model = new DrawingModel();

		Point p1 = new Point(10, 10, Color.RED);
		Point p2 = new Point(40, 20, Color.BLACK);
		Point p3 = new Point(30, 30, Color.BLACK);
		Line l1 = new Line(p1,p3, Color.BLUE);
		Line l2 = new Line(p2, p1, Color.BLACK);
		Circle c1 = new Circle(p2, 30, Color.BLACK, Color.WHITE);
		Circle c2 = new Circle(p1, 50, Color.BLACK, Color.WHITE);
		Rectangle r1 = new Rectangle(p1, 90, 90, Color.BLACK, Color.WHITE);
		Rectangle r2 = new Rectangle(p3, 100, 100, Color.red, Color.blue);
		Donut d1 = new Donut(p1, 40, 40, Color.BLACK, Color.WHITE);
		Donut d2 = new Donut(p3, 20, 20, Color.RED, Color.WHITE);
		
		Shape s1 = p1;
		Shape s3 = new Circle(p2, 30, Color.BLACK, Color.WHITE);
		
		AddShapeCmd addShapeCmd = new AddShapeCmd(s1, model);
		
		addShapeCmd.execute();
		System.out.println(model.getShapes().size());
		
		addShapeCmd.unexecute();
		System.out.println(model.getShapes().size());
		System.out.println(s3);
		
		RemoveShapeCmd removeShapeCmd = new RemoveShapeCmd(s1, model);
		
		removeShapeCmd.execute();
		System.out.println(model.getShapes().size());
		
		removeShapeCmd.unexecute();
		System.out.println(model.getShapes().size());
		System.out.println(s3);
		
		UpdatePointCmd updatePointCmd = new UpdatePointCmd(p1,p2);
		
		System.out.println(p1);
		updatePointCmd.execute();
		System.out.println(p1);
		
		UpdateLineCmd updateLineCmd = new UpdateLineCmd(l1,l2);
		System.out.println(l1);
		updateLineCmd.execute();
		System.out.println(l1);
		updateLineCmd.unexecute();
		System.out.println(l1);
		
		UpdateRectangleCmd uR = new UpdateRectangleCmd(r1,r2);
		
		System.out.println(r1);
		uR.execute();
		System.out.println(r1);
		uR.unexecute();
		System.out.println(r1);
		
		UpdateCircleCmd uC = new UpdateCircleCmd(c1,c2);
		
		System.out.println(c1);
		uC.execute();
		System.out.println(c1);
		uC.unexecute();
		System.out.println(c1);
		
		UpdateDonutCmd uD = new UpdateDonutCmd(d1,d2);
		
		System.out.println(d1);
		uD.execute();
		System.out.println(d1);
		uD.unexecute();
		System.out.println(d1);
	}

}
