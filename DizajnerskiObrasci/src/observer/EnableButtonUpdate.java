package observer;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import mvc.DrawingFrame;
import mvc.DrawingModel;

public class EnableButtonUpdate implements PropertyChangeListener {
	private DrawingFrame frame;
	//private DrawingModel model;

	private int shapes;
	private int selectedShapes;
	private int undoSize;
	private int redoSize;

	public EnableButtonUpdate(DrawingFrame frame, DrawingModel model) {
		this.frame = frame;
		//this.model = model;
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals("add")) {
			this.shapes = (int) evt.getNewValue();
			numOfShapes();
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
			frame.getBtnUndo().setEnabled(true);
			frame.getBtnRedo().setEnabled(false);
			//frame.getBtnRedo().setEnabled(false);
		}
	}
	
	private void numOfSelectedShapes() {
		if (selectedShapes == 1) {
			allEnabled();
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
			frame.getBtnSelect().setEnabled(true);
			frame.getBtnUndo().setEnabled(true);
			//frame.getBtnRedo().setEnabled(true);
			//allDisabled();
		} 
		if (undoSize == 0) {
			frame.getBtnSelect().setEnabled(false);
			frame.getBtnUndo().setEnabled(false);
			//frame.getBtnRedo().setEnabled(true);
			//allDisabled();
		} 
	}
	
	private void getRedoSize() {
		if (redoSize > 0) {
			frame.getBtnSelect().setEnabled(true);
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
