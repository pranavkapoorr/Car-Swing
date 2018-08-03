package com.pranavkapoorr.carracing;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class GameSetUp implements Runnable{
	private Thread gameThread;
	private Display display;
	private BufferStrategy bufferStrategy;
	private Graphics graphics;
	private String title;
	private int width,height;
	public static volatile int x,y;
	BufferedImage image;
	
	public GameSetUp(String title, int width, int height) {
		this.title = title;
		this.width = width;
		this.height = height;
		init();
	}
	private void init(){
		x = 0;
		y = 0;
		try {
			image = ImageIO.read(new File("src/resources/Car.png"));
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
		display = new Display(title, width, height);
	}
	private synchronized void start(){
		if(gameThread == null){
			gameThread = new Thread(this);
			gameThread.start();
		}
	}
	private synchronized void stop(){
			try {
				gameThread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	}
	
	public void render() {
		bufferStrategy = Display.canvas.getBufferStrategy();
		if(bufferStrategy == null){
			Display.canvas.createBufferStrategy(3);
			return;
		}
		graphics = bufferStrategy.getDrawGraphics();
		
		graphics.clearRect(0, 0, width, height);
		graphics.setColor(Color.RED);
		graphics.drawImage(image, x, y, 60, 40,null);
		
		
		bufferStrategy.show();
		graphics.dispose();
	}
	
	@Override
	public void run() {
		int fps = 50;
		double timePerTick = 1000000000/fps;
		double delta = 0;
		long current = System.nanoTime();
		while(true){
			delta = delta + (System.nanoTime() - current)/timePerTick;
			current = System.nanoTime();
			if(delta >= 1){
				render();
				delta--;
			}
		}
	}
	

}
