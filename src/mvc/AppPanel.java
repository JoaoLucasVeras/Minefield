package mvc;

import java.awt.*;
import java.awt.event.*;
import java.beans.*;
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
        model = new Model();
        this.factory = factory;
        view = new View(model);
        controlPanel = new ControlPanel();
        this.setLayout((new GridLayout(1, 2)));
        this.add(controlPanel);
        this.add(view);
        frame = new SafeFrame();
        Container cp = frame.getContentPane();
        cp.add(this);
        frame.setJMenuBar(this.createMenuBar());
        frame.setTitle("MineField");
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setVisible(true);
    }


    // called in main
    public void display() { frame.setVisible(true); }

    //
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

    protected JMenuBar createMenuBar() {
        JMenuBar result = new JMenuBar();
        JMenu fileMenu = Utilities.makeMenu("File", new String[]{"New", "Save", "Open", "Quit"}, this);
        result.add(fileMenu);
        JMenu editMenu = Utilities.makeMenu("Edit", factory.getEditCommands()), this);
        result.add(editMenu);
        JMenu helpMenu = Utilities.makeMenu("Help", new String[]{factory.about(), factory.getHelp()}, this);
        result.add(helpMenu);
        return result;
    }

    public void actionPerformed(ActionEvent ae) {
        String cmmd = ae.getActionCommand();
        Command command = factory.makeEditCommand(model, cmmd, ae.getSource());
        try {
            command.execute();
        } catch (Exception e) {
            handleException(e);
        }
    }

    protected void handleException(Exception e) {
        Utilities.error(e);
    }

    class ControlPanel extends JComponent {
        /*probably not needed because specifies too much
        public ControlPanel() {
            setBackground(Color.PINK);
            JPanel p = new JPanel();
            String[] cmmds = factory.getEditCommands();
            p.setLayout(new GridLayout(3,3)); //there HAS to be a better way to make a layout
            for (String cmmd : cmmds) {
                JButton n = new JButton(cmmd);
                n.addActionListener(AppPanel.this);
                p.add(n);
            }
            add(p);
        }*/
    }
}
