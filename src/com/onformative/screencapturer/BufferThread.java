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
class BufferThread extends Thread {
  public int wait;
  private boolean running;
  public static Robot robot;

  /**
   * the constructor initializes the buffer and only takes the destinated waiting time
   * 
   * @param wait
   */
  public BufferThread(float wait) {
    this.wait = (int) wait;
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
    running = false;
    System.out.println("ScreenCapturer thread quit.");
    interrupt();
  }

  /**
   * sends a new capture to the screen class
   */
  private void updateImage() {
    Screen.setCurrentImage(getImage(true));
    Screen.setOriginalImage(getImage(false));
  }

  /**
   * takes a new capture of the desinated area
   * 
   * @return PImage a pimage of the new capture
   */
  private PImage getImage(boolean toBeResized) {
    PImage currentImage = new PImage();
    if (Screen.getWindow() != null) {
      try {
        BufferedImage image = null;
        if (System.getProperty("os.name").contains("OS X")) {
          image =
              robot.createScreenCapture(new Rectangle(Screen.getWindow().getLocation().x + 5,
                  Screen.getWindow().getLocation().y + 27, Screen.getWindow().getWidth() - 10,
                  Screen.getWindow().getHeight() - 32));
        } else {
          image =
              robot.createScreenCapture(new Rectangle(Screen.getWindow().getLocation().x + 5,
                  Screen.getWindow().getLocation().y + 28, Screen.getWindow().getWidth() - 10,
                  Screen.getWindow().getHeight() - 33));
        }
        currentImage = new PImage(image);
      } catch (NullPointerException e) {
        e.printStackTrace();
      }
      if (toBeResized) {
        currentImage.resize(Screen.width, Screen.height);
      }
    }
    return currentImage;
  }
}
