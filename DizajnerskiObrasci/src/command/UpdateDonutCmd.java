package command;

import geometry.Donut;

public class UpdateDonutCmd implements Command {

	private Donut donut;
	private Donut newState;
	private Donut original = new Donut();
	
	public UpdateDonutCmd(Donut donut, Donut newShape) {
		super();
		this.donut = donut;
		this.newState = newShape;
	}

	@Override
	public void execute() throws Exception {
		original.getCenter().setX(donut.getCenter().getX());
		original.getCenter().setY(donut.getCenter().getY());
		original.setRadius(donut.getRadius());
		original.setInnerRadius(donut.getInnerRadius());
		original.setColor(donut.getColor());
		original.setInnerColor(donut.getInnerColor());
		
		donut.getCenter().setX(newState.getCenter().getX());
		donut.getCenter().setY(newState.getCenter().getY());
		donut.setRadius(newState.getRadius());
		donut.setInnerRadius(newState.getInnerRadius());
		donut.setColor(newState.getColor());
		donut.setInnerColor(newState.getInnerColor());
	}

	@Override
	public void unexecute() throws Exception {
		donut.getCenter().setX(original.getCenter().getX());
		donut.getCenter().setY(original.getCenter().getY());
		donut.setRadius(original.getRadius());
		donut.setInnerRadius(original.getInnerRadius());
		donut.setColor(original.getColor());
		donut.setInnerColor(original.getInnerColor());
	}

}
