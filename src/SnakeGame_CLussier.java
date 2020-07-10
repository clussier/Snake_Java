import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;

public class SnakeGame_CLussier extends JFrame
{	
	public static Board_CLussier myBoard;
	public static Snake_CLussier snake;
	private static int timeStep = 40;
	public SnakeGame_CLussier() throws InterruptedException
	{	
		super("Tron");

				myBoard = new Board_CLussier(100, 80);
				snake = new Snake_CLussier(myBoard.NUM_ROWS*5/6 ,myBoard.NUM_COLS/2,3,myBoard);
				
				setLayout(new BorderLayout());
				setDefaultCloseOperation(EXIT_ON_CLOSE);
				setResizable(false);
				add(myBoard, BorderLayout.CENTER);
				setBackground(Color.BLACK);
				pack();
				setLocationRelativeTo(null);
				setVisible(true);
				keyListener();
				runGame();
				myBoard.repaint();
	
	}
	
	public void keyListener()
	{
		addKeyListener(new KeyAdapter()
		{
			public void keyPressed(KeyEvent e) 
			{
				switch (e.getKeyCode())
				{
					case KeyEvent.VK_UP:
						snake.setDir(0); 
						break;
						
					case KeyEvent.VK_DOWN:
						snake.setDir(2); 
						 break;
					
					case KeyEvent.VK_LEFT:
						snake.setDir(3); 
						 break;
					
					case KeyEvent.VK_RIGHT:
						snake.setDir(1); 
						 break;
						 
					case KeyEvent.VK_W:
						snake.setDir(0); 
						 break;
						 
					case KeyEvent.VK_A:
						snake.setDir(3);
						 break;
						 
					case KeyEvent.VK_S:
						snake.setDir(2);
						 break;
						 
					case KeyEvent.VK_D:
						snake.setDir(1);
						 break;
						 
					case KeyEvent.VK_COMMA:				//decrease time step with ","
						timeStep = timeStep + 10;
						break;
						
					case KeyEvent.VK_PERIOD:			//increase time step with "."
						if(timeStep > 10)
						timeStep = timeStep - 10;
						break;

				}
			}
		});
	}
	
	public static void restart()
	{
		
	}
	
	public void runGame() throws InterruptedException
	{
		
		while (snake.checkCollisions())
		{	
			snake.move();
			myBoard.setRandom();
			snake.createSnake();
			myBoard.repaint();
			Thread.sleep(timeStep);
		}
		
	}
	
	public static void main(String[] args) throws InterruptedException
	{
		new SnakeGame_CLussier();
		
	}
	
	
}



