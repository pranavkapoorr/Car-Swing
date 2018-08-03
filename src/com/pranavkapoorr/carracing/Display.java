package com.pranavkapoorr.carracing;

import java.awt.Canvas;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class Display{
	private String title;
	private int width, height;
	private JFrame jFrame;
	public static KeyListener key;
	public static Canvas canvas;
	
	public Display(String title, int width, int height) {
		this.title = title;
		this.width = width;
		this.height = height;
		canvas = new Canvas();
		key = new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}
			@Override
			public void keyReleased(KeyEvent e) {
			}
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()== KeyEvent.VK_RIGHT)
		            GameSetUp.x += 5;
		        else if(e.getKeyCode()== KeyEvent.VK_LEFT)
		        	GameSetUp.x -= 5;
		        else if(e.getKeyCode()== KeyEvent.VK_DOWN)
		        	GameSetUp.y +=5;
		        else if(e.getKeyCode()== KeyEvent.VK_UP)
		        	GameSetUp.y -=5;
			}
		};
		createDisplay();
	}
	
	private void createDisplay(){
		jFrame = new JFrame(title);
		jFrame.setSize(width, height);
		jFrame.add(canvas);
		jFrame.addKeyListener(key);
		jFrame.setVisible(true);
	}
}