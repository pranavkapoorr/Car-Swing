package com.pranavkapoorr.carracing;

import java.awt.Canvas;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class Display{
	private String title;
	private int width, height;
	private JFrame jFrame;
	private KeyListener key;
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
				// TODO Auto-generated method stub	
			}
		};
		createDisplay();
	}
	
	private void createDisplay(){
		jFrame = new JFrame(title);
		jFrame.setSize(width, height);
		jFrame.add(canvas);
		jFrame.setVisible(true);
	}
}
