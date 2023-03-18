package mvc;

import java.awt.*;
import java.awt.event.*;
import java.beans.*;
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
        JMenu editMenu = Utilities.makeMenu("Edit", new String[]{"N", "NE", "E", "SE", "S", "SW", "W", "NW"}, this);
        result.add(editMenu);
        JMenu helpMenu = Utilities.makeMenu("Help", new String[]{"About", "Help"}, this);
        result.add(helpMenu);
        return result;
    }

    public void actionPerformed(ActionEvent ae) {
        String cmmd = ae.getActionCommand();
        try {
            switch (cmmd) {
                case "N": {
                    break;
                }
                case "NE": {
                    break;
                }
                case "E": {
                    break;
                }
                case "SE": {
                    break;
                }
                case "S": {
                    break;
                }
                case "SW": {
                    break;
                }
                case "W": {
                    break;
                }
                case "NW": {
                    break;
                }
                case "New": {
                    if (Utilities.confirm("Are you sure? Unsaved changes will be lost!")) {
                        this.setModel(new Model());
                    }
                    break;
                }
                case "Save": {
                    String fName = Utilities.getFileName((String) null, false);
                    ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(fName));
                    os.writeObject(this.model);
                    os.close();
                    break;
                }
                case "Open": {
                    if (Utilities.confirm("Are you sure? Unsaved changes will be lost!")) {
                        String fName = Utilities.getFileName((String) null, true);
                        ObjectInputStream is = new ObjectInputStream(new FileInputStream(fName));
                        this.setModel((Model) is.readObject());
                        is.close();
                    }
                    break;
                }
                case "Quit": {
                    if (Utilities.confirm("Are you sure? Unsaved changes will be lost!"))
                        System.exit(0);
                    break;
                }
                case "About": {
                    Utilities.inform("Joao Lucas Veras, William Tran, MineField, 2023. All rights reserved.");
                    break;
                }
                case "Help": {
                    String[] cmmds = new String[]{
                            "N: move North \nNE: move Northeast \nE: move East \nSE: move Southeast \nS: move South \nSW: move Southwest \nW: move West \nNW: move Northwest \n",
                    };
                    Utilities.inform(cmmds);
                    break;

                }
            }
        }
    }

    protected void handleException(Exception e) {
        // ???
    }

    class ControlPanel extends JPanel {
        public ControlPanel() {
            setBackground(Color.PINK);
            JPanel p = new JPanel();
            p.setLayout(new GridLayout(2, 4));
            JButton n = new JButton("N");
            n.addActionListener(AppPanel.this);
            p.add(n);
            JButton ne = new JButton("NE");
            ne.addActionListener(AppPanel.this);
            p.add(ne);
            JButton e = new JButton("E");
            e.addActionListener(AppPanel.this);
            p.add(e);
            JButton se = new JButton("SE");
            se.addActionListener(AppPanel.this);
            p.add(se);
            JButton s = new JButton("S");
            s.addActionListener(AppPanel.this);
            p.add(s);
            JButton sw = new JButton("SW");
            sw.addActionListener(AppPanel.this);
            p.add(sw);
            JButton w = new JButton("W");
            w.addActionListener(AppPanel.this);
            p.add(w);
            JButton nw = new JButton("NW");
            nw.addActionListener(AppPanel.this);
            p.add(nw);
            add(p);
        }
    }
        public static void main(String[] args) {
            AppPanel app = new AppPanel();
        }
}

