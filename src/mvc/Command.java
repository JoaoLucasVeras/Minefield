package mvc;

import java.awt.event.ActionEvent;

public class Command {

	
	public void actionPerformed(ActionEvent ae) {
		   try {
		     // handle control actions here
		   } catch (Exception e) {
		   handleException(e);
		   }
		}

		protected void handleException(Exception e) {
		Utilities.error(e);
		}
}
