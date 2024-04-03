package command;

import geometry.Shape;
import mvc.DrawingModel;

public class ToBackCmd implements Command {
	private DrawingModel model;
	private Shape selected;
	private int index;
	private int newIndex;
	//private int startIndex;

	public ToBackCmd(DrawingModel model, Shape selected) {
		this.model = model;
		this.selected = selected;
		//this.index = model.getShapes().indexOf(selected);
		//this.startIndex = model.getShapes().indexOf(selected);
	}

	@Override
	public void execute() {
		index = model.getShapes().indexOf(selected);
		if (index - 1 > 0) {
			newIndex = index - 1;
		} else {
			newIndex = 0;
		}

		if (index != newIndex) {
			model.getShapes().remove(selected);
			model.getShapes().add(newIndex, selected);
		}

	}

	@Override
	public void unexecute() {
		index = model.getShapes().indexOf(selected);
		// if (index + 1 > startIndex) {
		//return;
		// } else {
		if (index + 1 < model.getShapes().size() - 1) {
			newIndex = index + 1;
		} else {
			newIndex = model.getShapes().size() - 1;
		}

		if (index != newIndex) {
			model.getShapes().remove(selected);
			model.getShapes().add(newIndex, selected);
		}
		// }
	}

}
