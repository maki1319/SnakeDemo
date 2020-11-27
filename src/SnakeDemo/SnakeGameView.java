package SnakeDemo;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;

import javax.swing.*;

public class SnakeGameView extends Frame{
	
	private JLabel jl;
	private DrawView drawView;
	private Panel panel;
	private JLabel scoreTitle;
	private JLabel score;
	private Panel rule;
	private JTextArea hint;
	
	private Random random;
	public static boolean gameState = true;
	public static int gameScore = 0;
	private Snake snake;
	private Node egg;
	private GameRunThread grt;
	private static Thread thread;
	
	public SnakeGameView() {
		
		random = new Random();
		//��ʼ��Snake
		snake = new Snake(10 * DrawView.VIEW_NUMBER + random.nextInt(19) * DrawView.VIEW_NUMBER,
				10 * DrawView.VIEW_NUMBER + random.nextInt(19) * DrawView.VIEW_NUMBER,this);
		//��ʼ��Egg
		egg = new Node(random.nextInt(DrawView.VIEW_WIDTH - 1) * DrawView.VIEW_NUMBER,
				random.nextInt(DrawView.VIEW_WIDTH - 1) * DrawView.VIEW_NUMBER);
		//��ʼ������
		drawView = new DrawView(snake, egg);
		grt = new GameRunThread(drawView, snake);
		thread = new Thread(grt);	
		
		jl = new JLabel("SnakeGame",JLabel.CENTER);	
		panel = new Panel();
		scoreTitle = new JLabel("Score", JLabel.CENTER);
		score = new JLabel(gameScore + "", JLabel.CENTER);
		rule = new Panel();
		hint = new JTextArea("    ������ʽ\n      ��\n  ��      ��\n      ��\n\n��Esc���˳�����\n\n"
				+ "2017117312 �²�");		
	}
	
	//���õ�
	public void setEgg(int eggX, int eggY) {
		
		this.egg.setNodeX(eggX);
		this.egg.setNodeY(eggY);
	}
	//���
	public JLabel getJTextArea() {
		return score;
	}
	//������
	public static void main(String[] args) {
		
		new SnakeGameView().showView();
		thread.start();
	}
	public void showView() {
		
		jl.setFont(new Font("����", 1, 18));//�������壬0������1����
		jl.setForeground(Color.white);//������ɫ
		jl.setBounds(0, 0, DrawView.VIEW_HEIGHT * DrawView.VIEW_NUMBER + 70, 40);
		drawView.setBackground(new Color(51,51,51));
		drawView.setBounds(20, 50, DrawView.VIEW_WIDTH * DrawView.VIEW_NUMBER + 1, DrawView.VIEW_HEIGHT * DrawView.VIEW_NUMBER + 1);
		panel.setLayout(null);	
		panel.setBackground(new Color(0,102,102));
		panel.setBounds(DrawView.VIEW_WIDTH * DrawView.VIEW_NUMBER + 40, 50, 150, DrawView.VIEW_HEIGHT * DrawView.VIEW_NUMBER);	
		scoreTitle.setFont(new Font("����", 1, 18));//�������壬0������1����
		scoreTitle.setForeground(Color.white);//������ɫ
		scoreTitle.setBounds(0, 0, 150, 50);
		score.setFont(new Font("����", 1, 20));//�������壬0������1����
		score.setForeground(Color.white);//������ɫ
		score.setBounds(0, 50, 150, 40);
		rule.setBackground(new Color(0,128,128));
		rule.setBounds(0, 90, 150, 1);
		hint.setFont(new Font("����", 0, 16));//�������壬0������1����
		hint.setBounds(10, 100, 130, 150);
		hint.setBackground(new Color(0,102,102));
		
		panel.add(scoreTitle);
		panel.add(score);
		panel.add(rule);
		panel.add(hint);	
		this.add(jl);
		this.setTitle("SnakeGame");
		this.setSize(DrawView.VIEW_WIDTH * DrawView.VIEW_NUMBER + 210, DrawView.VIEW_HEIGHT * DrawView.VIEW_NUMBER + 70);
		this.setLocation(500, 200);
		this.setLayout(null);
		this.setBackground(new Color(0,128,128));
		this.add(drawView);
		this.add(panel);
		this.addKeyListener(new GameControl(snake));
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				System.exit(0);
			}
		});
		this.setVisible(true);
		
	}
 
}