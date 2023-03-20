package minefield;

import mvc.Model;

public class MineField extends Model{
	
	private int dim;
	Patch patch;
	public MineField() {
		dim = 20;
		patch = new Patch(dim);
	}

	public int getDim() {
		return dim;
	}

	public Patch getPatch(int row, int col) {
		return patch.patch[row][col];
	}

	
	public void move(Heading heading) throws Exception {
		if (patch.patch[patch.occupiedX][patch.occupiedY].bomb || patch.patch[patch.occupiedX][patch.occupiedY].goal)
		{
			throw new Exception("The Game is over!");
		}
		patch.patch[patch.occupiedX][patch.occupiedY].lastOccupied = true;
		switch(heading) 
		{
			case N:
			{
				if (patch.occupiedX - 1 >= 0)
				{
					patch.patch[patch.occupiedX - 1][patch.occupiedY].occupied = true;
					patch.patch[patch.occupiedX][patch.occupiedY].occupied = false;
					patch.occupiedX -= 1;
				}
				else 
				{
					throw new Exception("Cannot go out of bounds");
				}
				break;
			}
			case S:
			{	
				if (patch.occupiedX + 1 < dim)
				{
					patch.patch[patch.occupiedX + 1][patch.occupiedY].occupied = true;
					patch.patch[patch.occupiedX][patch.occupiedY].occupied = false;
					patch.occupiedX += 1;
				}
				else 
				{
					throw new Exception("Cannot go out of bounds");
				}
				break;
			}
			case W:
			{
				if (patch.occupiedY - 1 >= 0)
				{
					patch.patch[patch.occupiedX][patch.occupiedY - 1].occupied = true;
					patch.patch[patch.occupiedX][patch.occupiedY].occupied = false;
					patch.occupiedY -= 1;
				}
				else 
				{
					throw new Exception("Cannot go out of bounds");
				}
				break;
			}
			case E:
			{
				if (patch.occupiedY + 1 < dim)
				{
					patch.patch[patch.occupiedX][patch.occupiedY + 1].occupied = true;
					patch.patch[patch.occupiedX][patch.occupiedY].occupied = false;
					patch.occupiedY += 1;
				}
				else 
				{
					throw new Exception("Cannot go out of bounds");
				}
				break;
			}
			case NE:
			{
				if (patch.occupiedX - 1 >= 0 && patch.occupiedY + 1 < dim)
				{
					patch.patch[patch.occupiedX - 1][patch.occupiedY + 1].occupied = true;
					patch.patch[patch.occupiedX][patch.occupiedY].occupied = false;
					patch.occupiedX -= 1;
					patch.occupiedY += 1;
				}
				else 
				{
					throw new Exception("Cannot go out of bounds");
				}
				break;
			}
			case NW:
			{
				if (patch.occupiedX - 1 >= 0 && patch.occupiedY - 1 >= 0)
				{
					patch.patch[patch.occupiedX - 1][patch.occupiedY - 1].occupied = true;
					patch.patch[patch.occupiedX][patch.occupiedY].occupied = false;
					patch.occupiedX -= 1;
					patch.occupiedY -= 1;
				}
				else 
				{
					throw new Exception("Cannot go out of bounds");
				}
				break;
			}
			case SE:
			{
				if (patch.occupiedX + 1 < dim && patch.occupiedY + 1 < dim)
				{
					patch.patch[patch.occupiedX + 1][patch.occupiedY + 1].occupied = true;
					patch.patch[patch.occupiedX][patch.occupiedY].occupied = false;
					patch.occupiedX += 1;
					patch.occupiedY += 1;
				}
				else 
				{
					throw new Exception("Cannot go out of bounds");
				}
				break;
			}
			case SW:
			{
				if (patch.occupiedX + 1 < dim && patch.occupiedY - 1 >= 0)
				{
					patch.patch[patch.occupiedX + 1][patch.occupiedY - 1].occupied = true;
					patch.patch[patch.occupiedX][patch.occupiedY].occupied = false;
					patch.occupiedX += 1;
					patch.occupiedY -= 1;
				}
				else 
				{
					throw new Exception("Cannot go out of bounds");
				}
				break;
			}
		}
		changed();
		patch.patch[patch.occupiedX][patch.occupiedY].visited = true;

		if (patch.patch[patch.occupiedX][patch.occupiedY].bomb)
		{
			throw new Exception("You have stepped on a mine, game over!");
		}
		if (patch.patch[patch.occupiedX][patch.occupiedY].goal)
		{
			throw new Exception("You have reached the goal!");
		}
	}
}