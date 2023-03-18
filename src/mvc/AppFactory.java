package mvc;

public interface AppFactory {

	public Model makeModel();
	
	public View makeView(Model model);
	
	public String getTitle();
	
	public abstract String[] getHelp();
	
	public String about();
	
	public abstract String[] getEditCommands();

	public Command makeEditCommand(Model model, String cmmd, Object source);
}
