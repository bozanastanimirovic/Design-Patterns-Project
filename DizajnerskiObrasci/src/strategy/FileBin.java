package strategy;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

import geometry.Shape;
import mvc.DrawingFrame;
import mvc.DrawingModel;

public class FileBin implements FileStrategy{
	DrawingFrame frame;
	DrawingModel model;
	

	public FileBin(DrawingFrame frame, DrawingModel model) {
		this.frame = frame;
		this.model = model;
	}
	
	@Override
	public void save(String file) {
		try (ObjectOutputStream obs = new ObjectOutputStream(new FileOutputStream(file))) {
			obs.writeObject(model.getShapes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void open(String file) {
		try (ObjectInputStream reader = new ObjectInputStream(new FileInputStream(file))) {
			 ArrayList<Shape> shapes = ( ArrayList<Shape>) reader.readObject();

            for (Object s : shapes) {
                Shape shape = (Shape) s;
                //frame.getListModel().addElement(shape.toString());
                model.add(shape);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
	}

}
