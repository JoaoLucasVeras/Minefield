package minefield;

import mvc.Command;
import mvc.Model;

public class MineFieldCommands extends Command{

	Heading heading;
	public MineFieldCommands(Model model) {
		super(model);
		heading = null;
	}

	@Override
	public void execute() throws Exception {
		MineField minefield = new MineField;
		minefield.move(heading);
		//TO-DO throw exceptions for off screen, stepping on mine, reaching goal, moving after goal
	}

}
