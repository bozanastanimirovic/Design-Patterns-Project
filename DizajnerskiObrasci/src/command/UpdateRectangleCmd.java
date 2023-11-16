package command;

import geometry.Rectangle;

public class UpdateRectangleCmd implements Command {

	private Rectangle rectangle;
	private Rectangle newState;
	private Rectangle original = new Rectangle();
	
	public UpdateRectangleCmd(Rectangle rectangle, Rectangle newState) {
		super();
		this.rectangle = rectangle;
		this.newState = newState;
	}

	@Override
	public void execute() {
		original.getUpperLeftPoint().setX(rectangle.getUpperLeftPoint().getX());
		original.getUpperLeftPoint().setY(rectangle.getUpperLeftPoint().getY());
		original.setWidth(rectangle.getWidth());
		original.setHeight(rectangle.getHeight());
		original.setColor(rectangle.getColor());
		original.setInnerColor(rectangle.getInnerColor());

		rectangle.getUpperLeftPoint().setX(newState.getUpperLeftPoint().getX());
		rectangle.getUpperLeftPoint().setY(newState.getUpperLeftPoint().getY());
		rectangle.setWidth(newState.getWidth());
		rectangle.setHeight(newState.getHeight());
		rectangle.setColor(newState.getColor());
		rectangle.setInnerColor(newState.getInnerColor());
	}

	@Override
	public void unexecute() {
		rectangle.getUpperLeftPoint().setX(original.getUpperLeftPoint().getX());
		rectangle.getUpperLeftPoint().setY(original.getUpperLeftPoint().getY());
		rectangle.setWidth(original.getWidth());
		rectangle.setHeight(original.getHeight());
		rectangle.setColor(original.getColor());
		rectangle.setInnerColor(original.getInnerColor());
	}

}
