import java.util.ArrayList;

public class Snake_CLussier
{
	public static Board_CLussier myBoard;
	private int posR;
	private int posC;
	public int dir;
	public int nextDir;
	public static int snakeLength;
	public static boolean gameOver = false;
	
	public Snake_CLussier(int r, int c, int d, Board_CLussier  b)
	{
		posR = r;
		posC = c;
		dir = d;	
		nextDir = d;
		myBoard = b;	
		myBoard.setValue(r, c);
		snakeLength = 3;
	}

	
	public void setDir(int d)
	{
		if (Math.abs(dir-d) != 2)
		{
			nextDir = d;
		}
	}
	
	public void createSnake()
	{
		//resets points behind snake to 0 and also removes those coordinates from the saved array lists
		while(snakeLength < SnakeGame_CLussier.myBoard.savedRows.size())									
		{
			Board_CLussier.matrix[SnakeGame_CLussier.myBoard.savedRows.get(snakeLength)]//sets the spot in the matrix behind the snake's tail to zero
					[SnakeGame_CLussier.myBoard.savedCols.get(snakeLength)] = 0;
			
			SnakeGame_CLussier.myBoard.savedRows.remove(snakeLength);					//removes row coordinate behind snake
			SnakeGame_CLussier.myBoard.savedCols.remove(snakeLength);					//removes col coordinate behind snake
		}
		for(int i = 0; i < myBoard.savedCols.size() ; i++)								//loops through savedCol and savedRow to set that coordinate values to 1 in the matrix
		{
			int posC = SnakeGame_CLussier.myBoard.savedCols.get(i);						//sets posC to the coordinate saved within savedCols
			int posR = SnakeGame_CLussier.myBoard.savedRows.get(i);						//sets posR to the coordinate saved within savedRows
			
			Board_CLussier.matrix[posR][posC] = 1;										//sets coordinate spot (posR,posC) within the matrix to 1
		}
	}
	
	public void eatFruit(int newR, int newC)
	{
		if((myBoard.getValue(newR , newC)) == -1)
		{
			snakeLength = snakeLength + 3;
		}
	}
	
	public boolean checkCollisions()
	{
		int newC = posC;
		int newR = posR;
		//finds next spot based on direction
		switch (dir)
		{
		
			//left
			case 3: newR = posR-1;
					break;
			//down
			case 2: newC = posC+1;
					break;
			//right
			case 1: 
					newR = posR+1;
					break;
			// up
			case 0:	newC = posC-1;
			 		break;
		}
		
		//checks collision for top and bottom walls
		if((newR < 0) || (newR >= myBoard.NUM_ROWS))
		{
			gameOver = true;
			return false;
		}
		//checks collision for left and right walls
		else if((newC < 0) || (newC >= myBoard.NUM_COLS))
		{
			gameOver = true;
			return false;
		}
		//checks collisions for body collisions
		else if(((myBoard.getValue(newR, newC)) == 1))
		{		
			gameOver = true;
			return false;
		}
		eatFruit(newR, newC);
		
		return true;
	}

	
	
	
	public boolean move()
	{
		dir = nextDir;
		
		switch (dir)
		{
			//left
			case 3: myBoard.setValue(--posR, posC);
					break;
			//down
			case 2: myBoard.setValue(posR, ++posC);
					break;
			//right
			case 1: myBoard.setValue(++posR, posC);
					break;
			//up
			case 0: myBoard.setValue(posR, --posC);
			 		break;
		
		}
		return true;
	}


}
