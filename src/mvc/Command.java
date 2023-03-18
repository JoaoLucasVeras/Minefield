package mvc;

import java.awt.event.ActionEvent;

public abstract class Command {

	private Model model;
	
	public Command(Model model) {
		// TODO Auto-generated constructor stub
		this.model = model;
	}
	
	public abstract void execute();
}
