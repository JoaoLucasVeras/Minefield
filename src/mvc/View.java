package mvc;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import java.util.*;

public class View extends JPanel implements PropertyChangeListener {
	protected Model model;

	public View(Model newModel) {
		model = newModel;
		setSize(500,500);
		Border blackline = BorderFactory.createLineBorder(Color.black);
		setBorder(blackline);
		setBackground(Color.LIGHT_GRAY);
		repaint();
	}
	public void setModel(Model newModel) {
		this.model.removePropertyChangeListener(this);
		this.model = newModel;
		this.model.initSupport(); // defined in Bean
		this.model.addPropertyChangeListener(this);
		repaint();
	}
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub
		repaint();
	}

}
