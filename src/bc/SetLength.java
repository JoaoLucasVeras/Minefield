package bc;

import mvc.Command;
import mvc.Model;
import mvc.Utilities;

public class SetLength extends Command {

	public Double newValue;
	public SetLength(Model model) {
		super(model);
		newValue = null;
	}

	@Override
	public void execute() throws Exception {
		// TODO Auto-generated method stub
		Brick brick = (Brick)model;
        if (newValue == null) {
            String resp = Utilities.ask("New Value?");
            newValue = Double.parseDouble(resp);
        }
        brick.setLength(newValue);
	}

}
