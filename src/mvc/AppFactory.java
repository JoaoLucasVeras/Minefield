package mvc;

public interface AppFactory {

	public Model makeModel();
	
	public View makeView();
	
	public String getTitle();
	
	public abstract String[] getHelp();
	
	public String about();
	
	public abstract String[] getEditCommands();
	
	public Command makeEditCommands(String name);
}
