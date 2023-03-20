package mvc;

import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.*;

/**
 * Provides the GUI while connecting JPanel inputs to the model. It will
 * also handle exceptions, file saving/opening, and information.
 * @author Joao Lucas Veras
 * @author William Tran
 * @version %I%, %G%
 */
public class AppPanel extends JPanel implements PropertyChangeListener, ActionListener  {

    protected Model model;
    protected AppFactory factory;
    protected View view;
    protected JPanel controlPanel; // not a separate class!
    private SafeFrame frame;
    public static int FRAME_WIDTH = 500;
    public static int FRAME_HEIGHT = 300;

    /**
     * Creates a panel connected to a model and displays it. Accepts a factory to
     * handle model, command, and information creation. Creates a view connected
     * to the model. Displays two panels, a control and a view panel, and a menubar.
     * @param factory the factory used to create the model
     */
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

    /**
     * Displays AppPanel to the user.
     */
    public void display() { frame.setVisible(true); }

    /**
     * Provides a way to notify all parts of a change in property. Usually called
     * after commands and fires a PropertyChangeEvent. Subclasses should override
     * as needed.
     * @param evt A PropertyChangeEvent object describing the event source
     *          and the property that has changed.
     */
    public void propertyChange(PropertyChangeEvent evt) {
        /* override in extensions if needed */
    	//evt.getNewValue();
    	
    }

    /**
     * Returns the current model
     * @return the current model
     */
    public Model getModel() { return model; }

    // called by file/open and file/new

    /**
     * Sets a new model to the current active AppPanel. Called when a new
     * file is created or opened.
     * @param newModel the new model to connect to
     */
    public void setModel(Model newModel) {
            this.model.removePropertyChangeListener(this);
            this.model = newModel;
            this.model.initSupport(); // defined in Bean
            this.model.addPropertyChangeListener(this);
            view.setModel(newModel);
            model.changed();       
    }

    /**
     * Creates a menubar that has a File menu, an Edit menu, and a Help menu. File
     * has commands for creating a new file, saving the current file, saving the current
     * file in a different name, opening a saved file, or quitting. The edit menu will
     * contain commands available to the current model gotten from a list created by Factory.
     * Help menu has a help command giving information on each edit command and an about
     * command giving information about the app.
     * @return a finished JMenuBar with File, Edit, and Help menus
     */
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

    /**
     * Receives a user's action and decides what command to execute. It first
     * turns the ActionEvent into a String and creates a Command related to
     * the ActionEvent. It then checks for a matching command starting from the
     * File menu, then the Help menu, and finally the Edit menu. If the action
     * doesn't match any command or the Command executed throws an exception, it
     * will be handled by handleEsception() method.
     * @param ae the ActionEvent sent by the user to be converted into a Command
     */
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

    /**
     * Called by actionPerformed() to handle Command not found or Commands
     * throwing exceptions. Pops up an error window with a description of
     * the exception being thrown.
     * @param e the exception that was thrown. It will appear in a separate
     *          error window.
     */
    protected void handleException(Exception e) {
        Utilities.error(e);
    }

    /**
     * A class to manage the Edit commands an application might need. Creates a Panel
     * with a pink background for applications to add JComponents in a separate panel
     * from the viewing panel.
     */
    class ControlPanel extends JPanel {
        /**
         * Constructor class for ControlPanel. Creates a ControlPanel with a pink background.
         */
        public ControlPanel() 
        {
            setBackground(Color.PINK);
        }
    }
}
