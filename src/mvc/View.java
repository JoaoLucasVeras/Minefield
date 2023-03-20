package mvc;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.beans.*;

/**
 * Creates a JPanel which is used for visualizations. Customizers are able to 
 * make specific views that extend this class.
 * @author Joao Lucas Veras
 * @author William Tran
 * @version %I%, %G%
 */
public class View extends JPanel implements PropertyChangeListener {
	protected Model model;

	/*
	 * Creates a new View using a model that is passed. Sets the size of panel
	 * to 500 500 and creates a border
	 * @param Model the model is used to make the view panel
	 */
	public View(Model newModel) {
		model = newModel;
		setSize(500,500);
		Border blackline = BorderFactory.createLineBorder(Color.black);
		setBorder(blackline);
		setBackground(Color.LIGHT_GRAY);
	}
	/*
	 * Sets the model in class to a new model. Removes the propertyChangeListener
	 * of old model and adds new model to addPropertyChangeListener
	 * @param Model takes in the new model
	 */
	public void setModel(Model newModel) {
		//	remove(this);
		this.model.removePropertyChangeListener(this);
		this.model = newModel;
		this.model.initSupport(); // defined in Bean
		this.model.addPropertyChangeListener(this);
		repaint();
	}
	/**
     * Provides a way to notify all parts of a change in property. Usually called
     * after commands and fires a PropertyChangeEvent. Subclasses should override
     * as needed.
     * @param evt A PropertyChangeEvent object describing the event source
     *          and the property that has changed.
     */
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		revalidate();
		repaint();
	}
}
