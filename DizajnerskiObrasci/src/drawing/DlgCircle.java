package drawing;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import geometry.Circle;
import geometry.Point;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DlgCircle extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtXCoordinate;
	private JTextField txtYCoordinate;
	private JTextField txtRadius;
	private JButton btnBorderColor;
	private JButton btnInnerColor;
	private Circle circle;
	protected boolean ok;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgCircle dialog = new DlgCircle();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setLocationRelativeTo(null);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgCircle() {
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
			gbl_panel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
			gbl_panel.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
			gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
			panel.setLayout(gbl_panel);
			{
				JLabel lblCenter = new JLabel("CENTER");
				lblCenter.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 11));
				GridBagConstraints gbc_lblCenter = new GridBagConstraints();
				gbc_lblCenter.gridwidth = 2;
				gbc_lblCenter.insets = new Insets(0, 0, 5, 0);
				gbc_lblCenter.gridx = 0;
				gbc_lblCenter.gridy = 0;
				panel.add(lblCenter, gbc_lblCenter);
			}
			{
				JLabel lblXCoordinate = new JLabel("X coordinate");
				lblXCoordinate.setFont(new Font("Yu Gothic UI Semibold", Font.ITALIC, 11));
				GridBagConstraints gbc_lblXCoordinate = new GridBagConstraints();
				gbc_lblXCoordinate.anchor = GridBagConstraints.EAST;
				gbc_lblXCoordinate.insets = new Insets(0, 0, 5, 5);
				gbc_lblXCoordinate.gridx = 0;
				gbc_lblXCoordinate.gridy = 1;
				panel.add(lblXCoordinate, gbc_lblXCoordinate);
			}
			{
				txtXCoordinate = new JTextField();
				GridBagConstraints gbc_txtXCoordinate = new GridBagConstraints();
				gbc_txtXCoordinate.insets = new Insets(0, 0, 5, 0);
				gbc_txtXCoordinate.fill = GridBagConstraints.HORIZONTAL;
				gbc_txtXCoordinate.gridx = 1;
				gbc_txtXCoordinate.gridy = 1;
				panel.add(txtXCoordinate, gbc_txtXCoordinate);
				txtXCoordinate.setColumns(10);
			}
			{
				JLabel lblYCoordinate = new JLabel("Y coordinate");
				lblYCoordinate.setFont(new Font("Yu Gothic UI Semibold", Font.ITALIC, 11));
				GridBagConstraints gbc_lblYCoordinate = new GridBagConstraints();
				gbc_lblYCoordinate.anchor = GridBagConstraints.EAST;
				gbc_lblYCoordinate.insets = new Insets(0, 0, 5, 5);
				gbc_lblYCoordinate.gridx = 0;
				gbc_lblYCoordinate.gridy = 2;
				panel.add(lblYCoordinate, gbc_lblYCoordinate);
			}
			{
				txtYCoordinate = new JTextField();
				GridBagConstraints gbc_txtYCoordinate = new GridBagConstraints();
				gbc_txtYCoordinate.insets = new Insets(0, 0, 5, 0);
				gbc_txtYCoordinate.fill = GridBagConstraints.HORIZONTAL;
				gbc_txtYCoordinate.gridx = 1;
				gbc_txtYCoordinate.gridy = 2;
				panel.add(txtYCoordinate, gbc_txtYCoordinate);
				txtYCoordinate.setColumns(10);
			}
			{
				Component horizontalGlue = Box.createHorizontalGlue();
				GridBagConstraints gbc_horizontalGlue = new GridBagConstraints();
				gbc_horizontalGlue.insets = new Insets(0, 0, 5, 0);
				gbc_horizontalGlue.gridx = 1;
				gbc_horizontalGlue.gridy = 3;
				panel.add(horizontalGlue, gbc_horizontalGlue);
			}
			{
				Component horizontalStrut = Box.createHorizontalStrut(20);
				GridBagConstraints gbc_horizontalStrut = new GridBagConstraints();
				gbc_horizontalStrut.insets = new Insets(0, 0, 5, 0);
				gbc_horizontalStrut.gridx = 1;
				gbc_horizontalStrut.gridy = 4;
				panel.add(horizontalStrut, gbc_horizontalStrut);
			}
			{
				JLabel lblRadius = new JLabel("Radius");
				lblRadius.setFont(new Font("Yu Gothic UI Semibold", Font.ITALIC, 11));
				GridBagConstraints gbc_lblRadius = new GridBagConstraints();
				gbc_lblRadius.anchor = GridBagConstraints.EAST;
				gbc_lblRadius.insets = new Insets(0, 0, 5, 5);
				gbc_lblRadius.gridx = 0;
				gbc_lblRadius.gridy = 5;
				panel.add(lblRadius, gbc_lblRadius);
			}
			{
				txtRadius = new JTextField();
				GridBagConstraints gbc_txtRadius = new GridBagConstraints();
				gbc_txtRadius.insets = new Insets(0, 0, 5, 0);
				gbc_txtRadius.fill = GridBagConstraints.HORIZONTAL;
				gbc_txtRadius.gridx = 1;
				gbc_txtRadius.gridy = 5;
				panel.add(txtRadius, gbc_txtRadius);
				txtRadius.setColumns(10);
			}
			{
				btnBorderColor = new JButton("Border Color");
				btnBorderColor.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Color borderColor = JColorChooser.showDialog(null, "Choose border color",
								btnBorderColor.getBackground());
						if (borderColor != null) {
							btnBorderColor.setBackground(borderColor);
						}
					}
				});
				btnBorderColor.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 11));
				btnBorderColor.setBackground(new Color(255, 182, 193));
				GridBagConstraints gbc_btnBorderColor = new GridBagConstraints();
				gbc_btnBorderColor.insets = new Insets(0, 0, 5, 0);
				gbc_btnBorderColor.fill = GridBagConstraints.HORIZONTAL;
				gbc_btnBorderColor.gridwidth = 2;
				gbc_btnBorderColor.gridx = 0;
				gbc_btnBorderColor.gridy = 6;
				panel.add(btnBorderColor, gbc_btnBorderColor);
				btnBorderColor.setFocusPainted(false);
			}
			{
				btnInnerColor = new JButton("Inner Color");
				btnInnerColor.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Color innerColor = JColorChooser.showDialog(null, "Choose inner color",
								btnInnerColor.getBackground());
						if (innerColor != null) {
							btnInnerColor.setBackground(innerColor);
						}
					}
				});
				btnInnerColor.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 11));
				btnInnerColor.setBackground(new Color(255, 182, 193));
				GridBagConstraints gbc_btnInnerColor = new GridBagConstraints();
				gbc_btnInnerColor.fill = GridBagConstraints.HORIZONTAL;
				gbc_btnInnerColor.gridwidth = 2;
				gbc_btnInnerColor.gridx = 0;
				gbc_btnInnerColor.gridy = 7;
				panel.add(btnInnerColor, gbc_btnInnerColor);
				btnInnerColor.setFocusPainted(false);
			}
		}
		{
			JPanel panel = new JPanel();
			panel.setBackground(new Color(255, 182, 193));
			contentPanel.add(panel, BorderLayout.NORTH);
			{
				JLabel lblCircle = new JLabel("CIRCLE");
				lblCircle.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
				panel.add(lblCircle);
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
						if ((txtXCoordinate.getText().equals("")) || (txtYCoordinate.getText().equals(""))
								|| (txtRadius.getText().equals(""))) {
							JOptionPane.showMessageDialog(null, "Fill in all fields!", "Message",
									JOptionPane.ERROR_MESSAGE);
						} else {
							try {
								int x = Integer.parseInt(getTxtXCoordinate().getText());
								int y = Integer.parseInt(getTxtYCoordinate().getText());
								int radius = Integer.parseInt(getTxtRadius().getText());

								if ((x < 0) || (y < 0)) {
									JOptionPane.showMessageDialog(null, "Enter positive coordinates!", "Message",
											JOptionPane.ERROR_MESSAGE);
								} else if ((radius < 0)) {
									JOptionPane.showMessageDialog(null, "Radius can't be a negative number!", "Message",
											JOptionPane.ERROR_MESSAGE);
								} else {
									circle = new Circle(new Point(x, y), radius, false, btnBorderColor.getBackground(),
											btnInnerColor.getBackground());
									ok = true;
									setVisible(false);

								}

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

	public JTextField getTxtXCoordinate() {
		return txtXCoordinate;
	}

	public void setTxtXCoordinate(JTextField txtXCoordinate) {
		this.txtXCoordinate = txtXCoordinate;
	}

	public JTextField getTxtYCoordinate() {
		return txtYCoordinate;
	}

	public void setTxtYCoordinate(JTextField txtYCoordinate) {
		this.txtYCoordinate = txtYCoordinate;
	}

	public JTextField getTxtRadius() {
		return txtRadius;
	}

	public void setTxtRadius(JTextField txtRadius) {
		this.txtRadius = txtRadius;
	}

	public Circle getCircle() {
		return circle;
	}

	public void setCircle(Circle circle) {
		this.circle = circle;
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

}
