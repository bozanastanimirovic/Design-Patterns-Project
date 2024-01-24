package command;

import geometry.Shape;
import mvc.DrawingModel;

public class BringToBackCmd implements Command{
	private DrawingModel model;
	private Shape selected;
    private int index;
    private int oldIndex;

    public BringToBackCmd(DrawingModel model, Shape selected) {
        this.model = model;
        this.selected = selected;
        this.oldIndex = model.getShapes().indexOf(selected);
    }

    @Override
    public void execute() {
        model.remove(selected);
        model.getShapes().add(0, selected);
    }

	@Override
	public void unexecute(){
		index = model.getShapes().indexOf(selected);
		if (index + 1 < model.getShapes().size() - 1) {
			index = oldIndex;
		} else {
			index = model.getShapes().size() - 1;
		}

		model.getShapes().remove(selected);
		model.getShapes().add(index, selected);
	}
}
