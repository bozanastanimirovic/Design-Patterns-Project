package command;

import geometry.Shape;
import mvc.DrawingModel;

public class BringToFrontCmd implements Command{
	private DrawingModel model;
	private Shape selected;
    private int index;
    private int oldIndex;

    public BringToFrontCmd(DrawingModel model, Shape selected) {
        this.model = model;
        this.selected = selected;
        this.oldIndex = model.getShapes().indexOf(selected);
    }

    @Override
    public void execute() {
    	model.remove(selected);
    	model.add(selected);
    }

	@Override
	public void unexecute(){
		index = model.getShapes().indexOf(selected);
		if (index - 1 > 0) {
			index = oldIndex;
		} else {
			index = 0;
		}

		model.getShapes().remove(selected);
		model.getShapes().add(index, selected);
	}
}
