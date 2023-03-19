package minefield;

import java.awt.GridLayout;
import java.beans.PropertyChangeEvent;

import javax.swing.JButton;
import javax.swing.JPanel;

import bc.Brick;
import mvc.AppFactory;
import mvc.AppPanel;

public class MineFieldPanel extends AppPanel{

	public MineFieldPanel(AppFactory factory) {
		super(factory);
		MineField minefield = (MineField) model;
		minefield.addPropertyChangeListener(this);
		controlPanel.setLayout(new GridLayout(4,2));
		
		JPanel panel = new JPanel();
		
		JButton northWest = new JButton("NW");
		northWest.setActionCommand("NorthWest");
		northWest.addActionListener(this);
		panel.add(northWest);
		controlPanel.add(panel);
		
		JButton north = new JButton("N");
		north.setActionCommand("North");
		north.addActionListener(this);
		panel.add(north);
		controlPanel.add(panel);
		
		JButton northEast = new JButton("NE");
		northEast.setActionCommand("NorthEast");
		northEast.addActionListener(this);
		panel.add(northEast);
		controlPanel.add(panel);
		
		JButton west = new JButton("W");
		west.setActionCommand("West");
		west.addActionListener(this);
		panel.add(west);
		controlPanel.add(panel);
		
		JButton east = new JButton("E");
		east.setActionCommand("East");
		east.addActionListener(this);
		panel.add(east);
		controlPanel.add(panel);
		
		JButton southWest = new JButton("SW");
		southWest.setActionCommand("SouthWest");
		southWest.addActionListener(this);
		panel.add(southWest);
		controlPanel.add(panel);
		
		JButton south = new JButton("S");
		south.setActionCommand("South");
		south.addActionListener(this);
		panel.add(south);
		controlPanel.add(panel);
		
		JButton southEast = new JButton("SE");
		southEast.setActionCommand("SouthEast");
		southEast.addActionListener(this);
		panel.add(southEast);
		controlPanel.add(panel);
		
		
		// TODO Auto-generated constructor stub
	}
	
	 @Override
	    public void propertyChange(PropertyChangeEvent evt) {
	        /*super.propertyChange(evt);
	        Brick brick = (Brick) model;*/
	        
	    }
	 
	 public static void main(String[] args) {
		AppFactory factory = new MineFieldFactory();
		AppPanel panel = new MineFieldPanel(factory);
		panel.display();
		 
	}

}
