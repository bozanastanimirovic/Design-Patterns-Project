package mvc;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import adapter.HexagonAdapter;
import command.AddShapeCmd;
import command.BringToBackCmd;
import command.BringToFrontCmd;
import command.RemoveShapeCmd;
import command.ToBackCmd;
import command.ToFrontCmd;
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
import observer.EnableButton;
import observer.EnableButtonUpdate;
import strategy.FileBin;
import strategy.FileTxt;

public class DrawingController {

	private Point startPoint;
	private Shape selected;
	private Point upperLeftPoint;
	private Point center;

	private Stack<Shape> shapesUndo = new Stack<Shape>();
	private Stack<Shape> shapesRedo = new Stack<Shape>();

	private Stack<String> modifyShape = new Stack<String>();
	private Stack<String> modifyShapeRedo = new Stack<String>();
	private Stack<Shape> modifiedShape = new Stack<Shape>();
	private Stack<Shape> modifiedShapeRedo = new Stack<Shape>();

	private Stack<String> undoOp = new Stack<String>();
	private Stack<String> redoOp = new Stack<String>();

	private ArrayList<Shape> selectedList = new ArrayList<Shape>();
	private List<Shape> deletedShapes = new ArrayList<>();
	private List<Shape> deletedShapesUndo = new ArrayList<>();

	private ArrayList<Shape> openCopy = new ArrayList<Shape>();

	AddShapeCmd addShapeCmd;
	RemoveShapeCmd removeShapeCmd;
	UpdatePointCmd updatePointCmd;
	UpdateLineCmd updateLineCmd;
	UpdateRectangleCmd updateRectangleCmd;
	UpdateCircleCmd updateCircleCmd;
	UpdateDonutCmd updateDonutCmd;
	UpdateHexagonCmd updateHexagonCmd;
	BringToFrontCmd bringToFrontCmd;
	BringToBackCmd bringToBackCmd;
	ToFrontCmd toFrontCmd;
	ToBackCmd toBackCmd;

	List<Shape> selectedShapes = new ArrayList<>();
	List<String> commands = new ArrayList<String>();

	DrawingModel model;
	DrawingFrame frame;

	EnableButton enable;
	EnableButtonUpdate enableUpdate;

	public DrawingController(DrawingModel model, DrawingFrame frame) {
		this.model = model;
		this.frame = frame;
		this.enable = new EnableButton();
		this.enableUpdate = new EnableButtonUpdate(frame, model);
		enable.addListener(enableUpdate);
	}

	public void mouseClicked(MouseEvent e) {
		Shape newShape = null;
		if (frame.getBtnSelect().isSelected()) {
			Iterator<Shape> it = model.getShapes().iterator();
			while (it.hasNext()) {
				Shape shape = it.next();
				if (shape.contains(e.getX(), e.getY())) {
					if (!shape.isSelected()) {
						shape.setSelected(true);
						selected = shape;
						setSelected(shape);
						selectedList.add(shape);
						undoOp.add("select");
						shapesUndo.add(shape);
						commands.add("Selected-->" + shape.toString());
						frame.getListModel().addElement(commands.get(commands.size() - 1));
						enable.setAddedShape(model.getShapes().size());
						enable.setSelectedShape(selectedList.size());
						enable.setUndo(undoOp.size());
						enable.setRedo(undoOp.size());
					} else {
						shape.setSelected(false);
						selectedList.remove(shape);
						undoOp.add("unselect");
						shapesUndo.add(shape);
						commands.add("Unselected-->" + shape.toString());
						frame.getListModel().addElement(commands.get(commands.size() - 1));
						enable.setAddedShape(model.getShapes().size());
						enable.setSelectedShape(selectedList.size());
						enable.setUndo(undoOp.size());
						enable.setRedo(undoOp.size());
					}
				}
			}
			frame.getBtnSelect().setSelected(false);
			frame.repaint();

		} else if (frame.getTglBtnPoint().isSelected()) {
			Point point = new Point(e.getX(), e.getY());
			point.setColor(frame.getBorderColor());
			newShape = point;
			startPoint = null;
		} else if (frame.getTglBtnLine().isSelected()) {
			if (startPoint == null) {
				startPoint = new Point(e.getX(), e.getY());
			} else {
				Line line = new Line(startPoint, new Point(e.getX(), e.getY()));
				line.setColor(frame.getBorderColor());
				newShape = line;
				startPoint = null;
			}
		} else if (frame.getTglBtnRectangle().isSelected()) {
			DlgRectangle dlgRectangle = new DlgRectangle();
			upperLeftPoint = new Point(e.getX(), e.getY());
			dlgRectangle.getBtnBorderColor().setBackground(frame.getBorderColor());
			dlgRectangle.getBtnInnerColor().setBackground(frame.getInnerColor());
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
			dlgCircle.getBtnBorderColor().setBackground(frame.getBorderColor());
			dlgCircle.getBtnInnerColor().setBackground(frame.getInnerColor());
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
			dlgDonut.getBtnBorderColor().setBackground(frame.getBorderColor());
			dlgDonut.getBtnInnerColor().setBackground(frame.getInnerColor());
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
			dlgHexagon.getBtnBorderColor().setBackground(frame.getBorderColor());
			dlgHexagon.getBtnInnerColor().setBackground(frame.getInnerColor());
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

		if (newShape != null) {
			shapesUndo.push(newShape);
			addShapeCmd = new AddShapeCmd(newShape, model);
			addShapeCmd.execute();
			undoOp.push("add");
			commands.add("Added-->" + newShape.toString());
			frame.getListModel().addElement(commands.get(commands.size() - 1));
			enable.setAddedShape(model.getShapes().size());
		}
		frame.repaint();
	}

	public void modify() throws Exception {
		Shape selected = getSelected();
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
					Point newPoint = dlgPoint.getPoint();
					Point oldPoint = point.clone();

					undoOp.add("modify");
					modifyShape.add("point");
					modifiedShape.add(selected.clone());
					modifiedShapeRedo.add(newPoint.clone());

					updatePointCmd = new UpdatePointCmd((Point) selected, newPoint);
					updatePointCmd.execute();
					commands.add("Modified-->" + oldPoint.toString() + "~" + newPoint.toString());
					frame.getListModel().addElement(commands.get(commands.size() - 1));

					frame.repaint();
				}

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
					Line newLine = dlgLine.getLine();
					Line oldLine = line.clone();

					undoOp.add("modify");
					modifyShape.add("line");
					modifiedShape.add(selected.clone());

					modifiedShapeRedo.add(newLine.clone());

					updateLineCmd = new UpdateLineCmd((Line) selected, newLine);
					updateLineCmd.execute();

					commands.add("Modified-->" + oldLine.toString() + "~" + newLine.toString());
					frame.getListModel().addElement(commands.get(commands.size() - 1));

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
					Rectangle newRectangle = dlgRectangle.getRectangle();
					Rectangle oldRectangle = rectangle.clone();

					undoOp.add("modify");
					modifyShape.add("rectangle");
					modifiedShape.add(selected.clone());
					modifiedShapeRedo.add(newRectangle.clone());

					updateRectangleCmd = new UpdateRectangleCmd((Rectangle) selected, newRectangle);
					updateRectangleCmd.execute();

					commands.add("Modified-->" + oldRectangle.toString() + "~" + newRectangle.toString());
					frame.getListModel().addElement(commands.get(commands.size() - 1));

					frame.repaint();
				}

			} else if ((selected instanceof Circle) & !(selected instanceof Donut)) {
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
					Circle newCircle = dlgCircle.getCircle();
					Circle oldCircle = circle.clone();

					undoOp.add("modify");
					modifyShape.add("circle");
					modifiedShape.add(selected.clone());
					modifiedShapeRedo.add(newCircle.clone());

					updateCircleCmd = new UpdateCircleCmd((Circle) selected, newCircle);
					updateCircleCmd.execute();

					commands.add("Modified-->" + oldCircle.toString() + "~" + newCircle.toString());
					frame.getListModel().addElement(commands.get(commands.size() - 1));

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
					Donut newDonut = dlgDonut.getDonut();
					Donut oldDonut = donut.clone();

					undoOp.add("modify");
					modifyShape.add("donut");
					modifiedShape.add(selected.clone());
					modifiedShapeRedo.add(newDonut.clone());

					updateDonutCmd = new UpdateDonutCmd((Donut) selected, newDonut);
					updateDonutCmd.execute();

					commands.add("Modified --> " + oldDonut.toString() + "~" + newDonut.toString());
					frame.getListModel().addElement(commands.get(commands.size() - 1));

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
					HexagonAdapter newHexagon = dlgHexagon.getHexagon();
					HexagonAdapter oldHexagon = (HexagonAdapter) hexagon.clone();

					undoOp.add("modify");
					modifyShape.add("hexagon");
					modifiedShape.add(selected.clone());
					modifiedShapeRedo.add(newHexagon.clone());

					updateHexagonCmd = new UpdateHexagonCmd((HexagonAdapter) selected, newHexagon);
					updateHexagonCmd.execute();

					commands.add("Modified-->" + oldHexagon.toString() + "~" + newHexagon.toString());
					frame.getListModel().addElement(commands.get(commands.size() - 1));

					frame.repaint();
				}
			}
		}
		selected.setSelected(true);
		enable.setAddedShape(model.getShapes().size());
		enable.setSelectedShape(selectedList.size());
		enable.setUndo(undoOp.size());
		enable.setRedo(undoOp.size());
		frame.repaint();
	}

	public void delete() {
		Shape selected = getSelected();
		if (selected != null) {
			if (selectedList.size() == 1) {
				int selectedOpt = JOptionPane.showConfirmDialog(null, "Do you want to delete this shape?", "Message",
						JOptionPane.YES_NO_OPTION);
				if (selectedOpt == JOptionPane.YES_OPTION) {
					removeShapeCmd = new RemoveShapeCmd(selected, model);
					removeShapeCmd.execute();
					shapesUndo.push(selected);
					undoOp.push("delete");
					commands.add("Deleted-->" + selected.toString());
					frame.getListModel().addElement(commands.get(commands.size() - 1));
				}
			} else if (selectedList.size() > 1) {
				int selectedOpt = JOptionPane.showConfirmDialog(null, "Do you want to delete all shapes?", "Message",
						JOptionPane.YES_NO_OPTION);
				if (selectedOpt == JOptionPane.YES_OPTION) {
					Iterator<Shape> it = selectedList.iterator();

					while (it.hasNext()) {
						Shape shape = it.next();
						removeShapeCmd = new RemoveShapeCmd(shape, model);
						removeShapeCmd.execute();
						deletedShapes.add(shape);
						undoOp.push("deleteAll");
						commands.add("Deleted-->" + selected.toString());
						frame.getListModel().addElement(commands.get(commands.size() - 1));
					}
				}
			}
		}
		setSelected(null);
		frame.getBtnSelect().setSelected(false);
		enable.setAddedShape(model.getShapes().size());
		enable.setSelectedShape(selectedList.size());
		enable.setUndo(undoOp.size());
		enable.setRedo(undoOp.size());
		frame.repaint();

	}

	public void undo() throws Exception {
		if (undoOp.size() == 0) {
			JOptionPane.showMessageDialog(null, "Niste izvrsili nijednu operaciju!", "Message",
					JOptionPane.ERROR_MESSAGE);
			frame.getBtnUndo().setEnabled(false);
		} else if (undoOp.peek().equals("add")) {
			if (model.getShapes().size() > 0) {
				addShapeCmd = new AddShapeCmd(shapesUndo.peek(), model);
				commands.add("Undo-->Add:" + shapesUndo.peek().toString());
				shapesRedo.push(shapesUndo.pop());
				addShapeCmd.unexecute();
				redoOp.push(undoOp.pop());
				frame.repaint();
			}
		} else if (undoOp.peek().equals("delete")) {
			removeShapeCmd = new RemoveShapeCmd(shapesUndo.peek(), model);
			commands.add("Undo-->Delete:" + shapesUndo.peek().toString());
			shapesRedo.push(shapesUndo.pop());
			removeShapeCmd.unexecute();
			redoOp.push(undoOp.pop());

		} else if (undoOp.peek().equals("deleteAll")) {
			Iterator<Shape> it = deletedShapes.iterator();

			while (it.hasNext()) {
				Shape shape = it.next();
				removeShapeCmd = new RemoveShapeCmd(shape, model);
				removeShapeCmd.unexecute();
				commands.add("Undo-->Delete:" + shape.toString());
				deletedShapesUndo.add(shape);
				redoOp.push(undoOp.pop());
			}
			deletedShapes.clear();

		} else if (undoOp.peek().equals("modify")) {

			if (modifyShape.peek() == "point") {
				updatePointCmd.unexecute();

				modifyShapeRedo.push(modifyShape.pop());
				redoOp.push(undoOp.pop());
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
			commands.add("Undo-->Modify:" + modifiedShape.peek().toString());
		} else if (undoOp.peek().equals("bringToBack")) {
			Shape selected = shapesUndo.pop();
			bringToBackCmd.unexecute();

			redoOp.add(undoOp.pop());
			shapesRedo.add(selected);
			commands.add("Undo-->BringToBack:" + selected.toString());
		} else if (undoOp.peek().equals("bringToFront")) {
			Shape selected = shapesUndo.pop();

			bringToFrontCmd.unexecute();

			redoOp.add(undoOp.pop());
			shapesRedo.add(selected);
			commands.add("Undo-->BringToFront:" + selected.toString());
		} else if (undoOp.peek().equals("toFront")) {
			Shape selected = shapesUndo.pop();

			toFrontCmd.unexecute();

			redoOp.add(undoOp.pop());
			shapesRedo.add(selected);
			commands.add("Undo-->ToFront:" + selected.toString());
		} else if (undoOp.peek().equals("toBack")) {
			Shape selected = shapesUndo.pop();

			toBackCmd.unexecute();

			redoOp.add(undoOp.pop());
			shapesRedo.add(selected);
			commands.add("Undo-->ToBack:" + selected.toString());
		} else if (undoOp.peek().equals("select")) {
			Shape selected = shapesUndo.pop();

			selected.setSelected(false);

			redoOp.add(undoOp.pop());
			shapesRedo.add(selected);
			commands.add("Undo-->Select:" + selected.toString());
		} else if (undoOp.peek().equals("unselect")) {
			Shape selected = shapesUndo.pop();

			selected.setSelected(true);

			redoOp.add(undoOp.pop());
			shapesRedo.add(selected);
			commands.add("Undo-->Unselect:" + selected.toString());
		}
		enable.setUndo(undoOp.size());
		enable.setRedo(redoOp.size());
		frame.getListModel().addElement(commands.get(commands.size() - 1));
		frame.repaint();
	}

	public void redo() throws Exception {
		if (redoOp.size() == 0) {
			JOptionPane.showMessageDialog(null, "Niste izvrsili nijednu operaciju!", "Message",
					JOptionPane.ERROR_MESSAGE);
		} else if (redoOp.peek() == "add") {
			addShapeCmd = new AddShapeCmd(shapesRedo.peek(), model);
			commands.add("Redo-->Add:" + shapesRedo.peek().toString());
			shapesUndo.push(shapesRedo.pop());
			addShapeCmd.execute();
			undoOp.push(redoOp.pop());

		} else if (redoOp.peek() == "delete") {
			removeShapeCmd = new RemoveShapeCmd(shapesRedo.peek(), model);
			commands.add("Redo-->Delete:" + shapesRedo.peek().toString());
			shapesUndo.push(shapesRedo.pop());
			removeShapeCmd.execute();
			undoOp.push(redoOp.pop());

		} else if (redoOp.peek() == "deleteAll") {
			Iterator<Shape> it = deletedShapesUndo.iterator();

			System.out.println("Prvi" + deletedShapesUndo);
			while (it.hasNext()) {
				Shape shape = it.next();
				removeShapeCmd = new RemoveShapeCmd(shape, model);
				removeShapeCmd.execute();
				commands.add("Redo-->Delete:" + shape.toString());
				deletedShapes.add(shape);
				redoOp.push(undoOp.pop());
			}
			deletedShapesUndo.clear();
			System.out.println("Drugi" + deletedShapes);

		} else if (redoOp.peek() == "modify") {
			if (modifyShapeRedo.peek() == "point") {
				updatePointCmd.execute();

				modifyShape.push(modifyShapeRedo.pop());
				undoOp.push(redoOp.pop());

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
			commands.add("Redo-->Modify:" + modifiedShapeRedo.peek().toString());
		} else if (redoOp.peek() == "bringToBack") {
			Shape selected = shapesRedo.pop();
			bringToBackCmd.execute();

			undoOp.add(redoOp.pop());
			shapesUndo.add(selected);
			commands.add("Redo-->Bring To Back:" + selected.toString());
		} else if (redoOp.peek() == "bringToFront") {
			Shape selected = shapesRedo.pop();
			bringToFrontCmd.execute();

			undoOp.add(redoOp.pop());
			shapesUndo.add(selected);
			commands.add("Redo-->Bring To Front:" + selected.toString());
		} else if (redoOp.peek() == "toFront") {
			Shape selected = shapesRedo.pop();

			toFrontCmd.execute();

			undoOp.add(redoOp.pop());
			shapesRedo.add(selected);
			commands.add("Redo-->To Front:" + selected.toString());
		} else if (redoOp.peek() == "toBack") {
			Shape selected = shapesRedo.pop();

			toBackCmd.execute();

			undoOp.add(redoOp.pop());
			shapesRedo.add(selected);
			commands.add("Redo-->To Back:" + selected.toString());
		} else if (redoOp.peek() == "select") {
			Shape selected = shapesRedo.pop();

			selected.setSelected(true);

			undoOp.add(redoOp.pop());
			shapesRedo.add(selected);
			commands.add("Redo-->Select:" + selected.toString());
		} else if (redoOp.peek() == "unselect") {
			Shape selected = shapesRedo.pop();

			selected.setSelected(false);

			undoOp.add(redoOp.pop());
			shapesRedo.add(selected);
			commands.add("Redo-->Unselect:" + selected.toString());
		}
		enable.setUndo(undoOp.size());
		enable.setRedo(redoOp.size());
		frame.getListModel().addElement(commands.get(commands.size() - 1));
		frame.repaint();
	}

	public void toFront() {
		Shape selected = getSelected();
		toFrontCmd = new ToFrontCmd(model, selected);
		toFrontCmd.execute();
		frame.repaint();
		undoOp.add("toFront");
		shapesUndo.add(selected);
		commands.add("ToFront-->" + selected.toString());
		frame.getListModel().addElement(commands.get(commands.size() - 1));
		enable.setUndo(undoOp.size());
		enable.setRedo(redoOp.size());
	}

	public void toBack() {
		Shape selected = getSelected();
		toBackCmd = new ToBackCmd(model, selected);
		toBackCmd.execute();
		frame.repaint();
		undoOp.add("toBack");
		shapesUndo.add(selected);
		commands.add("ToBack-->" + selected.toString());
		frame.getListModel().addElement(commands.get(commands.size() - 1));
		enable.setAddedShape(model.getShapes().size());
		enable.setSelectedShape(selectedList.size());
		enable.setUndo(undoOp.size());
		enable.setRedo(redoOp.size());

	}

	public void bringToFront() {
		Shape selected = getSelected();
		bringToFrontCmd = new BringToFrontCmd(model, selected);
		bringToFrontCmd.execute();
		frame.repaint();
		undoOp.add("bringToFront");
		shapesUndo.add(selected);
		commands.add("BroughtToFront-->" + selected.toString());
		frame.getListModel().addElement(commands.get(commands.size() - 1));
		enable.setAddedShape(model.getShapes().size());
		enable.setSelectedShape(selectedList.size());
		enable.setUndo(undoOp.size());
		enable.setRedo(redoOp.size());
	}

	public void bringToBack() {
		Shape selected = getSelected();
		bringToBackCmd = new BringToBackCmd(model, selected);
		bringToBackCmd.execute();
		frame.repaint();
		undoOp.add("bringToBack");
		shapesUndo.add(selected);
		commands.add("BroughtToBack-->" + selected.toString());
		frame.getListModel().addElement(commands.get(commands.size() - 1));
		enable.setAddedShape(model.getShapes().size());
		enable.setSelectedShape(selectedList.size());
		enable.setUndo(undoOp.size());
		enable.setRedo(redoOp.size());
	}

	public Shape getSelected() {
		return selected;
	}

	public void setSelected(Shape selected) {
		this.selected = selected;
	}

	public void save() {
		File f = new File("D:\\Faks\\Dizajnerski obrasci\\Fajlovi");
		JFileChooser fileChooser = new JFileChooser(f);
		fileChooser.setDialogTitle("Save");

		FileNameExtensionFilter filterTxt = new FileNameExtensionFilter("Text file (*.txt)", "txt");
		FileNameExtensionFilter filterBin = new FileNameExtensionFilter("Drawing (*.bin)", "bin");
		fileChooser.setFileFilter(filterTxt);
		fileChooser.addChoosableFileFilter(filterBin);

		int opt = fileChooser.showSaveDialog(frame);

		if (opt == JFileChooser.APPROVE_OPTION) {
			File fileToSave = fileChooser.getSelectedFile();

			if (fileChooser.getFileFilter().equals(filterTxt) && !fileToSave.getName().toLowerCase().endsWith(".txt")) {
				fileToSave = new File(fileToSave.getAbsolutePath() + ".txt");
				FileTxt file = new FileTxt(frame, model, this);
				file.save(fileToSave.toString());
			} else if (fileChooser.getFileFilter().equals(filterBin)
					&& !fileToSave.getName().toLowerCase().endsWith(".bin")) {
				fileToSave = new File(fileToSave.getAbsolutePath() + ".bin");
				FileBin file = new FileBin(frame, model);
				file.save(fileToSave.toString());
			}
		}
		enable.setAddedShape(model.getShapes().size());
		enable.setSelectedShape(selectedList.size());
		enable.setUndo(undoOp.size());
		enable.setRedo(undoOp.size());
	}

	public void openTxt() {
		File f = new File("D:\\Faks\\Dizajnerski obrasci\\Fajlovi");
		JFileChooser fileChooser = new JFileChooser(f);
		fileChooser.setDialogTitle("Open");
		fileChooser.setFileFilter(new FileNameExtensionFilter("Text file (*.txt)", "txt"));

		int opt = fileChooser.showOpenDialog(fileChooser);

		if (opt == JFileChooser.APPROVE_OPTION) {
			File fileToOpen = fileChooser.getSelectedFile();

			FileTxt file = new FileTxt(frame, model, this);
			file.open(fileToOpen.toString());
		}
		enable.setAddedShape(model.getShapes().size());
		enable.setSelectedShape(selectedList.size());
		enable.setUndo(undoOp.size());
		enable.setRedo(undoOp.size());
	}

	public void drawOpened(String line) {
		if (line != null) {
			String[] one = line.split("-->");
			//System.out.println("one[0]: " + one[0]);
			if (one[0].equals("Added")) {
				Shape shape = readShape(one[1]);
				shapesUndo.push(shape);
				addShapeCmd = new AddShapeCmd(shape, model);
				addShapeCmd.execute();
				undoOp.push("add");
			} else if (one[0].equals("Deleted")) {
				Shape shape = readShape(one[1]);
				removeShapeCmd = new RemoveShapeCmd(shape, model);
				removeShapeCmd.execute();
				shapesUndo.push(shape);
				undoOp.push("delete");
			} else if (one[0].equals("Modified")) {
				String[] modified = one[1].split("~");

				// model.getShapes().indexOf(shape);
				System.out.println("TEST" + readShape(modified[0]));
				if (model.getShapes().contains(readShape(modified[0]))) {
					Shape shape = model.get(model.getShapes().indexOf(readShape(modified[0])));
					Shape newShape = readShape(modified[1]);
					System.out.println(model.getShapes().indexOf(shape));
					System.out.println(shape);
					System.out.println(readShape(modified[0]));
					System.out.println(model.getShapes());
					if (shape instanceof Point) {
						undoOp.add("modify");
						modifyShape.add("point");
						modifiedShape.add(shape.clone());
						modifiedShapeRedo.add(newShape.clone());
						updatePointCmd = new UpdatePointCmd((Point) shape, (Point) newShape);
						updatePointCmd.execute();
					} else if (shape instanceof Line) {
						undoOp.add("modify");
						modifyShape.add("line");
						modifiedShape.add(shape.clone());
						modifiedShapeRedo.add(newShape.clone());
						updateLineCmd = new UpdateLineCmd((Line) shape, (Line) newShape);
						updateLineCmd.execute();
					} else if (shape instanceof Rectangle) {
						undoOp.add("modify");
						modifyShape.add("rectangle");
						modifiedShape.add(shape.clone());
						modifiedShapeRedo.add(newShape.clone());
						updateRectangleCmd = new UpdateRectangleCmd((Rectangle) shape, (Rectangle) newShape);
						updateRectangleCmd.execute();
					} else if ((shape instanceof Circle) & !(shape instanceof Donut)) {
						undoOp.add("modify");
						modifyShape.add("circle");
						modifiedShape.add(shape.clone());
						modifiedShapeRedo.add(newShape.clone());
						updateCircleCmd = new UpdateCircleCmd((Circle) shape, (Circle) newShape);
						try {
							updateCircleCmd.execute();
						} catch (Exception e) {
							e.printStackTrace();
						}
					} else if (shape instanceof Donut) {
						undoOp.add("modify");
						modifyShape.add("donut");
						modifiedShape.add(shape.clone());
						modifiedShapeRedo.add(newShape.clone());
						updateDonutCmd = new UpdateDonutCmd((Donut) shape, (Donut) newShape);
						try {
							updateDonutCmd.execute();
						} catch (Exception e) {
							e.printStackTrace();
						}
					} else if (shape instanceof HexagonAdapter) {
						System.out.println(shape);
						undoOp.add("modify");
						modifyShape.add("hexagon");
						modifiedShape.add(shape.clone());
						modifiedShapeRedo.add(newShape.clone());
						updateHexagonCmd = new UpdateHexagonCmd((HexagonAdapter) shape, (HexagonAdapter) newShape);
						updateHexagonCmd.execute();
					}
				}
			} else if (one[0].equals("Undo")) {
				try {
					undo();
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (one[0].equals("Redo")) {
				try {
					redo();
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (one[0].equals("ToFront")) {
				int index = model.getShapes().indexOf(readShape(one[1]));
				Shape shape = model.get(index);
				toFrontCmd = new ToFrontCmd(model, shape);
				toFrontCmd.execute();
				frame.repaint();
				undoOp.add("toFront");
				shapesUndo.add(shape);
			} else if (one[0].equals("ToBack")) {
				int index = model.getShapes().indexOf(readShape(one[1]));
				Shape shape = model.get(index);
				toBackCmd = new ToBackCmd(model, shape);
				toBackCmd.execute();
				frame.repaint();
				undoOp.add("toBack");
				shapesUndo.add(shape);
			} else if (one[0].equals("BroughtToFront")) {
				int index = model.getShapes().indexOf(readShape(one[1]));
				Shape shape = model.get(index);
				bringToFrontCmd = new BringToFrontCmd(model, shape);
				bringToFrontCmd.execute();
				undoOp.add("bringToFront");
				shapesUndo.add(shape);
			} else if (one[0].equals("BroughtToBack")) {
				int index = model.getShapes().indexOf(readShape(one[1]));
				Shape shape = model.get(index);
				bringToBackCmd = new BringToBackCmd(model, shape);
				bringToBackCmd.execute();
				undoOp.add("bringToBack");
				shapesUndo.add(shape);
			} else if (one[0].equals("Selected")) {
				/*
				 * System.out.println("why" + one[0]); Shape shape =
				 * model.get(model.getShapes().indexOf(readShape(one[1])));
				 * shape.setSelected(true); selected = shape; setSelected(shape);
				 * selectedList.add(shape); undoOp.add("select"); shapesUndo.add(shape);
				 */
				if (model.getShapes().contains(readShape(one[1]))) {
					int index = model.getShapes().indexOf(readShape(one[1]));
					Shape shape = model.get(index);

					shape.setSelected(true);
					selected = shape;
					setSelected(shape);
					selectedList.add(shape);
					undoOp.add("select");
					shapesUndo.add(shape);
				} else {
					System.out.println("Shape not found in the model.");
				}
			} else if (one[0].equals("Unselected")) {

				if (model.getShapes().contains(readShape(one[1]))) {
					int index = model.getShapes().indexOf(readShape(one[1]));
					Shape shape = model.get(index);

					shape.setSelected(false);
					selectedList.remove(shape);
					undoOp.add("unselect");
					shapesUndo.add(shape);
				} else {
					System.out.println("Shape not found in the model.");
				}
			}
			enable.setAddedShape(model.getShapes().size());
			enable.setSelectedShape(selectedList.size());
			enable.setUndo(undoOp.size());
			enable.setRedo(undoOp.size());
			frame.repaint();
		}
	}

	private Shape readShape(String string) {
		String[] el = string.split(":");
		if (el[0].equals("Point")) {
			Point point = new Point();
			String[] p = el[1].split(",");
			point.setX(Integer.parseInt(p[0]));
			point.setY(Integer.parseInt(p[1]));
			Color c = parseColor(el[2]);
			point.setColor(c);
			return point;
		} else if (el[0].equals("Line")) {
			Line line = new Line();
			String[] start = el[2].split(",");
			String[] end = el[3].split(",");
			line.getStartPoint().setX(Integer.parseInt(start[0]));
			line.getStartPoint().setY(Integer.parseInt(start[1]));
			line.getEndPoint().setX(Integer.parseInt(end[0]));
			line.getEndPoint().setY(Integer.parseInt(end[1]));
			line.setColor(parseColor(el[4]));
			return line;
		} else if (el[0].equals("Circle")) {
			Circle circle = new Circle();
			String[] coordinates = el[2].split(",");
			String[] radius = coordinates[2].split("=");
			circle.getCenter().setX(Integer.parseInt(coordinates[0]));
			circle.getCenter().setY(Integer.parseInt(coordinates[1]));
			try {
				circle.setRadius(Integer.parseInt(radius[1]));
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			circle.setColor(parseColor(el[3]));
			circle.setInnerColor(parseColor(el[4]));
			return circle;
		} else if (el[0].equals("Rectangle")) {
			Rectangle rectangle = new Rectangle();
			String[] point = el[2].split(",");
			String[] width = point[2].split("=");
			String[] height = point[3].split("=");
			rectangle.getUpperLeftPoint().setX(Integer.parseInt(point[0]));
			rectangle.getUpperLeftPoint().setY(Integer.parseInt(point[1]));
			rectangle.setHeight(Integer.parseInt(height[1]));
			rectangle.setWidth(Integer.parseInt(width[1]));
			rectangle.setColor(parseColor(el[3]));
			rectangle.setInnerColor(parseColor(el[4]));
			return rectangle;
		} else if (el[0].equals("Donut")) {
			Donut donut = new Donut();
			String[] coordinates = el[2].split(",");
			String[] radius = coordinates[2].split("=");
			String[] innerRadius = coordinates[3].split("=");
			donut.getCenter().setX(Integer.parseInt(coordinates[0]));
			donut.getCenter().setY(Integer.parseInt(coordinates[1]));
			try {
				donut.setRadius(Integer.parseInt(radius[1]));
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			donut.setInnerRadius(Integer.parseInt(innerRadius[1]));
			donut.setColor(parseColor(el[3]));
			donut.setInnerColor(parseColor(el[4]));
			return donut;
		} else if (el[0].equals("Hexagon")) {
			HexagonAdapter hexagon = new HexagonAdapter();
			String[] coordinates = el[2].split(",");
			String[] radius = coordinates[2].split("=");
			hexagon.setX(Integer.parseInt(coordinates[0]));
			hexagon.setY(Integer.parseInt(coordinates[1]));
			hexagon.setR(Integer.parseInt(radius[1]));
			hexagon.setColor(parseColor(el[3]));
			hexagon.setInnerColor(parseColor(el[4]));
			return hexagon;
		} else {
			return null;
		}
	}

	private Color parseColor(String colorString) {
		colorString = colorString.replace("[", " ").replace("]", "");
		String[] string = colorString.split("[=,]");

		int r = Integer.parseInt(string[1]);
		int g = Integer.parseInt(string[3]);
		int b = Integer.parseInt(string[5]);

		return new Color(r, g, b);
	}

	public void openBin() {
		File f = new File("D:\\Faks\\Dizajnerski obrasci\\Fajlovi");
		JFileChooser fileChooser = new JFileChooser(f);
		fileChooser.setDialogTitle("Open");
		fileChooser.setFileFilter(new FileNameExtensionFilter("Drawing (*.bin)", "bin"));

		int opt = fileChooser.showOpenDialog(fileChooser);

		if (opt == JFileChooser.APPROVE_OPTION) {
			File fileToOpen = fileChooser.getSelectedFile();

			FileBin file = new FileBin(frame, model);
			file.open(fileToOpen.toString());
			frame.repaint();
		}
		enable.setAddedShape(model.getShapes().size());
		enable.setSelectedShape(selectedList.size());
		enable.setUndo(undoOp.size());
		enable.setRedo(undoOp.size());
	}

}
