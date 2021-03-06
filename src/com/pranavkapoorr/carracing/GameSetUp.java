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
	public static volatile int carX,carY,grassX,grassY,roadX,roadY,grassWidth,carWidth,roadWidth;
	BufferedImage carImage;
	BufferedImage grassImage;
	BufferedImage roadImage;
	public static volatile boolean gameOver;
	
	
	public GameSetUp(String title, int width, int height) {
		this.title = title;
		this.width = width;
		this.height = height;
		init();
	}
	private void init(){
		gameOver = false;
		carX = 160;
		grassWidth = 150;
		carY = height-160;
		roadX = grassWidth + 1;
		roadY = -10000;
		grassY = -10000;
		carWidth = 40;
		roadWidth = 2 * grassWidth;
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
		
		graphics.drawImage(carImage, carX, carY, carWidth, 60,null);
		
		graphics.dispose();
	}
	public void drawGrass(Graphics graphics){
		for(int i = roadY; i <= 10000; i+= grassImage.getHeight()){
			graphics.drawImage(grassImage, 0, i, grassWidth, grassImage.getHeight(),null);
			graphics.drawImage(grassImage, width-grassWidth, i, 150, grassImage.getHeight(),null);
		}
	}
	public void drawRoad(Graphics graphics){
		
		for(int i = roadY; i <= 10000; i += roadImage.getHeight()){
			graphics.drawImage(roadImage, roadX, i, roadWidth, roadImage.getHeight(),null);
		}
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
