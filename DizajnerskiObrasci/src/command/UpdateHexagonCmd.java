package command;

import adapter.HexagonAdapter;

public class UpdateHexagonCmd {
	private HexagonAdapter hexagon;
	private HexagonAdapter newState;
	private HexagonAdapter original = new HexagonAdapter();
	
	public UpdateHexagonCmd(HexagonAdapter hexagon, HexagonAdapter newState) {
		super();
		this.hexagon = hexagon;
		this.newState = newState;
	}

	public void execute() {
		original.setX(hexagon.getX());
		original.setY(hexagon.getY());
		original.setR(hexagon.getR());
		original.setColor(hexagon.getColor());
		original.setInnerColor(hexagon.getInnerColor());
		
		hexagon.setX(newState.getX());
		hexagon.setY(newState.getY());
		hexagon.setR(newState.getR());
		hexagon.setColor(newState.getColor());
		hexagon.setInnerColor(newState.getInnerColor());
	}

	public void unexecute() {
		hexagon.setX(original.getX());
		hexagon.setY(original.getY());
		hexagon.setR(original.getR());
		hexagon.setColor(original.getColor());
		hexagon.setInnerColor(original.getInnerColor());
	}
}
