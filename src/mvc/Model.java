package mvc;

/**
 * An abstract class for an application. Provides other classes within the
 * Model-View-Controller framework with functionality based on an application's
 * functionality.
 * @author Joao Lucas Veras
 * @author William Tran
 * @version %I%, %G%
 */
public abstract class Model extends Bean{

	private String fName;
	private boolean unsavedChanges;

	/**
	 * Constructor class for Model, sets the filename to null and unsavedChanges to false.
	 */
	public Model() {
		fName = null;
		unsavedChanges = false;
	}

	/**
	 * Returns the fileName
	 * @return	a string with the name of the current file
	 */
	public String getFileName() {
		return fName;
	}

	/**
	 * Sets the fileName to a new String
	 * @param fName the text of the new fileName
	 */
	public void setFileName(String fName) {
		this.fName = fName;
	}

	/**
	 * Sets UnsavedChanges to true or false. Should be called when a
	 * property is changed or the file is saved.
	 * @param b True if property is changed, false when the file is saved.
	 */
	public void setUnsavedChanges(boolean b) {
		unsavedChanges = b;
	}

	/**
	 * Returns UnsavedChanges.
	 * @return True if the file has not been saved, false if the file
	 * has been saved.
	 */
	public boolean getUnsavedChanges() {
		return unsavedChanges;
	}

	/**
	 * Tells any related classes that a property has been changed, such
	 * as View. Sets unsavedChanges to true and fires a propertyChangedEvent,
	 * which notifies any PropertyChangeListeners listening to this model.
	 */
	public void changed() {
		unsavedChanges = true;
		firePropertyChange(fName, null, this);
	}
}
