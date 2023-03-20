package mvc;

public abstract class Model extends Bean{

	private String fName;
	private boolean unsavedChanges;
	public Model() {
		fName = null;
		unsavedChanges = false;
		
	}

	public String getFileName() {
		return fName;
	}

	public void setFileName(String fName) {
		this.fName = fName;
	}

	public void setUnsavedChanges(boolean b) {
		unsavedChanges = b;
	}

	public boolean getUnsavedChanges() {
		return unsavedChanges;
	}

	public void changed() {
		unsavedChanges = true;
		firePropertyChange(fName, null, this);
	}
}
