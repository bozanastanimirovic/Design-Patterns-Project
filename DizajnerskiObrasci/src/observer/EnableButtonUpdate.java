package observer;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import mvc.DrawingController;
import mvc.DrawingFrame;
import mvc.DrawingModel;

public class EnableButtonUpdate implements PropertyChangeListener {
	private DrawingFrame frame;
	private DrawingController controller;
	private DrawingModel model;
	//private DrawingModel model;

	private int shapes;
	private int selectedShapes;
	private int undoSize;
	private int redoSize;

	public EnableButtonUpdate(DrawingFrame frame, DrawingModel model, DrawingController controller) {
		this.frame = frame;
		this.controller = controller;
		this.model = model;
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals("add")) {
			this.shapes = (int) evt.getNewValue();
			numOfShapes();
			frame.getBtnRedo().setEnabled(false);
		} else if (evt.getPropertyName().equals("select")) {
			this.selectedShapes = (int) evt.getNewValue();
			numOfShapes();
			numOfSelectedShapes();
			getUndoSize();
			getRedoSize();

		} else if (evt.getPropertyName().equals("undo")) {
			this.undoSize = (int) evt.getNewValue();
			numOfShapes();
			numOfSelectedShapes();
			getUndoSize();
			getRedoSize();
		} else if (evt.getPropertyName().equals("redo")) {
			this.redoSize = (int) evt.getNewValue();
			numOfShapes();
			numOfSelectedShapes();
			getUndoSize();
			getRedoSize();
		}
	}
	
	private void numOfShapes() {
		if (shapes > 0) {
			frame.getBtnSelect().setEnabled(true);
			//frame.getBtnUndo().setEnabled(true);
			//frame.getBtnRedo().setEnabled(false);
			//frame.getBtnRedo().setEnabled(false);
		} else if (shapes == 0) {
			allDisabled();
		}
	}
	
	private void numOfSelectedShapes() {
		if (selectedShapes == 1) {
			int index = model.getShapes().indexOf(controller.getSelected());
			if (shapes == 1) {
				frame.getBtnModify().setEnabled(true);
				frame.getBtnDelete().setEnabled(true);
				frame.getBtnBringToFront().setEnabled(false);
				frame.getBtnToFront().setEnabled(false);
				frame.getBtnBringToBack().setEnabled(false);
				frame.getBtnToBack().setEnabled(false);
				
			} else if (index == 0) {
				frame.getBtnModify().setEnabled(true);
				frame.getBtnDelete().setEnabled(true);
				frame.getBtnBringToBack().setEnabled(false);
				frame.getBtnToBack().setEnabled(false);
				frame.getBtnBringToFront().setEnabled(true);
				frame.getBtnToFront().setEnabled(true);
			} else if (index == model.getShapes().size() - 1) {
				frame.getBtnModify().setEnabled(true);
				frame.getBtnDelete().setEnabled(true);
				frame.getBtnBringToFront().setEnabled(false);
				frame.getBtnToFront().setEnabled(false);
				frame.getBtnBringToBack().setEnabled(true);
				frame.getBtnToBack().setEnabled(true);
			}else {
				allEnabled();
			}
		} else if (selectedShapes > 1) {
			frame.getBtnModify().setEnabled(false);
			frame.getBtnDelete().setEnabled(true);
			frame.getBtnBringToFront().setEnabled(false);
			frame.getBtnToFront().setEnabled(false);
			frame.getBtnBringToBack().setEnabled(false);
			frame.getBtnToBack().setEnabled(false);
		} else if (selectedShapes == 0) {
			allDisabled();
		}
	}
	
	private void getUndoSize() {
		if (undoSize > 0) {
			//frame.getBtnSelect().setEnabled(true);
			frame.getBtnUndo().setEnabled(true);
			//frame.getBtnRedo().setEnabled(true);
			//allDisabled();
		} 
		if (undoSize == 0) {
			//frame.getBtnSelect().setEnabled(false);
			frame.getBtnUndo().setEnabled(false);
			//frame.getBtnRedo().setEnabled(true);
			//allDisabled();
		} 
	}
	
	private void getRedoSize() {
		if (redoSize > 0) {
			//frame.getBtnSelect().setEnabled(true);
			//frame.getBtnUndo().setEnabled(true);
			frame.getBtnRedo().setEnabled(true);
			//allDisabled();
		} 
		if (redoSize == 0) {
			frame.getBtnRedo().setEnabled(false);
		} 
	}
	
	private void allDisabled() {
		frame.getBtnModify().setEnabled(false);
		frame.getBtnDelete().setEnabled(false);
		frame.getBtnBringToFront().setEnabled(false);
		frame.getBtnToFront().setEnabled(false);
		frame.getBtnBringToBack().setEnabled(false);
		frame.getBtnToBack().setEnabled(false);
	}
	private void allEnabled() {
		frame.getBtnModify().setEnabled(true);
		frame.getBtnDelete().setEnabled(true);
		frame.getBtnBringToFront().setEnabled(true);
		frame.getBtnToFront().setEnabled(true);
		frame.getBtnBringToBack().setEnabled(true);
		frame.getBtnToBack().setEnabled(true);
	}

}
