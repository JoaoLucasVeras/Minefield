package mvc;

/**
 * An abstract class for models to create commands as objects. Allows
 * AppPanel to more easily compare edit commands without needing to know
 * every command.
 * @author Joao Lucas Veras
 * @author William Tran
 * @version %I%, %G%
 */
public abstract class Command {

	protected Model model;

	/**
	 * Constructor class for Command. Connects the command to a specific model.
	 * @param model the model this command is connected to.
	 */
	public Command(Model model) {
		this.model = model;
	}

	/**
	 * Executes a related command that exists in Model. Also passes on any
	 * relevant information to the model. Throws an exception if the executed
	 * command throws an exception.
	 * @throws Exception If an Exception was thrown by the executed command.
	 */
	public abstract void execute() throws Exception;
}
