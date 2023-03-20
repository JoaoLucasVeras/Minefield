package mvc;

import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;
import javax.swing.*;



public class AppPanel extends JPanel implements PropertyChangeListener, ActionListener  {

    protected Model model;
    protected AppFactory factory;
    protected View view;
    protected JPanel controlPanel; // not a separate class!
    private SafeFrame frame;
    public static int FRAME_WIDTH = 500;
    public static int FRAME_HEIGHT = 300;

    public AppPanel(AppFactory factory) {
        super();
        
        model = factory.makeModel();
        
        view = factory.makeView(model);
        view.setModel(model);
        
        this.factory = factory;
        controlPanel = new ControlPanel();
        
        this.setLayout((new GridLayout(1, 2)));
        this.add(controlPanel);
        this.add(view);
        
        frame = new SafeFrame();
        Container cp = frame.getContentPane();
        cp.add(this);
        
        frame.setJMenuBar(this.createMenuBar());
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setTitle(factory.getTitle());
        frame.setVisible(true);
    }


    // called in main
    public void display() { frame.setVisible(true); }

    public void propertyChange(PropertyChangeEvent evt) {
        /* override in extensions if needed */
    	
    }

    public Model getModel() { return model; }

    // called by file/open and file/new
    public void setModel(Model newModel) {
        this.model.removePropertyChangeListener(this);
        this.model = newModel;
        this.model.initSupport(); // defined in Bean
        this.model.addPropertyChangeListener(this);
        view.setModel(this.model);
        model.changed();
    }

    protected JMenuBar createMenuBar() { //make menu bar
        JMenuBar result = new JMenuBar();
        JMenu fileMenu = Utilities.makeMenu("File", new String[]{"New", "Save", "Save As", "Open", "Quit"}, this);
        result.add(fileMenu);
        JMenu editMenu = Utilities.makeMenu("Edit", factory.getEditCommands(), this);
        result.add(editMenu);
        JMenu helpMenu = Utilities.makeMenu("Help", new String[] {"Help", "About"}, this);
        result.add(helpMenu);
        return result;
    }

    public void actionPerformed(ActionEvent ae) { //gets action performed
        String cmmd = ae.getActionCommand();
        Command command = factory.makeEditCommand(model, cmmd, ae.getSource());
        
        try {
        	switch(cmmd) 
        	{
	        	case "Save": { //saves project to a file
	            	if(model.getFileName() == null) { //if the project is not already saved in a file find a new location
	            		model.setFileName(Utilities.getFileName((String) null, false));
	            	}
	            	ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(model.getFileName()));
	                os.writeObject(this.model);
	                os.close();
	            	break;
	            }

	            case "Save As": { //finds a new save file location
	            	model.setFileName(Utilities.getFileName((String) null, false));
	                ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(model.getFileName()));
	                os.writeObject(this.model);
	                os.close();
	                break;
	            }
	
	            case "Open": { //opens file with saved project
	                if (Utilities.confirm("Are you sure? Unsaved changes will be lost!")) {
	                    model.setFileName(Utilities.getFileName((String) null, true));
	                    ObjectInputStream is = new ObjectInputStream(new FileInputStream(model.getFileName()));
	                    model = (Model) is.readObject();
                        this.model.initSupport();
                        this.remove(view);
                        view = factory.makeView(model);
                        setModel(model);
                        this.add(view);
                        revalidate();
	                    is.close();
	                }
	                break;
	
	            }
	
	            case "New": { //makes a new canvas
	                if (Utilities.confirm("Are you sure? Unsaved changes will be lost!")) {
	                	model = factory.makeModel();
                        this.remove(view);
                        view = factory.makeView(model);
                        setModel(model);
	                    this.add(view);
                        revalidate();
                    }
	                break;
	            }
	
	            case "Quit": { //closes project
	                if (Utilities.confirm("Are you sure? Unsaved changes will be lost!"))
	                    System.exit(0);
	                break;
	            }

	            case "About": { //informs user about creator info
                    Utilities.inform(factory.about());
                    break;
                }

                case "Help": { //tells user about what each command/button does
                    Utilities.inform(factory.getHelp());
                    break;
                }
        	}

        	if(command != null) //action occurs on control panel
        	{
        		command.execute();
        	}
       } catch (Exception e) {
            handleException(e);
        }
    }

    protected void handleException(Exception e) {
        Utilities.error(e);
    }

    class ControlPanel extends JPanel {
        public ControlPanel() 
        {
            setBackground(Color.PINK);
        }
     }
}
