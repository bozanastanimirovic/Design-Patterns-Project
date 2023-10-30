package drawing;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import geometry.Circle;
import geometry.Donut;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Shape;

import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JColorChooser;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JToggleButton;
import java.awt.Insets;
import javax.swing.JToolBar;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.ButtonGroup;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;

public class FrmDrawing extends JFrame {

	private JPanel contentPane;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private PnlDrawing pnlDrawing = new PnlDrawing(this);
	private JToggleButton tglBtnPoint;
	private JToggleButton tglBtnLine;
	private JToggleButton tglBtnRectangle;
	private JToggleButton tglBtnCircle;
	private JToggleButton tglBtnDonut;
	private JToggleButton btnSelect;
	private JToolBar toolBar;
	private Component verticalStrut_2;
	private JButton btnBorderColor;
	private JButton btnInnerColor;
	private Component verticalStrut;
	private Color borderColor;
	private Color innerColor;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmDrawing frame = new FrmDrawing();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmDrawing() {
		setTitle("Stanimirovic Bozana IT 32/2021 ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 772, 472);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 182, 193));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		pnlDrawing.setBackground(Color.WHITE);
		contentPane.add(pnlDrawing, BorderLayout.CENTER);
		contentPane.setVisible(true);

		toolBar = new JToolBar();
		toolBar.setBackground(new Color(255, 182, 193));
		contentPane.add(toolBar, BorderLayout.NORTH);

		tglBtnPoint = new JToggleButton("POINT");
		tglBtnPoint.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 13));
		tglBtnPoint.setBackground(new Color(255, 255, 255));
		toolBar.add(tglBtnPoint);
		tglBtnPoint.setFocusPainted(false);
		buttonGroup.add(tglBtnPoint);

		tglBtnLine = new JToggleButton("LINE");
		tglBtnLine.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 13));
		tglBtnLine.setBackground(new Color(255, 255, 255));
		toolBar.add(tglBtnLine);
		tglBtnLine.setFocusPainted(false);
		buttonGroup.add(tglBtnLine);

		tglBtnRectangle = new JToggleButton("RECTANGLE");
		tglBtnRectangle.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 13));
		tglBtnRectangle.setBackground(new Color(255, 255, 255));
		toolBar.add(tglBtnRectangle);
		tglBtnRectangle.setFocusPainted(false);
		buttonGroup.add(tglBtnRectangle);

		tglBtnCircle = new JToggleButton("CIRCLE");
		tglBtnCircle.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 13));
		tglBtnCircle.setBackground(new Color(255, 255, 255));
		toolBar.add(tglBtnCircle);
		tglBtnCircle.setFocusPainted(false);
		buttonGroup.add(tglBtnCircle);

		tglBtnDonut = new JToggleButton("DONUT");
		tglBtnDonut.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 13));
		tglBtnDonut.setBackground(new Color(255, 255, 255));
		toolBar.add(tglBtnDonut);
		tglBtnDonut.setFocusPainted(false);
		buttonGroup.add(tglBtnDonut);

		verticalStrut = Box.createVerticalStrut(20);
		toolBar.add(verticalStrut);

		btnBorderColor = new JButton("Border color");
		btnBorderColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				borderColor = JColorChooser.showDialog(btnBorderColor, "Select color", borderColor);
				btnBorderColor.setBackground(borderColor);
			}
		});
		btnBorderColor.setFocusPainted(false);
		toolBar.add(btnBorderColor);

		btnInnerColor = new JButton("Inner color");
		btnInnerColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				innerColor = JColorChooser.showDialog(btnInnerColor, "Select color", innerColor);
				btnInnerColor.setBackground(innerColor);
			}
		});
		btnInnerColor.setFocusPainted(false);
		toolBar.add(btnInnerColor);

		verticalStrut_2 = Box.createVerticalStrut(20);
		toolBar.add(verticalStrut_2);

		btnSelect = new JToggleButton("Select");
		btnSelect.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 13));
		btnSelect.setBackground(new Color(255, 255, 255));
		toolBar.add(btnSelect);
		btnSelect.setFocusPainted(false);
		buttonGroup.add(btnSelect);

		JButton btnDelete = new JButton("Delete");
		btnDelete.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 13));
		btnDelete.setBackground(new Color(255, 255, 255));
		toolBar.add(btnDelete);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delete();
				btnSelect.setSelected(false);
			}
		});
		btnDelete.setFocusPainted(false);

		JButton btnModify = new JButton("Modify");
		btnModify.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 13));
		btnModify.setBackground(new Color(255, 255, 255));
		toolBar.add(btnModify);
		btnModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (pnlDrawing.getSelected() == null) {
					JOptionPane.showMessageDialog(null, "Select what you want to modify!", "Message",
							JOptionPane.ERROR_MESSAGE);
					btnSelect.setSelected(true);

				} else {
					modify();
					pnlDrawing.getSelected().setSelected(false);
					pnlDrawing.setSelected(null);
					btnSelect.setSelected(false);
				}

			}
		});
		btnModify.setFocusPainted(false);
	}

	protected void delete() {
		Shape selected = pnlDrawing.getSelected();
		if (selected == null) {
			JOptionPane.showMessageDialog(null, "Select shape!", "Message", JOptionPane.WARNING_MESSAGE);
		} else {
			int selectedOpt = JOptionPane.showConfirmDialog(null, "Do you want to delete this shape?", "Message",
					JOptionPane.YES_NO_OPTION);
			if (selectedOpt == JOptionPane.YES_OPTION) {
				pnlDrawing.getShapes().remove(selected);
			}
		}
		pnlDrawing.setSelected(null);
		btnSelect.setSelected(false);
		pnlDrawing.repaint();
	}

	protected void modify() {
		Shape selected = pnlDrawing.getSelected();
		if (selected != null) {
			if (selected instanceof Point) {
				Point point = (Point) selected;
				DlgPoint dlgPoint = new DlgPoint();
				dlgPoint.setPoint(point);
				dlgPoint.setModal(true);
				dlgPoint.getTxtXCoordinate().setText(String.valueOf(point.getX()));
				dlgPoint.getTxtYCoordinate().setText(String.valueOf(point.getY()));
				dlgPoint.setVisible(true);
				if (dlgPoint.ok) {
					pnlDrawing.getShapes().remove(selected);
					pnlDrawing.getShapes().add(dlgPoint.getPoint());
					repaint();
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
				dlgLine.setVisible(true);
				if (dlgLine.ok) {
					pnlDrawing.getShapes().remove(selected);
					pnlDrawing.getShapes().add(dlgLine.getLine());
					repaint();
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
				dlgRectangle.setVisible(true);
				if (dlgRectangle.ok) {
					pnlDrawing.getShapes().remove(selected);
					pnlDrawing.getShapes().add(dlgRectangle.getRectangle());
					repaint();
				}

			} else if (selected instanceof Circle) {
				Circle circle = (Circle) selected;
				DlgCircle dlgCircle = new DlgCircle();
				dlgCircle.setCircle(circle);
				dlgCircle.setModal(true);
				dlgCircle.getTxtXCoordinate().setText(String.valueOf(circle.getCenter().getX()));
				dlgCircle.getTxtYCoordinate().setText(String.valueOf(circle.getCenter().getY()));
				dlgCircle.getTxtRadius().setText(String.valueOf(circle.getRadius()));
				dlgCircle.setVisible(true);
				if (dlgCircle.ok) {
					pnlDrawing.getShapes().remove(selected);
					pnlDrawing.getShapes().add(dlgCircle.getCircle());
					repaint();
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
				dlgDonut.setVisible(true);
				if (dlgDonut.ok) {
					pnlDrawing.getShapes().remove(selected);
					pnlDrawing.getShapes().add(dlgDonut.getDonut());
					repaint();
				}
			}
		}

	}

	public PnlDrawing getPnlDrawing() {
		return pnlDrawing;
	}

	public void setPnlDrawing(PnlDrawing pnlDrawing) {
		this.pnlDrawing = pnlDrawing;
	}

	public JToggleButton getTglBtnPoint() {
		return tglBtnPoint;
	}

	public void setTglBtnPoint(JToggleButton tglBtnPoint) {
		this.tglBtnPoint = tglBtnPoint;
	}

	public JToggleButton getTglBtnLine() {
		return tglBtnLine;
	}

	public void setTglBtnLine(JToggleButton tglBtnLine) {
		this.tglBtnLine = tglBtnLine;
	}

	public JToggleButton getTglBtnRectangle() {
		return tglBtnRectangle;
	}

	public void setTglBtnRectangle(JToggleButton tglBtnRectangle) {
		this.tglBtnRectangle = tglBtnRectangle;
	}

	public JToggleButton getTglBtnCircle() {
		return tglBtnCircle;
	}

	public void setTglBtnCircle(JToggleButton tglBtnCircle) {
		this.tglBtnCircle = tglBtnCircle;
	}

	public JToggleButton getTglBtnDonut() {
		return tglBtnDonut;
	}

	public void setTglBtnDonut(JToggleButton tglBtnDonut) {
		this.tglBtnDonut = tglBtnDonut;
	}

	public JToggleButton getBtnSelect() {
		return btnSelect;
	}

	public void setBtnSelect(JToggleButton btnSelect) {
		this.btnSelect = btnSelect;
	}

	public JToolBar getToolBar() {
		return toolBar;
	}

	public void setToolBar(JToolBar toolBar) {
		this.toolBar = toolBar;
	}

	public ButtonGroup getButtonGroup() {
		return buttonGroup;
	}

}
