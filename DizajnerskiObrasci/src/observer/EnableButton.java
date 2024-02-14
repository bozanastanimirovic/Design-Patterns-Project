package observer;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class EnableButton {
	private int selectedShapes;
	private int undoSize;
	private int redoSize;

	private PropertyChangeSupport propertyChangeSupport;

	public EnableButton() {
		propertyChangeSupport = new PropertyChangeSupport(this);
	}

	public void setAddedShape() {
		propertyChangeSupport.firePropertyChange("add", null, null);
	}

	public void setSelectedShape(int selectedShapes) {
		propertyChangeSupport.firePropertyChange("select", this.selectedShapes, selectedShapes);
		this.selectedShapes = selectedShapes;
	}

	public void setUndo(int undoSize) {
		propertyChangeSupport.firePropertyChange("undo", this.undoSize, undoSize);
		this.undoSize = undoSize;
	}

	public void setRedo(int redoSize) {
		propertyChangeSupport.firePropertyChange("redo", this.redoSize, redoSize);
		this.redoSize = redoSize;
	}

	public void addListener(PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(listener);
	}

	public void removeListener(PropertyChangeListener listener) {
		propertyChangeSupport.removePropertyChangeListener(listener);
	}
}
