package com.pranavkapoorr.carracing;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;

public class Display{
	private String title;
	private int width, height;
	private JFrame jFrame;
	public static KeyListener key;
	public static JPanel mainPanel;
	private JLabel gameOverLabel;
	
	public Display(String title, int width, int height) {
		this.title = title;
		this.width = width;
		this.height = height;
		key = new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}
			@Override
			public void keyReleased(KeyEvent e) {
			}
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()== KeyEvent.VK_RIGHT){
		            if(GameSetUp.carX <= GameSetUp.roadX + GameSetUp.roadWidth - (GameSetUp.carWidth + GameSetUp.carWidth/2-9)){//-9 to allow gameover
		            	if(GameSetUp.carX == GameSetUp.roadX + GameSetUp.roadWidth - (GameSetUp.carWidth + GameSetUp.carWidth/2-9)){
		            		GameSetUp.gameOver = true;
		            	}
		            	GameSetUp.carX += 15;
		            }
				}else if(e.getKeyCode()== KeyEvent.VK_LEFT){
					if(GameSetUp.carX >= GameSetUp.roadX ){//+6 to allow gameover
						if(GameSetUp.carX == 160){
							GameSetUp.gameOver = true;
						}
						GameSetUp.carX -= 15;
					}
				}else if(e.getKeyCode()== KeyEvent.VK_DOWN){
		        	//GameSetUp.carY +=10;
					GameSetUp.roadY -= 10;
					GameSetUp.grassY -= 5;
		        }else if(e.getKeyCode()== KeyEvent.VK_UP){
		        	//GameSetUp.carY -=10;
					GameSetUp.roadY += 10;
					GameSetUp.grassY += 5;
		        }
			}
		};
		createDisplay();
	}
	
	private void createDisplay(){
		jFrame = new JFrame(title);
		jFrame.setResizable(false);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setSize(600,400);
		mainPanel = new JPanel();
		mainPanel.setBackground(Color.WHITE);
		mainPanel.setSize(width, height);
		jFrame.addKeyListener(key);
		jFrame.getContentPane().add(mainPanel);
		gameOverLabel = new JLabel();
		if(GameSetUp.gameOver){gameOverLabel.setText("Game-Over");}else{gameOverLabel.setText("");}
		gameOverLabel.setForeground(Color.RED);
		mainPanel.add(gameOverLabel);
		jFrame.setVisible(true);
	}
}
