package command;

import geometry.Shape;
import mvc.DrawingModel;

public class RemoveShapeCmd implements Command {

	private Shape shape;
	private DrawingModel model;
	int index;
	
	public RemoveShapeCmd(Shape shape, DrawingModel model) {
		this.shape = shape;
		this.model = model;
	}

	@Override
	public void execute() {
		index = model.getShapes().indexOf(shape);
		model.remove(shape);
	}

	@Override
	public void unexecute() {
		model.getShapes().add(index,shape);
		//model.add(shape);
	}

}
