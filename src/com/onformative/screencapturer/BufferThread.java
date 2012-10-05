package com.onformative.screencapturer;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * Bufferthread.java last edited: 05.10.2012 author: marcel schwittlick
 * 
 */
public class BufferThread extends Thread {
  public int wait;
  private boolean running;
  private static Robot robot;

  /**
   * the constructor initializes the buffer and only takes the destinated waiting time
   * 
   * @param wait
   */
  public BufferThread(int wait) {
    this.wait = wait;
    running = false;
    try {
      robot = new Robot();
    } catch (AWTException e) {
      System.out.println("Couldn't instantiate Robot class.");
      e.printStackTrace();
    }

    start(); // starting the thread when initializing
  }

  /**
   * starts the thread
   */
  public void start() {
    running = true;
    super.start();
  }

  /**
   * gets executed once and runs as long as the running boolean is set to true
   */
  public void run() {
    while (running) {
      updateImage();
      try {
        sleep(wait);
      } catch (Exception e) {
        PApplet.println(e);
      }
    }
  }

  public void quit() {
    System.out.println("ScreenCapturer thread quit.");
  }

  /**
   * sends a new capture to the screen class
   */
  private void updateImage() {
    Screen.setCurrentImage(getImage());
  }

  /**
   * takes a new capture of the desinated area
   * 
   * @return PImage a pimage of the new capture
   */
  private PImage getImage() {
    PImage currentImage = new PImage();
    if (Screen.getWindow() != null) {
      try {
        BufferedImage image =
            robot.createScreenCapture(new Rectangle(Screen.getWindow().getLocation().x + 5, Screen
                .getWindow().getLocation().y + 28, Screen.getWindow().getWidth() - 10, Screen
                .getWindow().getHeight() - 33));
        currentImage = new PImage(image);
      } catch (NullPointerException e) {
        e.printStackTrace();
      }
      currentImage
          .resize((int) Screen.getWindow().getWidth(), (int) Screen.getWindow().getHeight());
    }
    return currentImage;
  }
}
