package mvc;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.beans.*;

public class View extends JPanel implements PropertyChangeListener {
	protected Model model;

	public View(Model newModel) {
		model = newModel;
		setSize(500,500);
		Border blackline = BorderFactory.createLineBorder(Color.black);
		setBorder(blackline);
		setBackground(Color.LIGHT_GRAY);
	}
	public void setModel(Model newModel) {
		//	remove(this);
		this.model.removePropertyChangeListener(this);
		this.model = newModel;
		this.model.initSupport(); // defined in Bean
		this.model.addPropertyChangeListener(this);
		repaint();
	}
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		revalidate();
		repaint();
	}
}
