package mvc;

public abstract class Model extends Bean{

	private String fName;
	private boolean unsavedChanges;
	public Model() {
		// TODO Auto-generated constructor stub
		fName = null;
		unsavedChanges = false;
		
	}

	public String getFileName() {
		// TODO Auto-generated method stub
		return fName;
	}

	public void setFileName(String fName) {
		// TODO Auto-generated method stub
		this.fName = fName;
	}

	public void setUnsavedChanges(boolean b) {
		// TODO Auto-generated method stub
		unsavedChanges = b;
	}

	public boolean getUnsavedChanges() {
		// TODO Auto-generated method stub
		return unsavedChanges;
	}

	public void changed() {
		// TODO Auto-generated method stub
		unsavedChanges = true;
		firePropertyChange(fName, null, this);
	}
	
	

}
