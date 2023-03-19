package minefield;

import mvc.*;

import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.beans.PropertyChangeEvent;

// my MineField contains an n x n array of patches 
class Cell extends JLabel {
    Patch patch;
    
}

public class MineFieldView extends View {

    private Cell cells[][];
    private int dim;
    public MineFieldView(MineField m) {
        super(m);
        dim = m.getDim();
        cells = new Cell[dim][dim];
        setLayout(new GridLayout(dim, dim));
        for(int row = 0; row < dim; row++) {
            for (int col = 0; col < dim; col++) {
            
            	cells[row][col] = new Cell();
            	cells[row][col].setText("?");
            	cells[row][col].patch = m.getPatch(row,col);
               cells[row][col].setBorder(BorderFactory.createLineBorder(Color.black));
               if(cells[row][col].patch.occupied) 
               {
            	   cells[row][col].setBackground(Color.red);
            	   cells[row][col].setBorder(BorderFactory.createLineBorder(Color.white));
            	   cells[row][col].setText("" + cells[row][col].patch.numMinedNums);
               }
               if(cells[row][col].patch.goal) 
               {
            	   cells[row][col].setBackground(Color.white);
            	   cells[row][col].setBorder(BorderFactory.createLineBorder(Color.green));
               }
//               if(cells[row][col].patch.bomb) 
//               {
//            	   cells[row][col].setText("X");
//            	   cells[row][col].setBorder(BorderFactory.createLineBorder(Color.red));
//               }
              add(cells[row][col]);
               
            }
        }
    }

    public int findBomb(int row, int col) 
    {
    	int bombCount = 0;
    	
    	if(row-1 >=0) 
    	{
    		if(cells[row - 1][col].patch.bomb) 
    		{
    			bombCount++;
    		}
    		if(col-1 >= 0) 
    		{
    			if(cells[row - 1][col -1].patch.bomb) 
        		{
        			bombCount++;
        		}
    		}
    		if(col + 1 < dim) 
    		{
    			if(cells[row - 1][col + 1].patch.bomb) 
        		{
        			bombCount++;
        		}
    		}
    	}
    	if(row +1 < dim) 
    	{
    		if(cells[row + 1][col].patch.bomb) 
    		{
    			bombCount++;
    		}
    		if(col-1 >= 0) 
    		{
    			if(cells[row + 1][col -1].patch.bomb) 
        		{
        			bombCount++;
        		}
    		}
    		if(col + 1 < dim) 
    		{
    			if(cells[row + 1][col + 1].patch.bomb) 
        		{
        			bombCount++;
        		}
    		}
    	}
    	if(col-1 >= 0) 
		{
			if(cells[row][col -1].patch.bomb) 
    		{
    			bombCount++;
    		}
		}
    	if(col + 1 < dim) 
		{
			if(cells[row][col +1].patch.bomb) 
    		{
    			bombCount++;
    		}
		}
    	return bombCount;
    }

    public void propertyChange(PropertyChangeEvent evt) {
        // ???
    	for(int i = 0; i< cells.length; i++) 
    	{
    		for(int j = 0; j<cells.length; j++) 
    		{
    			if(cells[i][j].patch.lastOccupied) 
    			{
    				cells[i][j].setBorder(new LineBorder(Color.white));
    			}
    			if(cells[i][j].patch.occupied) 
    			{
    				cells[i][j].setText("" + findBomb(i, j));
    				cells[i][j].setBorder(new LineBorder(Color.blue));
    			}
    			
    		}
    	}
    }


}