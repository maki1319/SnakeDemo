package SnakeDemo;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameControl implements KeyListener{
	 
	private Snake snake;
	
	public GameControl(Snake snake) {
		
		this.snake = snake;
	}
	
	public void keyReleased(KeyEvent ke) {
		
		switch(ke.getKeyCode()) {
		
		case KeyEvent.VK_UP:
			snake.setDirection(0);
			break;
		case KeyEvent.VK_RIGHT:
			snake.setDirection(1);
			break;
		case KeyEvent.VK_DOWN:
			snake.setDirection(2);
			break;
		case KeyEvent.VK_LEFT:
			snake.setDirection(3);
			break;
		case KeyEvent.VK_ENTER:
			
			if(SnakeGameView.gameState == true) {
				SnakeGameView.gameState = false;
				break;
			}
				
			else 
				{
				SnakeGameView.gameState = true;
				break;
				}
		
		case KeyEvent.VK_ESCAPE:
			System.exit(0);
			break;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
 
	
 
}