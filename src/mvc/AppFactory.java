package mvc;
/**
 * An interface for customizers to implement. Allows for customizers to 
 * connect to the mvc by creating models and view. Also passes information
 * required for the frame.
 * @author Joao Lucas Veras
 * @author William Tran
 * @version %I%, %G%
 */
public interface AppFactory {

	
	public Model makeModel(); //makes a new model
	
	public View makeView(Model model); //makes a new view using the model
	
	public String getTitle(); //gets the title of the customized package
	
	public abstract String[] getHelp(); //list of help
	
	public String about(); //"about" information
	
	public abstract String[] getEditCommands(); //list of commands for "edit"

	public Command makeEditCommand(Model model, String cmmd, Object source); //used to make commands of the edit list
}
