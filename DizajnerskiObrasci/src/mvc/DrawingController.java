package mvc;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

import javax.swing.JOptionPane;

import adapter.HexagonAdapter;
import command.AddShapeCmd;
import command.RemoveShapeCmd;
import command.UpdateCircleCmd;
import command.UpdateDonutCmd;
import command.UpdateHexagonCmd;
import command.UpdateLineCmd;
import command.UpdatePointCmd;
import command.UpdateRectangleCmd;
import drawing.DlgCircle;
import drawing.DlgDonut;
import drawing.DlgHexagon;
import drawing.DlgLine;
import drawing.DlgPoint;
import drawing.DlgRectangle;
import geometry.Circle;
import geometry.Donut;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Shape;

public class DrawingController {

	private Point startPoint;
	private Shape selected;
	private Point upperLeftPoint;
	private Point center;

	private Stack<Shape> shapesUndo = new Stack<Shape>();
	private Stack<Shape> shapesRedo = new Stack<Shape>();

	/*
	 * private Stack<Shape> newShape = new Stack<Shape>(); private Stack<Shape>
	 * modifiedShape = new Stack<Shape>(); private Stack<Shape> oldShape = new
	 * Stack<Shape>();
	 * 
	 * private Stack<Shape> newShapeRedo = new Stack<Shape>(); private Stack<Shape>
	 * modifiedShapeRedo = new Stack<Shape>(); private Stack<Shape> oldShapeRedo =
	 * new Stack<Shape>();
	 */

	private Stack<String> modifyShape = new Stack<String>();
	private Stack<String> modifyShapeRedo = new Stack<String>();

	private Stack<String> undoOp = new Stack<String>();
	private Stack<String> redoOp = new Stack<String>();

	AddShapeCmd addShapeCmd;
	RemoveShapeCmd removeShapeCmd;
	UpdatePointCmd updatePointCmd;
	UpdateLineCmd updateLineCmd;
	UpdateRectangleCmd updateRectangleCmd;
	UpdateCircleCmd updateCircleCmd;
	UpdateDonutCmd updateDonutCmd;
	UpdateHexagonCmd updateHexagonCmd;

	List<Shape> selectedShapes = new ArrayList<>();

	DrawingModel model;
	DrawingFrame frame;

	public DrawingController(DrawingModel model, DrawingFrame frame) {
		this.model = model;
		this.frame = frame;
	}

	public void mouseClicked(MouseEvent e) {
		Shape newShape = null;
		if (frame.getBtnSelect().isSelected()) {
			// selected = null;
			Iterator<Shape> it = model.getShapes().iterator();

			while (it.hasNext()) {
				/*
				 * Shape shape = it.next(); model.add(shape); if (shape.isSelected()) { if
				 * (shape.contains(e.getX(), e.getY())) { shape.setSelected(false); break; } }
				 * shape.setSelected(false); if (shape.contains(e.getX(), e.getY())) {
				 * shape.setSelected(true); selected = shape; } for (int i = 0; i <
				 * model.getShapes().size(); i++) { for (int n = i + 1; n <
				 * model.getShapes().size(); n++) { if
				 * (model.getShapes().get(i).contains(e.getX(), e.getY()) &&
				 * model.getShapes().get(n).contains(e.getX(), e.getY())) {
				 * shape.setSelected(false); model.getShapes().get(n).setSelected(true);
				 * model.getShapes().get(i).setSelected(false); } } }
				 * 
				 * } if (selected != null) { selected.setSelected(true); }
				 */

				/*
				 * Shape shape = it.next(); if (shape.contains(e.getX(), e.getY())) { if
				 * (!selectedShapes.contains(shape)) { selectedShapes.add(shape); }
				 * shape.setSelected(true); } else { shape.setSelected(false); }
				 * 
				 * for (int i = 0; i < selectedShapes.size(); i++) {
				 * frame.getView().setSelected(shape); }
				 */

				Shape shape = it.next();
				if (shape.contains(e.getX(), e.getY())) {
					shape.setSelected(true);
					selected = shape;
					frame.getView().setSelected(shape);
				} else {
					shape.setSelected(false);
				}

			}

			frame.repaint();

		} else if (frame.getTglBtnPoint().isSelected()) {
			DlgPoint dlgPoint = new DlgPoint();
			dlgPoint.getTxtXCoordinate().setText(String.valueOf(e.getX()));
			dlgPoint.getTxtXCoordinate().setEditable(false);
			dlgPoint.getTxtYCoordinate().setText(String.valueOf(e.getY()));
			dlgPoint.getTxtYCoordinate().setEditable(false);
			dlgPoint.setBackground(frame.getBorderColor());
			dlgPoint.setModal(true);
			dlgPoint.setVisible(true);
			newShape = new Point(e.getX(), e.getY(), false, dlgPoint.getBtnColor().getBackground());
			startPoint = null;
		} else if (frame.getTglBtnLine().isSelected()) {

			if (startPoint == null) {
				startPoint = new Point(e.getX(), e.getY());
			} else {
				DlgLine dlgLine = new DlgLine();
				dlgLine.getTxtStartX().setText(String.valueOf(e.getX()));
				dlgLine.getTxtStartX().setEditable(false);
				dlgLine.getTxtStartY().setText(String.valueOf(e.getY()));
				dlgLine.getTxtStartY().setEditable(false);
				dlgLine.getTxtEndX().setText(String.valueOf(e.getX()));
				dlgLine.getTxtEndX().setEditable(false);
				dlgLine.getTxtEndY().setText(String.valueOf(e.getY()));
				dlgLine.getTxtEndY().setEditable(false);
				dlgLine.setModal(true);
				dlgLine.setVisible(true);
				newShape = new Line(startPoint, new Point(e.getX(), e.getY()), false,
						dlgLine.getBtnColor().getBackground());
				startPoint = null;
			}
		} else if (frame.getTglBtnRectangle().isSelected()) {
			DlgRectangle dlgRectangle = new DlgRectangle();
			upperLeftPoint = new Point(e.getX(), e.getY());

			dlgRectangle.setRectangle(new Rectangle(upperLeftPoint, -1, -1, false,
					dlgRectangle.getBtnBorderColor().getBackground(), dlgRectangle.getBtnInnerColor().getBackground()));

			dlgRectangle.getTxtXCoordinate().setText(String.valueOf(upperLeftPoint.getX()));
			dlgRectangle.getTxtXCoordinate().setEditable(false);
			dlgRectangle.getTxtYCoordinate().setText(String.valueOf(upperLeftPoint.getY()));
			dlgRectangle.getTxtYCoordinate().setEditable(false);
			dlgRectangle.setModal(true);
			dlgRectangle.setVisible(true);
			if (!dlgRectangle.isOk())
				return;
			newShape = dlgRectangle.getRectangle();
			startPoint = null;
		} else if (frame.getTglBtnCircle().isSelected()) {
			DlgCircle dlgCircle = new DlgCircle();
			center = new Point(e.getX(), e.getY());
			dlgCircle.setCircle(new Circle(center, -1, false, dlgCircle.getBtnBorderColor().getBackground(),
					dlgCircle.getBtnInnerColor().getBackground()));
			dlgCircle.getTxtXCoordinate().setText(String.valueOf(center.getX()));
			dlgCircle.getTxtXCoordinate().setEditable(false);
			dlgCircle.getTxtYCoordinate().setText(String.valueOf(center.getY()));
			dlgCircle.getTxtYCoordinate().setEditable(false);
			dlgCircle.setModal(true);
			dlgCircle.setVisible(true);
			if (!dlgCircle.isOk())
				return;
			newShape = dlgCircle.getCircle();
			startPoint = null;
		} else if (frame.getTglBtnDonut().isSelected()) {
			DlgDonut dlgDonut = new DlgDonut();
			center = new Point(e.getX(), e.getY());
			dlgDonut.setDonut(new Donut(center, -1, -1));

			dlgDonut.getTxtXCoordinate().setText(String.valueOf(center.getX()));
			dlgDonut.getTxtXCoordinate().setEditable(false);
			dlgDonut.getTxtYCoordinate().setText(String.valueOf(center.getY()));
			dlgDonut.getTxtYCoordinate().setEditable(false);
			dlgDonut.setModal(true);
			dlgDonut.setVisible(true);
			if (!dlgDonut.isOk())
				return;
			newShape = dlgDonut.getDonut();
			startPoint = null;
		} else if (frame.getTglBtnHexagon().isSelected()) {
			DlgHexagon dlgHexagon = new DlgHexagon();
			center = new Point(e.getX(), e.getY());
			dlgHexagon.setHexagon(new HexagonAdapter(center, -1, false, dlgHexagon.getBtnBorderColor().getBackground(),
					dlgHexagon.getBtnInnerColor().getBackground()));

			dlgHexagon.getTxtXCoordinate().setText(String.valueOf(center.getX()));
			dlgHexagon.getTxtXCoordinate().setEditable(false);
			dlgHexagon.getTxtYCoordinate().setText(String.valueOf(center.getY()));
			dlgHexagon.getTxtYCoordinate().setEditable(false);
			dlgHexagon.setModal(true);
			dlgHexagon.setVisible(true);
			if (!dlgHexagon.isOk())
				return;
			newShape = dlgHexagon.getHexagon();
			System.out.println(newShape);
			startPoint = null;
		}

		if (newShape != null)

		{
			System.out.println(newShape);
			// model.getShapes().add(newShape);
			shapesUndo.push(newShape);
			addShapeCmd = new AddShapeCmd(newShape, model);
			addShapeCmd.execute();
			// operation = "add";
			undoOp.push("add");
			/*
			 * System.out.println(undoOp); System.out.println(shapesUndo.peek());
			 * System.out.println(model.getShapes());
			 */
		}

		frame.repaint();
	}

	public void modify() throws Exception {
		Shape selected = frame.getView().getSelected();
		if (selected != null) {
			if (selected instanceof Point) {

				Point point = (Point) selected;
				DlgPoint dlgPoint = new DlgPoint();
				dlgPoint.setPoint(point);
				dlgPoint.setModal(true);
				dlgPoint.getTxtXCoordinate().setText(String.valueOf(point.getX()));
				dlgPoint.getTxtYCoordinate().setText(String.valueOf(point.getY()));
				dlgPoint.getBtnColor().setBackground(point.getColor());
				dlgPoint.setVisible(true);
				if (dlgPoint.isOk()) {
					/*
					 * shapesUndo.push(point); updatePointCmd = new UpdatePointCmd(point,
					 * dlgPoint.getPoint()); updatePointCmd.execute(); undoOp.push("modify");
					 * System.out.println(undoOp); System.out.println(shapesUndo.peek());
					 * System.out.println(model.getShapes()); //
					 * frame.getView().getModel().getShapes().remove(selected); //
					 * frame.getView().getModel().getShapes().add(dlgPoint.getPoint());
					 * frame.repaint();
					 */
					/*
					 * shapesUndo.push(point.clone()); modifyList.add(point.clone()); Point newPoint
					 * = dlgPoint.getPoint(); newShapes.push(newPoint);
					 * 
					 * updatePointCmd = new UpdatePointCmd(point, newPoint);
					 * updatePointCmd.execute(); modified.push(point);
					 * 
					 * undoOp.push("modifyPoint");
					 * 
					 * System.out.println(undoOp); System.out.println(shapesUndo.peek());
					 * System.out.println(newShapes.peek()); System.out.println(model.getShapes());
					 * 
					 * frame.repaint();
					 */

					undoOp.add("modify");
					modifyShape.add("point");

					// oldShape.add(point.clone());
					Point newPoint = dlgPoint.getPoint();

					updatePointCmd = new UpdatePointCmd((Point) selected, newPoint);
					updatePointCmd.execute();

					// modifiedShape.add(point);
					// newShape.add(newPoint);
					// modifiedShapeRedo.add(newPoint.clone());

					// System.out.println(model.getShapes());

					frame.repaint();
				}
				selected.setSelected(false);

			} else if (selected instanceof Line) {
				Line line = (Line) selected;
				DlgLine dlgLine = new DlgLine();
				dlgLine.setLine(line);
				dlgLine.setModal(true);
				dlgLine.getTxtStartX().setText(String.valueOf(line.getStartPoint().getX()));
				dlgLine.getTxtStartY().setText(String.valueOf(line.getStartPoint().getY()));
				dlgLine.getTxtEndX().setText(String.valueOf(line.getEndPoint().getX()));
				dlgLine.getTxtEndY().setText(String.valueOf(line.getEndPoint().getY()));
				dlgLine.getBtnColor().setBackground(line.getColor());
				dlgLine.setVisible(true);
				if (dlgLine.isOk()) {

					undoOp.add("modify");
					modifyShape.add("line");

					Line newLine = dlgLine.getLine();

					updateLineCmd = new UpdateLineCmd((Line) selected, newLine);
					updateLineCmd.execute();

					frame.repaint();
				}

			} else if (selected instanceof Rectangle) {
				Rectangle rectangle = (Rectangle) selected;
				DlgRectangle dlgRectangle = new DlgRectangle();
				dlgRectangle.setRectangle(rectangle);
				dlgRectangle.setModal(true);
				dlgRectangle.getTxtXCoordinate().setText(String.valueOf(rectangle.getUpperLeftPoint().getX()));
				dlgRectangle.getTxtYCoordinate().setText(String.valueOf(rectangle.getUpperLeftPoint().getY()));
				dlgRectangle.getTxtHeight().setText(String.valueOf(rectangle.getHeight()));
				dlgRectangle.getTxtWidth().setText(String.valueOf(rectangle.getWidth()));
				dlgRectangle.getBtnBorderColor().setBackground(rectangle.getColor());
				dlgRectangle.getBtnInnerColor().setBackground(rectangle.getInnerColor());
				dlgRectangle.setVisible(true);
				if (dlgRectangle.isOk()) {

					undoOp.add("modify");
					modifyShape.add("rectangle");

					Rectangle newRectangle = dlgRectangle.getRectangle();

					updateRectangleCmd = new UpdateRectangleCmd((Rectangle) selected, newRectangle);
					updateRectangleCmd.execute();

					frame.repaint();
				}

			} else if (selected instanceof Circle) {
				Circle circle = (Circle) selected;
				DlgCircle dlgCircle = new DlgCircle();
				dlgCircle.setCircle(circle);
				dlgCircle.setModal(true);
				dlgCircle.getTxtXCoordinate().setText(String.valueOf(circle.getCenter().getX()));
				dlgCircle.getTxtYCoordinate().setText(String.valueOf(circle.getCenter().getY()));
				dlgCircle.getTxtRadius().setText(String.valueOf(circle.getRadius()));
				dlgCircle.getBtnBorderColor().setBackground(circle.getColor());
				dlgCircle.getBtnInnerColor().setBackground(circle.getInnerColor());
				dlgCircle.setVisible(true);
				if (dlgCircle.isOk()) {

					undoOp.add("modify");
					modifyShape.add("circle");

					Circle newCircle = dlgCircle.getCircle();

					updateCircleCmd = new UpdateCircleCmd((Circle) selected, newCircle);
					updateCircleCmd.execute();

					frame.repaint();
				}
			} else if (selected instanceof Donut) {
				Donut donut = (Donut) selected;
				DlgDonut dlgDonut = new DlgDonut();
				dlgDonut.setDonut(donut);
				dlgDonut.setModal(true);
				dlgDonut.getTxtXCoordinate().setText(String.valueOf(donut.getCenter().getX()));
				dlgDonut.getTxtYCoordinate().setText(String.valueOf(donut.getCenter().getY()));
				dlgDonut.getTxtRadius().setText(String.valueOf(donut.getRadius()));
				dlgDonut.getTxtInnerRadius().setText(String.valueOf(donut.getInnerRadius()));
				dlgDonut.getBtnBorderColor().setBackground(donut.getColor());
				dlgDonut.getBtnInnerColor().setBackground(donut.getInnerColor());
				dlgDonut.setVisible(true);
				if (dlgDonut.isOk()) {

					undoOp.add("modify");
					modifyShape.add("donut");

					Donut newDonut = dlgDonut.getDonut();

					updateDonutCmd = new UpdateDonutCmd((Donut) selected, newDonut);
					updateDonutCmd.execute();

					frame.repaint();
				}
			} else if (selected instanceof HexagonAdapter) {
				HexagonAdapter hexagon = (HexagonAdapter) selected;
				DlgHexagon dlgHexagon = new DlgHexagon();
				dlgHexagon.setHexagon(hexagon);
				dlgHexagon.setModal(true);
				dlgHexagon.getTxtXCoordinate().setText(String.valueOf(hexagon.getX()));
				dlgHexagon.getTxtYCoordinate().setText(String.valueOf(hexagon.getY()));
				dlgHexagon.getTxtR().setText(String.valueOf(hexagon.getR()));
				dlgHexagon.getBtnBorderColor().setBackground(hexagon.getColor());
				dlgHexagon.getBtnInnerColor().setBackground(hexagon.getInnerColor());
				dlgHexagon.setVisible(true);
				if (dlgHexagon.isOk()) {

					undoOp.add("modify");
					modifyShape.add("hexagon");

					HexagonAdapter newHexagon = dlgHexagon.getHexagon();

					updateHexagonCmd = new UpdateHexagonCmd((HexagonAdapter) selected, newHexagon);
					updateHexagonCmd.execute();

					frame.repaint();
				}
			}
		}

	}

	public void delete() {
		Shape selected = frame.getView().getSelected();
		if (selected != null) {
			int selectedOpt = JOptionPane.showConfirmDialog(null, "Do you want to delete this shape?", "Message",
					JOptionPane.YES_NO_OPTION);
			if (selectedOpt == JOptionPane.YES_OPTION) {
				// frame.getView().getModel().getShapes().remove(selected);
				removeShapeCmd = new RemoveShapeCmd(selected, model);
				removeShapeCmd.execute();
				shapesUndo.push(selected);
				// operation = "delete";
				undoOp.push("delete");
				System.out.println(undoOp);
				System.out.println(shapesUndo.peek());
				System.out.println(model.getShapes());
			}
		}
		frame.getView().setSelected(null);
		frame.getBtnSelect().setSelected(false);
		frame.getView().repaint();

	}

	public void undo() throws Exception {
		if (undoOp.size() == 0) {
			JOptionPane.showMessageDialog(null, "Niste izvrsili nijednu operaciju!", "Message",
					JOptionPane.ERROR_MESSAGE);
		} else if (undoOp.peek() == "add") {
			if (model.getShapes().size() > 0) {
				addShapeCmd = new AddShapeCmd(shapesUndo.peek(), model);
				shapesRedo.push(shapesUndo.pop());
				addShapeCmd.unexecute();
				redoOp.push(undoOp.pop());

			}
		} else if (undoOp.peek() == "delete") {
			removeShapeCmd = new RemoveShapeCmd(shapesUndo.peek(), model);
			shapesRedo.push(shapesUndo.pop());
			removeShapeCmd.unexecute();
			redoOp.push(undoOp.pop());

		} else if (undoOp.peek() == "modify") {

			// for (int i = 0; i < modifyList.size(); i++) {
			if (modifyShape.peek() == "point") {
				/*
				 * System.out.println(undoOp); System.out.println(undoOp.peek());
				 */

				// modifiedShapeRedo.push(modifiedShape.peek());

				/*
				 * System.out.println(modifiedShape.peek());
				 * System.out.println(oldShape.peek());
				 */
				// modifiedShapeRedo.push(modifiedShape.peek().clone());

				// updatePointCmd = new UpdatePointCmd((Point) modifiedShape.pop(), (Point)
				// oldShape.peek());
				updatePointCmd.unexecute();

				modifyShapeRedo.push(modifyShape.pop());
				// oldShapeRedo.push(oldShape.pop());
				// modifiedShapeRedo.push(modifiedShape.pop());
				redoOp.push(undoOp.pop());

				/*
				 * System.out.println(undoOp); System.out.println(modifiedShapeRedo.peek());
				 * System.out.println(oldShapeRedo.peek());
				 * System.out.println(model.getShapes()); System.out.println(undoOp.peek());
				 */
			} else if (modifyShape.peek() == "line") {

				updateLineCmd.unexecute();

				modifyShapeRedo.push(modifyShape.pop());
				redoOp.push(undoOp.pop());
			} else if (modifyShape.peek() == "rectangle") {

				updateRectangleCmd.unexecute();

				modifyShapeRedo.push(modifyShape.pop());
				redoOp.push(undoOp.pop());
			} else if (modifyShape.peek() == "circle") {

				updateCircleCmd.unexecute();

				modifyShapeRedo.push(modifyShape.pop());
				redoOp.push(undoOp.pop());
			} else if (modifyShape.peek() == "donut") {

				updateDonutCmd.unexecute();

				modifyShapeRedo.push(modifyShape.pop());
				redoOp.push(undoOp.pop());
			} else if (modifyShape.peek() == "hexagon") {

				updateHexagonCmd.unexecute();

				modifyShapeRedo.push(modifyShape.pop());
				redoOp.push(undoOp.pop());
			}
		}
		frame.repaint();
	}

	public void redo() throws Exception {
		if (redoOp.size() == 0) {
			JOptionPane.showMessageDialog(null, "Niste izvrsili nijednu operaciju!", "Message",
					JOptionPane.ERROR_MESSAGE);
		} else if (redoOp.peek() == "add") {
			addShapeCmd = new AddShapeCmd(shapesRedo.peek(), model);
			System.out.println(shapesRedo.peek());
			System.out.println(shapesRedo);
			System.out.println(redoOp);
			System.out.println(model.getShapes());
			shapesUndo.push(shapesRedo.pop());
			addShapeCmd.execute();
			undoOp.push(redoOp.pop());
			// shapesUndo.pop();
			// frame.getView().getModel().getShapes().remove(shapesUndo.pop());

		} else if (redoOp.peek() == "delete") {
			removeShapeCmd = new RemoveShapeCmd(shapesRedo.peek(), model);
			System.out.println(shapesRedo.peek());
			System.out.println(shapesRedo);
			System.out.println(redoOp);
			System.out.println(model.getShapes());
			shapesUndo.push(shapesRedo.pop());
			removeShapeCmd.execute();
			undoOp.push(redoOp.pop());
			// shapesUndo.pop();
			// frame.getView().getModel().getShapes().remove(shapesUndo.pop());

		} else if (redoOp.peek() == "modify") {
			if (modifyShapeRedo.peek() == "point") {
				/*
				 * updatePointCmd = new UpdatePointCmd((Point) modifyList.get(i), (Point)
				 * newShapes.peek());
				 * 
				 * shapesRedo.push(shapesUndo.pop()); newShapesRedo.push(newShapes.pop());
				 * modified.peek();
				 * 
				 * updatePointCmd.unexecute();
				 * 
				 * redoOp.push(undoOp.pop());
				 */
				/*
				 * System.out.println(modifiedShapeRedo.peek());
				 * System.out.println(oldShapeRedo.peek());
				 * System.out.println(model.getShapes());
				 * 
				 * oldShape.push(oldShapeRedo.peek().clone());
				 */

				// updatePointCmd = new UpdatePointCmd((Point) oldShapeRedo.pop(), (Point)
				// modifiedShapeRedo.peek());
				updatePointCmd.execute();

				modifyShape.push(modifyShapeRedo.pop());
				// modifiedShape.push(modifiedShapeRedo.pop());
				undoOp.push(redoOp.pop());

				/*
				 * System.out.println(modifiedShapeRedo.peek());
				 * System.out.println(oldShapeRedo.peek());
				 * System.out.println(model.getShapes());
				 */
			} else if (modifyShapeRedo.peek() == "line") {

				updateLineCmd.execute();

				modifyShape.push(modifyShapeRedo.pop());
				undoOp.push(redoOp.pop());
			} else if (modifyShapeRedo.peek() == "rectangle") {

				updateRectangleCmd.execute();

				modifyShape.push(modifyShapeRedo.pop());
				undoOp.push(redoOp.pop());
			} else if (modifyShapeRedo.peek() == "circle") {

				updateCircleCmd.execute();

				modifyShape.push(modifyShapeRedo.pop());
				undoOp.push(redoOp.pop());
			} else if (modifyShapeRedo.peek() == "donut") {

				updateDonutCmd.execute();

				modifyShape.push(modifyShapeRedo.pop());
				undoOp.push(redoOp.pop());
			} else if (modifyShapeRedo.peek() == "hexagon") {

				updateHexagonCmd.execute();

				modifyShape.push(modifyShapeRedo.pop());
				undoOp.push(redoOp.pop());
			}
		}
		frame.repaint();
	}
}
