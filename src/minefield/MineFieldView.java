package minefield;

import mvc.*;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;

// my MineField contains an n x n array of patches 
class Cell extends JLabel {
    Patch patch;
}

public class MineFieldView extends View {

    private Cell cells[][];
    public MineFieldView(MineField m) {
        super(m);
        int dim = m.getDim();
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
               if(cells[row][col].patch.bomb) 
               {
            	   cells[row][col].setBackground(Color.red);
            	   cells[row][col].setText("X");
               }
            }
        }
    }


    public void propertyChange(PropertyChangeEvent evt) {
        // ???
    }


}