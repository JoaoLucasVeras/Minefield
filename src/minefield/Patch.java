package minefield;

import java.io.Serializable;
import java.util.Random;

public class Patch implements Serializable {
	
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
	
	public Patch() 
	{
		numMinedNums = 0;
		occupied = false;
		goal = false;
		bomb = false;
	}
	public Patch(int dim) {
		// TODO Auto-generated constructor stub
		this.dim = dim;
		patch = new Patch[dim][dim];
		for(int i = 0; i<dim; i++) 
		{
			for(int j = 0; j<dim; j++) 
			{
				patch[i][j] = new Patch();
				if(i > 1 && j>1) {
					int rand = new Random().nextInt(100);
					if(rand < 5) 
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
		patch[dim-1][dim-1].goal = true;
		
	}
	

}
