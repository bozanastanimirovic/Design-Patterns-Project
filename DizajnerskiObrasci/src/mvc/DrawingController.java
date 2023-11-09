package mvc;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.Iterator;

import javax.swing.JOptionPane;

import drawing.DlgCircle;
import drawing.DlgDonut;
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

	DrawingModel model;
	DrawingFrame frame;

	public DrawingController(DrawingModel model, DrawingFrame frame) {
		this.model = model;
		this.frame = frame;
	}

	public void mouseClicked(MouseEvent e) {
		Shape newShape = null;
		if (frame.getBtnSelect().isSelected()) {
			selected = null;
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

				Shape shape = it.next();
				if (shape.contains(e.getX(), e.getY())) {
					shape.setSelected(true);
					selected = shape;
					frame.getView().setSelected(shape);
				} else {
					shape.setSelected(false);
				}
			}

			frame.getView().repaint();

		} else if (frame.getTglBtnPoint().isSelected()) {
			DlgPoint dlgPoint = new DlgPoint();
			dlgPoint.getTxtXCoordinate().setText(String.valueOf(e.getX()));
			dlgPoint.getTxtXCoordinate().setEditable(false);
			dlgPoint.getTxtYCoordinate().setText(String.valueOf(e.getY()));
			dlgPoint.getTxtYCoordinate().setEditable(false);
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
		}

		if (newShape != null)

		{
			model.getShapes().add(newShape);
		}

		frame.repaint();

	}

	public void modify() {
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
					frame.getView().getModel().getShapes().remove(selected);
					frame.getView().getModel().getShapes().add(dlgPoint.getPoint());
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
					frame.getView().getModel().getShapes().remove(selected);
					frame.getView().getModel().getShapes().add(dlgLine.getLine());
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
					frame.getView().getModel().getShapes().remove(selected);
					frame.getView().getModel().getShapes().add(dlgRectangle.getRectangle());
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
					frame.getView().getModel().getShapes().remove(selected);
					frame.getView().getModel().getShapes().add(dlgCircle.getCircle());
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
					frame.getView().getModel().getShapes().remove(selected);
					frame.getView().getModel().getShapes().add(dlgDonut.getDonut());
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
				frame.getView().getModel().getShapes().remove(selected);
			}
		}
		frame.getView().setSelected(null);
		frame.getBtnSelect().setSelected(false);
		frame.getView().repaint();

	}

}
