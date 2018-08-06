package com.pranavkapoorr.carracing;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO;

public class GameSetUp implements Runnable{
	private Thread gameThread;
	private Display display;
	private Graphics graphics;
	private String title;
	private int width,height;
	public static volatile int carX,carY,grassX,grassY,roadX,roadY;
	BufferedImage carImage;
	BufferedImage grassImage;
	BufferedImage roadImage;
	
	
	public GameSetUp(String title, int width, int height) {
		this.title = title;
		this.width = width;
		this.height = height;
		init();
	}
	private void init(){
		carX = 160;
		carY = height-160;
		roadX = 151;
		roadY = 0;
		grassY = 0;
		loadGrass();
		loadRoad();
		loadCar();
		display = new Display(title, width, height);
	}
	private void loadRoad() {
		try {
			roadImage = ImageIO.read(new File("src/resources/road.jpg"));
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}
	private void loadCar() {
		try {
			carImage = ImageIO.read(new File("src/resources/Car.png"));
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}
	private void loadGrass() {
		try {
			grassImage = ImageIO.read(new File("src/resources/grass.jpg"));
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
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
		graphics = Display.mainPanel.getGraphics();
		graphics.clearRect(0, 0, width, height);

		drawGrass(graphics);
		drawRoad(graphics);
		
		graphics.drawImage(carImage, carX, carY, 40, 60,null);
		
		graphics.dispose();
	}
	public void drawGrass(Graphics graphics){
		graphics.drawImage(grassImage, 0, grassY, 150, height,null);
		graphics.drawImage(grassImage, width-150, grassY, 150, height,null);
	}
	public void drawRoad(Graphics graphics){
		graphics.drawImage(roadImage, roadX, roadY, width-300, height,null);
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
