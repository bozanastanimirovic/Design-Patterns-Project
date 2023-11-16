package command;

public interface Command {
	public void execute() throws Exception;
	public void unexecute() throws Exception;
}
