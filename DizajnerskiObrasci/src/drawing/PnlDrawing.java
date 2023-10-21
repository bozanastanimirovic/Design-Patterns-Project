package drawing;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JPanel;

import geometry.Circle;
import geometry.Donut;
import geometry.Drawing;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Shape;

public class PnlDrawing extends JPanel {
	private FrmDrawing frame;
	private ArrayList<Shape> shapes = new ArrayList<Shape>();
	private Point startPoint;
	private Shape selected;
	private Point upperLeftPoint;
	private Point center;

	public PnlDrawing() {

	}

	public PnlDrawing(FrmDrawing frame) {
		this.frame = frame;
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				thisMouseClicked(e);
			}
		});
	}

	protected void thisMouseClicked(MouseEvent e) {
		Shape newShape = null;
		if (frame.getBtnSelect().isSelected()) {
			selected = null;
			Iterator<Shape> it = shapes.iterator();

			while (it.hasNext()) {
				Shape shape = it.next();
				if (shape.isSelected()) {
					if (shape.contains(e.getX(), e.getY())) {
						shape.setSelected(false);
						break;
					}
				}
				shape.setSelected(false);
				if (shape.contains(e.getX(), e.getY())) {
					shape.setSelected(true);
					selected = shape;
				}
				for (int i = 0; i < shapes.size(); i++) {
					for (int n = i + 1; n < shapes.size(); n++) {
						if (shapes.get(i).contains(e.getX(), e.getY()) && shapes.get(n).contains(e.getX(), e.getY())) {
							shape.setSelected(false);
							shapes.get(n).setSelected(true);
							shapes.get(i).setSelected(false);
						}
					}
				}

			}
			if (selected != null) {
				selected.setSelected(true);
			}

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
			if (!dlgRectangle.ok)
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
			if (!dlgCircle.ok)
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
			if (!dlgDonut.ok)
				return;
			newShape = dlgDonut.getDonut();
			startPoint = null;
		}

		if (newShape != null)

		{
			shapes.add(newShape);
		}

		repaint();

	}

	public void paint(Graphics g) {
		super.paint(g);
		Iterator<Shape> it = shapes.iterator();
		while (it.hasNext()) {
			it.next().draw(g);
		}
	}

	public Shape getSelected() {
		return selected;
	}

	public ArrayList<Shape> getShapes() {
		return shapes;
	}

	public void setSelected(Shape selected) {
		this.selected = selected;
	}

}
