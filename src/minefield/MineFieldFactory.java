package minefield;

import mvc.AppFactory;
import mvc.Command;
import mvc.Model;
import mvc.View;

public class MineFieldFactory implements AppFactory{

	@Override
	public Model makeModel() {
		return new MineField();
	}

	@Override
	public View makeView(Model model) {
		return new MineFieldView((MineField)model);
	}

	@Override
	public String getTitle() {
		return "MineField";
	}

	@Override
	public String[] getHelp() {
		return new String[] {
				"N: Move north",
				"NE: Move northeast",
				"E: Move east",
				"SE: Move southeast",
				"S: Move south",
				"SW: Move southwest",
				"W: Move west",
				"NW: Move northwest",
		};
	}

	@Override
	public String about() {
		return "MineField. Copyright 2023 by Joao Lucas Veras, William Tran";
	}

	@Override
	public String[] getEditCommands() {
		return new String[] {
				"N",
				"NE",
				"E",
				"SE",
				"S",
				"SW",
				"W",
				"NW",
		};
	}

	@Override
	public Command makeEditCommand(Model model, String cmmd, Object source) {
		Command command = null;
		switch (cmmd) {
			case "N":
				command = new MineFieldCommands(model);
				((MineFieldCommands)command).heading = Heading.N;
				break;
			case "NE":
				command = new MineFieldCommands(model);
				((MineFieldCommands)command).heading = Heading.NE;
				break;
			case "E":
				command = new MineFieldCommands(model);
				((MineFieldCommands)command).heading = Heading.E;
				break;
			case "SE":
				command = new MineFieldCommands(model);
				((MineFieldCommands)command).heading = Heading.SE;
				break;
			case "S":
				command = new MineFieldCommands(model);
				((MineFieldCommands)command).heading = Heading.S;
				break;
			case "SW":
				command = new MineFieldCommands(model);
				((MineFieldCommands)command).heading = Heading.SW;
				break;
			case "W":
				command = new MineFieldCommands(model);
				((MineFieldCommands)command).heading = Heading.W;
				break;
			case "NW":
				command = new MineFieldCommands(model);
				((MineFieldCommands)command).heading = Heading.NW;
				break;
		}
		return command;
	}
}