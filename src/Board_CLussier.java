import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;
import java.awt.Font;

public class Board_CLussier extends JPanel
{
	public static ArrayList<Integer> savedRows;
	public static ArrayList<Integer> savedCols;	
	public static int[][] matrix;
	public final int NUM_ROWS;
	public final int NUM_COLS;
	static final int size = 8;
	public Board_CLussier(int r, int c)
	{	
		NUM_ROWS = r;
		NUM_COLS = c;
		savedRows = new ArrayList<Integer>();
		savedCols = new ArrayList<Integer>();
		matrix = new  int[NUM_ROWS][NUM_COLS];
		setPreferredSize(new Dimension((NUM_ROWS)*size, (NUM_COLS+30)*size));
	}
	//Adds r and c to their respective arrayList
	public void setValue(int r, int c)
	{
		savedRows.add(0, r);
		savedCols.add(0, c);
	}
	//Gets the value at r,c in the matrix
	public int getValue(int r, int c)
	{
		return matrix[r][c];
	}
	//puts a fruit on a random space on the board (thats not on top of the snake)
	public boolean setRandom()
	{
			if(stillZeros() && stillOneFruit())
			{
				int randomRow = (int)(Math.random()*matrix.length);
				int randomCol = (int)(Math.random()*matrix[1].length);
				
				if(matrix[randomRow][randomCol] == 0)
					matrix[randomRow][randomCol] = -1;		
			}
		return stillZeros();	
	}
	//makes sure there is only one fruit at a time
	public boolean stillOneFruit()
	{
		for(int r = 0; r < matrix.length; r++)
			for(int c = 0; c < matrix[1].length; c++)
				if (matrix[r][c] == -1)
					return false;
		return true;
	}
	//checks if there are still zeros 
	public boolean stillZeros()
	{
		for(int r = 0; r <= matrix.length; r++)
				for(int c = 0; c <= matrix[1].length; c++)
					if (matrix[r][c] == 0)
						return true;
		return false;
	}
	
	public void paintComponent(Graphics g)
		{	
			super.paintComponents(g);
			setBackground(Color.BLACK);
			int maxWidth = NUM_COLS * size;
			int maxHeight = NUM_ROWS * size;
			
			//black background
			for(int i = 0; i < NUM_ROWS; i++)
				for( int j = 0; j < NUM_COLS; j++)
				{
					if(matrix[i][j] == 0)
					{
						g.setColor(Color.BLACK);
						g.fillRect(i*size, j*size, size, size);
					}
				}
			
			//draw grids
			g.setColor(Color.darkGray);
			g.drawRect(0, 0, maxHeight, maxWidth);
			for(int x = 0; x < maxHeight; x = x + size)
				for(int y = 0; y < maxWidth; y = y + size)
				{
					g.drawRect(x, y, size, size);
				}
			
			//checks and colors in non zero entities
			for(int i = 0; i < NUM_ROWS; i++)
				for( int j = 0; j < NUM_COLS; j++)
				{

					if(matrix[i][j] == 1)						//body
					{
						g.setColor(Color.RED);
						g.fillRect(i*size, j*size, size, size);
					
					}
					else if(matrix[i][j] == -1)					//fruit
					{
						g.setColor(Color.GREEN);
						g.fillRect(i*size, j*size, size, size);
					
					}
				}
			
			//Speed up directions
			
			//"Game Over" screen
			if(Snake_CLussier.gameOver)
			{
				for(int i = 0; i < NUM_ROWS; i++)
					for( int j = 0; j < NUM_COLS; j++)
					{
							g.setColor(Color.BLACK);
							g.fillRect(i*size, j*size, size, size);
					}
				//prints "Game Over"
				g.setColor(Color.RED);
				Font font = new Font("Times New Roman", Font.PLAIN, 100);
				g.setFont(font);
				g.drawString("Game Over", (maxHeight*9/44), maxWidth/2);
			}
			
			//Speed up print
			Font font = new Font("Times New Roman", Font.PLAIN, 20);
			g.setFont(font);
			g.setColor(Color.CYAN);
			g.drawString("press ' , ' to slow down", (maxHeight/8), maxWidth+50);
			g.drawString("press ' . ' to speed up", (maxHeight/8), maxWidth+100);
			
		}
}