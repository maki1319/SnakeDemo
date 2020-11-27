package SnakeDemo;

import java.util.LinkedList;
import java.util.Random;

import javax.swing.JOptionPane;

public class Snake {
	
	private LinkedList<Node> snakeBody;
	private Random random;
	//Snake�ķ���    0�ϣ� 1 �� �� 2 �� �� 3 ��
	private int direction = 0;  
	
	private SnakeGameView snakeGameView;
	
	public Snake(int snakeHeadX, int snakeHeadY, SnakeGameView snakeGameView) {
		random = new Random();
		snakeBody = new LinkedList<>();
		snakeBody.add(new Node(snakeHeadX, snakeHeadY));
		this.snakeGameView = snakeGameView;
	}
	
	//��ȡSnake�ķ���
	public int getDirection() {
		
		return direction;
	}
	//����Snake����
	public void setDirection(int direction) {
		
		this.direction = direction;
	}
	//��ȡSnakeͷ�����
	public Node getSnakeHead() {
		
		return snakeBody.getFirst();
	}
	//��ȡSnakeβ�����
	public Node getSnakeTail() {
		
		return snakeBody.getLast();
	}
	//��ȡSnake��Body
	public LinkedList<Node> getSnakeBody(){
		
		return snakeBody;
	}
	//Snake�ƶ�
	public void snakeMove() {
		
		switch(direction) {
		case 0:
			snakeBody.addFirst(new Node(getSnakeHead().getNodeX() ,getSnakeHead().getNodeY() - DrawView.VIEW_NUMBER));
			break;
		case 1:
			snakeBody.addFirst(new Node(getSnakeHead().getNodeX() + DrawView.VIEW_NUMBER,getSnakeHead().getNodeY()));
			break;
		case 2:
			snakeBody.addFirst(new Node(getSnakeHead().getNodeX(),getSnakeHead().getNodeY()+ DrawView.VIEW_NUMBER));
			break;
		case 3:
			snakeBody.addFirst(new Node(getSnakeHead().getNodeX() - DrawView.VIEW_NUMBER,getSnakeHead().getNodeY()));
			break;
		}
		snakeBody.removeLast();
	}
	//Snake ��Egg
	public void eatEgg(Node egg) {
		if(snakeBody.getFirst().getNodeX() == egg.getNodeX() && snakeBody.getFirst().getNodeY() == egg.getNodeY()) {
		   snakeBody.add(egg);
		   //����
		   snakeGameView.setEgg(random.nextInt(DrawView.VIEW_WIDTH - 1) * DrawView.VIEW_NUMBER,
					random.nextInt(DrawView.VIEW_WIDTH - 1) * DrawView.VIEW_NUMBER);
		   SnakeGameView.gameScore = SnakeGameView.gameScore + 5;
		   snakeGameView.getJTextArea().setText(SnakeGameView.gameScore + "");
		   System.out.println("�Ե�����");
		}
	}
	//SnakeԽ��
	public void snakeRunInterface() {
		
		if(this.getSnakeHead().getNodeX() < 0 || this.getSnakeHead().getNodeY() < 0||
				this.getSnakeHead().getNodeX() > (DrawView.VIEW_WIDTH * DrawView.VIEW_NUMBER)||
				this.getSnakeHead().getNodeY() > (DrawView.VIEW_WIDTH * DrawView.VIEW_NUMBER)) {
			SnakeGameView.gameState = false;
			JOptionPane.showMessageDialog(null, "��Ϸ����","snake",JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
	}
}