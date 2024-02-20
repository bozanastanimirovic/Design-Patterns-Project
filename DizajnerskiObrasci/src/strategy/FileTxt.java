package strategy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

import command.RemoveShapeCmd;
import mvc.DrawingFrame;
import mvc.DrawingModel;

public class FileTxt implements FileStrategy {
	DrawingFrame frame;
	DrawingModel model;

	public FileTxt(DrawingFrame frame, DrawingModel model) {
		this.frame = frame;
		this.model = model;
	}

	@Override
	public void save(String file) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
			for (int i = 0; i < frame.getListModel().size(); i++) {
				writer.write(frame.getListModel().getElementAt(i));
				writer.newLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void open(String file) {
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			String line;
			while ((line = reader.readLine()) != null) {
				int opt = JOptionPane.showConfirmDialog(null, "Do you want to insert " + line + "?", "Message",
						JOptionPane.YES_NO_OPTION);
				if (opt == JOptionPane.YES_OPTION) {
					((DefaultListModel<String>) frame.getListModel()).addElement(line);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
