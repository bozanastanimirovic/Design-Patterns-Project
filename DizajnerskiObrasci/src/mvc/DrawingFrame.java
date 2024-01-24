package mvc;

import javax.swing.AbstractButton;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.border.EmptyBorder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.GridBagLayout;
import javax.swing.JList;

public class DrawingFrame extends JFrame {

	private final ButtonGroup buttonGroup = new ButtonGroup();
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
	private Color borderColor = Color.BLACK;
	private Color innerColor = Color.WHITE;

	DrawingView view = new DrawingView();
	DrawingController controller;
	private JToggleButton tglbtnHexagon;
	private JToolBar toolBar2;
	private JButton btnUndo;
	private JButton btnRedo;
	private JScrollPane scrollPane;
	private JList<String> list;
	private DefaultListModel<String> listModel;
	private JButton btnToFront;
	private JButton btnToBack;
	private JButton btnBringToFront;
	private JButton btnBringToBack;

	public DrawingFrame() {
		view.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.mouseClicked(e);
			}
		});
		// getContentPane().add(view, BorderLayout.CENTER);

		//this.setSize(1000, 700);
		setLocationRelativeTo(null);
		setVisible(true);
		setBounds(100, 100, 772, 472);

		setTitle("Stanimirovic Bozana IT 32/2021 ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout(0, 0));
		view.setBackground(Color.WHITE);
		view.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		getContentPane().setPreferredSize(new Dimension(1200, 700));

        pack();
		
		JSplitPane splitPane = new JSplitPane();
		getContentPane().add(splitPane, BorderLayout.CENTER);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		splitPane.setLeftComponent(scrollPane_2);
		
		listModel = new DefaultListModel<>();
		list = new JList<>(listModel);
		scrollPane_2.setViewportView(list);
		
		splitPane.setRightComponent(view);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setBackground(new Color(255, 182, 193));
		getContentPane().add(toolBar, BorderLayout.NORTH);
		
		JToolBar toolBar2 = new JToolBar();
		toolBar2.setBackground(new Color(255, 182, 193));
		getContentPane().add(toolBar2, BorderLayout.SOUTH);
		
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
		
		tglbtnHexagon = new JToggleButton("HEXAGON");
		tglbtnHexagon.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 13));
		tglbtnHexagon.setBackground(new Color(255, 255, 255));
		toolBar.add(tglbtnHexagon);
		tglbtnHexagon.setFocusPainted(false);
		buttonGroup.add(tglbtnHexagon);

		verticalStrut = Box.createVerticalStrut(20);
		toolBar.add(verticalStrut);

		btnBorderColor = new JButton("Border color");
		btnBorderColor.setBackground(borderColor);
		btnBorderColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				borderColor = JColorChooser.showDialog(btnBorderColor, "Select color", borderColor);
				btnBorderColor.setBackground(borderColor);
			}
		});
		btnBorderColor.setFocusPainted(false);
		toolBar.add(btnBorderColor);

		btnInnerColor = new JButton("Inner color");
		btnInnerColor.setBackground(innerColor);
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
				if (controller.getSelected() == null) {
					JOptionPane.showMessageDialog(null, "Select shape!", "Message", JOptionPane.WARNING_MESSAGE);
					btnSelect.setSelected(true);
				} else {
					controller.delete();
					btnSelect.setSelected(false);
				}
			}
		});
		btnDelete.setFocusPainted(false);

		JButton btnModify = new JButton("Modify");
		btnModify.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 13));
		btnModify.setBackground(new Color(255, 255, 255));
		toolBar.add(btnModify);
		btnModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (controller.getSelected() == null) {
					JOptionPane.showMessageDialog(null, "Select what you want to modify!", "Message",
							JOptionPane.ERROR_MESSAGE);
					btnSelect.setSelected(true);

				} else {
					try {
						controller.modify();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					controller.getSelected().setSelected(false);
					controller.setSelected(null);
					btnSelect.setSelected(false);
				}

			}
		});
		btnModify.setFocusPainted(false);

		btnUndo = new JButton("Undo");
		btnUndo.setEnabled(false);
		btnUndo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				try {
					controller.undo();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnUndo.setBackground(new Color(255, 255, 255));
		toolBar2.add(btnUndo);
		
		btnRedo = new JButton("Redo");
		btnRedo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					controller.redo();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnRedo.setBackground(new Color(255, 255, 255));
		toolBar2.add(btnRedo);
		
		btnToFront = new JButton("To Front");
		btnToFront.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.toFront();
			}
		});
		toolBar2.add(btnToFront);
		
		btnToBack = new JButton("To Back");
		btnToBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.toBack();
			}
		});
		toolBar2.add(btnToBack);
		
		btnBringToFront = new JButton("Bring To Front");
		btnBringToFront.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.bringToFront();
			}
		});
		toolBar2.add(btnBringToFront);
		
		btnBringToBack = new JButton("Bring To Back");
		btnBringToBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.bringToBack();
			}
		});
		toolBar2.add(btnBringToBack);
		
	}
	
	/*btnUndo = new JButton("Undo");
	btnUndo.setEnabled(false);
	btnUndo.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e){
			try {
				controller.undo();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	});
	btnUndo.setBackground(new Color(255, 255, 255));
	toolBar_1.add(btnUndo);
	
	btnRedo = new JButton("Redo");
	btnRedo.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			try {
				controller.redo();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	});
	btnRedo.setBackground(new Color(255, 255, 255));
	toolBar_1.add(btnRedo);*/

	public DrawingView getView() {
		return view;
	}

	public void setController(DrawingController controller) {
		this.controller = controller;
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

	public JToggleButton getTglBtnHexagon() {
		return tglbtnHexagon;
	}

	public void setTglBtnHexagon(JToggleButton tglbtnHexagon) {
		this.tglbtnHexagon = tglbtnHexagon;
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

	public Component getVerticalStrut_2() {
		return verticalStrut_2;
	}

	public void setVerticalStrut_2(Component verticalStrut_2) {
		this.verticalStrut_2 = verticalStrut_2;
	}

	public JButton getBtnBorderColor() {
		return btnBorderColor;
	}

	public void setBtnBorderColor(JButton btnBorderColor) {
		this.btnBorderColor = btnBorderColor;
	}

	public JButton getBtnInnerColor() {
		return btnInnerColor;
	}

	public void setBtnInnerColor(JButton btnInnerColor) {
		this.btnInnerColor = btnInnerColor;
	}

	public Component getVerticalStrut() {
		return verticalStrut;
	}

	public void setVerticalStrut(Component verticalStrut) {
		this.verticalStrut = verticalStrut;
	}

	public Color getBorderColor() {
		return borderColor;
	}

	public void setBorderColor(Color borderColor) {
		this.borderColor = borderColor;
	}

	public Color getInnerColor() {
		return innerColor;
	}

	public void setInnerColor(Color innerColor) {
		this.innerColor = innerColor;
	}
	

	public JButton getBtnUndo() {
		return btnUndo;
	}

	public void setBtnUndo(JButton btnUndo) {
		this.btnUndo = btnUndo;
	}

	public JButton getBtnRedo() {
		return btnRedo;
	}

	public void setBtnRedo(JButton btnRedo) {
		this.btnRedo = btnRedo;
	}

	public ButtonGroup getButtonGroup() {
		return buttonGroup;
	}

	public JList getList() {
		return list;
	}

	public void setList(JList list) {
		this.list = list;
	}

	public DefaultListModel<String> getListModel() {
		return listModel;
	}

	public void setListModel(DefaultListModel<String> listModel) {
		this.listModel = listModel;
	}

	


	
}
