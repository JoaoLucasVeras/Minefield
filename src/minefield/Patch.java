package minefield;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;


public class Patch implements Serializable{

	
	public int numMinedNums;
	
	public boolean occupied;
	public boolean lastOccupied = false;
	public boolean visited = false;

	public boolean goal;
	public boolean bomb;
	
	public int dim;
	public int occupiedX;
	public int occupiedY;
	public Patch[][] patch;
	//public ArrayList<Patch> bombPatch;
	public Patch() 
	{
		numMinedNums = 0;
		occupied = false;
		goal = false;
		bomb = false;
	}
	public Patch(int dim) {
		this.dim = dim;
		patch = new Patch[dim][dim];
		for (int i = 0; i < dim; i++)
		{
			for (int j = 0; j < dim; j++)
			{
				patch[i][j] = new Patch();
				if (i > 1 && j > 1) {
					int rand = new Random().nextInt(100);
					if (rand < 5)
					{
						patch[i][j].bomb = true;
					}
				}
				patch[i][j].occupied = false;
				patch[i][j].goal = false;
				patch[i][j].numMinedNums = 0;
			}
		}
		patch[0][0].occupied = true;
		patch[dim - 1][dim - 1].goal = true;
		for(int i = 0; i<dim; i++) 
		{
			for(int j = 0; j<dim; j++) 
			{
				patch[i][j].numMinedNums = findBomb(i, j);
			}
		}
	}
	
	public int findBomb(int row, int col) 
    {
    	int bombCount = 0;
    	if (row - 1 >=0)
    	{
    		if (patch[row - 1][col].bomb)
    		{
    			bombCount++;
    		}
    		if (col - 1 >= 0)
    		{
    			if(patch[row - 1][col - 1].bomb)
        		{
        			bombCount++;
        		}
    		}
    		if(col + 1 < dim) 
    		{
    			if(patch[row - 1][col + 1].bomb) 
        		{
        			bombCount++;
        		}
    		}
    	}
    	if (row + 1 < dim)
    	{
    		if (patch[row + 1][col].bomb)
    		{
    			bombCount++;
    		}
    		if (col - 1 >= 0)
    		{
    			if(patch[row + 1][col - 1].bomb)
        		{
        			bombCount++;
        		}
    		}
    		if (col + 1 < dim)
    		{
    			if (patch[row + 1][col + 1].bomb)
        		{
        			bombCount++;
        		}
    		}
    	}
    	if (col - 1 >= 0)
		{
			if (patch[row][col - 1].bomb)
    		{
    			bombCount++;
    		}
		}
    	if (col + 1 < dim)
		{
			if(patch[row][col + 1].bomb)
    		{
    			bombCount++;
    		}
		}
    	return bombCount;
    }
}