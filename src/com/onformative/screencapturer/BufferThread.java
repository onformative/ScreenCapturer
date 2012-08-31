package com.onformative.screencapturer;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;

import processing.core.PApplet;
import processing.core.PImage;

public class BufferThread extends Thread{
	private int wait;
	private boolean running;
	private static Robot robot;

	public BufferThread(int wait){
		this.wait = wait;
		running = false;
		try {
			robot = new Robot();
		} catch (AWTException e) {
			System.out.println("Couldn't instatiate Robot class.");
			e.printStackTrace();
		}
		
		start(); // starting the thread when initializing
	}
	
	public void start(){
		running = true;
		super.start();
	}
	
	public void run(){
		while (running) {
			updateImage();
			try {
				sleep(wait);
			} catch (Exception e) {
				PApplet.println(e);
			}
		}
	}
	
	public void quit(){
		
	}
	
	private void updateImage(){
		ScreenCapturer.setCurrentImage(getImage());
	}
	
	public PImage getImage() {
		PImage currentImage = new PImage();
		if (ScreenCapturer.getWindow() != null) {
			try {
				BufferedImage image = robot.createScreenCapture(new Rectangle(
						ScreenCapturer.getWindow().getLocation().x + 5,
						ScreenCapturer.getWindow().getLocation().y + 28, ScreenCapturer.getWindow().getWidth() - 10,
						ScreenCapturer.getWindow().getHeight() - 33));
				currentImage = new PImage(image);
			} catch (NullPointerException e) {
				e.printStackTrace();
			}
			currentImage.resize((int)ScreenCapturer.getScreenWidth(), (int)ScreenCapturer.getScreenHeight());
		}
		return currentImage;
	}


}
