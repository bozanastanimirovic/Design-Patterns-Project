package drawing;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import geometry.Line;
import geometry.Point;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.UIManager;
import java.awt.SystemColor;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Component;
import javax.swing.Box;

public class DlgLine extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtStartX;
	private JTextField txtStartY;
	private JTextField txtEndX;
	private JTextField txtEndY;
	private JButton btnColor;
	private Line line;
	protected boolean ok;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgLine dialog = new DlgLine();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgLine() {
		setModal(true);
		setBackground(new Color(255, 182, 193));
		setTitle("Stanimirovic Bozana IT 32/2021 ");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 182, 193));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setBackground(new Color(255, 228, 225));
			contentPanel.add(panel, BorderLayout.CENTER);
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[] { 0, 0, 0 };
			gbl_panel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
			gbl_panel.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
			gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
			panel.setLayout(gbl_panel);
			{
				JLabel lblStartPoint = new JLabel("START POINT");
				lblStartPoint.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 11));
				GridBagConstraints gbc_lblStartPoint = new GridBagConstraints();
				gbc_lblStartPoint.gridwidth = 2;
				gbc_lblStartPoint.insets = new Insets(0, 0, 5, 0);
				gbc_lblStartPoint.gridx = 0;
				gbc_lblStartPoint.gridy = 0;
				panel.add(lblStartPoint, gbc_lblStartPoint);
			}
			{
				JLabel lblStartPointX = new JLabel("X coordinate");
				lblStartPointX.setFont(new Font("Yu Gothic UI Semibold", Font.ITALIC, 11));
				GridBagConstraints gbc_lblStartPointX = new GridBagConstraints();
				gbc_lblStartPointX.insets = new Insets(0, 0, 5, 5);
				gbc_lblStartPointX.anchor = GridBagConstraints.EAST;
				gbc_lblStartPointX.gridx = 0;
				gbc_lblStartPointX.gridy = 1;
				panel.add(lblStartPointX, gbc_lblStartPointX);
			}
			{
				txtStartX = new JTextField();
				GridBagConstraints gbc_txtStartX = new GridBagConstraints();
				gbc_txtStartX.insets = new Insets(0, 0, 5, 0);
				gbc_txtStartX.fill = GridBagConstraints.HORIZONTAL;
				gbc_txtStartX.gridx = 1;
				gbc_txtStartX.gridy = 1;
				panel.add(txtStartX, gbc_txtStartX);
				txtStartX.setColumns(10);
			}
			{
				JLabel lblStartPointY = new JLabel("Y coordinate");
				lblStartPointY.setFont(new Font("Yu Gothic UI Semibold", Font.ITALIC, 11));
				GridBagConstraints gbc_lblStartPointY = new GridBagConstraints();
				gbc_lblStartPointY.anchor = GridBagConstraints.EAST;
				gbc_lblStartPointY.insets = new Insets(0, 0, 5, 5);
				gbc_lblStartPointY.gridx = 0;
				gbc_lblStartPointY.gridy = 2;
				panel.add(lblStartPointY, gbc_lblStartPointY);
			}
			{
				txtStartY = new JTextField();
				GridBagConstraints gbc_txtStartY = new GridBagConstraints();
				gbc_txtStartY.insets = new Insets(0, 0, 5, 0);
				gbc_txtStartY.fill = GridBagConstraints.HORIZONTAL;
				gbc_txtStartY.gridx = 1;
				gbc_txtStartY.gridy = 2;
				panel.add(txtStartY, gbc_txtStartY);
				txtStartY.setColumns(10);
			}
			{
				JLabel lblEndPoint = new JLabel("END POINT");
				lblEndPoint.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 11));
				GridBagConstraints gbc_lblEndPoint = new GridBagConstraints();
				gbc_lblEndPoint.insets = new Insets(0, 0, 5, 0);
				gbc_lblEndPoint.gridwidth = 2;
				gbc_lblEndPoint.gridx = 0;
				gbc_lblEndPoint.gridy = 3;
				panel.add(lblEndPoint, gbc_lblEndPoint);
			}
			{
				JLabel lblEndPointX = new JLabel("X coordinate");
				lblEndPointX.setFont(new Font("Yu Gothic UI Semibold", Font.ITALIC, 11));
				GridBagConstraints gbc_lblEndPointX = new GridBagConstraints();
				gbc_lblEndPointX.anchor = GridBagConstraints.EAST;
				gbc_lblEndPointX.insets = new Insets(0, 0, 5, 5);
				gbc_lblEndPointX.gridx = 0;
				gbc_lblEndPointX.gridy = 4;
				panel.add(lblEndPointX, gbc_lblEndPointX);
			}
			{
				txtEndX = new JTextField();
				GridBagConstraints gbc_txtEndX = new GridBagConstraints();
				gbc_txtEndX.insets = new Insets(0, 0, 5, 0);
				gbc_txtEndX.fill = GridBagConstraints.HORIZONTAL;
				gbc_txtEndX.gridx = 1;
				gbc_txtEndX.gridy = 4;
				panel.add(txtEndX, gbc_txtEndX);
				txtEndX.setColumns(10);
			}
			{
				JLabel lblEndPointY = new JLabel("Y coordinate");
				lblEndPointY.setFont(new Font("Yu Gothic UI Semibold", Font.ITALIC, 11));
				GridBagConstraints gbc_lblEndPointY = new GridBagConstraints();
				gbc_lblEndPointY.anchor = GridBagConstraints.EAST;
				gbc_lblEndPointY.insets = new Insets(0, 0, 5, 5);
				gbc_lblEndPointY.gridx = 0;
				gbc_lblEndPointY.gridy = 5;
				panel.add(lblEndPointY, gbc_lblEndPointY);
			}
			{
				txtEndY = new JTextField();
				GridBagConstraints gbc_txtEndY = new GridBagConstraints();
				gbc_txtEndY.insets = new Insets(0, 0, 5, 0);
				gbc_txtEndY.fill = GridBagConstraints.HORIZONTAL;
				gbc_txtEndY.gridx = 1;
				gbc_txtEndY.gridy = 5;
				panel.add(txtEndY, gbc_txtEndY);
				txtEndY.setColumns(10);
			}
			{
				btnColor = new JButton("Color");
				btnColor.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Color borderColor = JColorChooser.showDialog(null, "Choose border color",
								btnColor.getBackground());
						if (borderColor != null) {
							btnColor.setBackground(borderColor);
						}
					}

				});
				{
					Component horizontalStrut = Box.createHorizontalStrut(20);
					GridBagConstraints gbc_horizontalStrut = new GridBagConstraints();
					gbc_horizontalStrut.insets = new Insets(0, 0, 5, 0);
					gbc_horizontalStrut.gridx = 1;
					gbc_horizontalStrut.gridy = 7;
					panel.add(horizontalStrut, gbc_horizontalStrut);
				}
				btnColor.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 11));
				btnColor.setBackground(new Color(255, 182, 193));
				GridBagConstraints gbc_btnColor = new GridBagConstraints();
				gbc_btnColor.fill = GridBagConstraints.HORIZONTAL;
				gbc_btnColor.gridwidth = 2;
				gbc_btnColor.gridx = 0;
				gbc_btnColor.gridy = 8;
				panel.add(btnColor, gbc_btnColor);
				btnColor.setFocusPainted(false);
			}
		}
		{
			JPanel panel = new JPanel();
			panel.setBackground(new Color(255, 182, 193));
			contentPanel.add(panel, BorderLayout.NORTH);
			{
				JLabel lblLine = new JLabel("LINE");
				lblLine.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
				panel.add(lblLine);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(255, 182, 193));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if ((txtStartX.getText().equals("")) || (txtStartY.getText().equals(""))
								|| (txtEndX.getText().equals("")) || (txtEndY.getText().equals(""))) {
							JOptionPane.showMessageDialog(null, "Fill in all fields!", "Message",
									JOptionPane.ERROR_MESSAGE);
						} else {
							try {
								int x = Integer.parseInt(getTxtStartX().getText());
								int y = Integer.parseInt(getTxtStartY().getText());
								int x2 = Integer.parseInt(getTxtEndX().getText());
								int y2 = Integer.parseInt(getTxtEndY().getText());

								if ((x < 0) || (y < 0) || (x2 < 0) || (y2 < 0)) {
									JOptionPane.showMessageDialog(null, "Enter positive coordinates!", "Message",
											JOptionPane.ERROR_MESSAGE);
								} else {
									line = new Line(new Point(x, y), new Point(x2, y2), false,
											btnColor.getBackground());
								}
								ok = true;
								setVisible(false);
							} catch (NumberFormatException nfe) {
								JOptionPane.showMessageDialog(null, "Enter numbers!", "Message",
										JOptionPane.ERROR_MESSAGE);
							}
						}
					}
				});
				okButton.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
				okButton.setBackground(new Color(255, 255, 255));
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				okButton.setFocusPainted(false);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
					}
				});
				cancelButton.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
				cancelButton.setBackground(new Color(255, 255, 255));
				cancelButton.setActionCommand("Cancel");
				cancelButton.setFocusPainted(false);
				buttonPane.add(cancelButton);
			}
		}
	}

	public JTextField getTxtStartX() {
		return txtStartX;
	}

	public void setTxtStartX(JTextField txtStartX) {
		this.txtStartX = txtStartX;
	}

	public JTextField getTxtStartY() {
		return txtStartY;
	}

	public void setTxtStartY(JTextField txtStartY) {
		this.txtStartY = txtStartY;
	}

	public JTextField getTxtEndX() {
		return txtEndX;
	}

	public void setTxtEndX(JTextField txtEndX) {
		this.txtEndX = txtEndX;
	}

	public JTextField getTxtEndY() {
		return txtEndY;
	}

	public void setTxtEndY(JTextField txtEndY) {
		this.txtEndY = txtEndY;
	}

	public boolean isOk() {
		return ok;
	}

	public void setOk(boolean ok) {
		this.ok = ok;
	}

	public JPanel getContentPanel() {
		return contentPanel;
	}

	public Line getLine() {
		return line;
	}

	public void setLine(Line line) {
		this.line = line;
	}

	public JButton getBtnColor() {
		return btnColor;
	}

	public void setBtnColor(JButton btnColor) {
		this.btnColor = btnColor;
	}
	

}
