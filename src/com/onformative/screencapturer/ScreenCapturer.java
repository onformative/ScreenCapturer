package com.onformative.screencapturer;

import java.awt.Point;

import javax.swing.JFrame;

import processing.core.PImage;

/**
 * ScreenCapturer.java last edit: 05.10.2012 author: marcel schwittlick
 * 
 */
public class ScreenCapturer {

  /**
   * constructor initializes the capturer.
   * 
   * @param w width
   * @param h height
   * @param imageFrameRate target framerate of the capturer
   */
  public ScreenCapturer(int w, int h, float imageFrameRate) {
    Screen.record(w, h, imageFrameRate);
  }

  /**
   * second constructor in order to set the position of the framre
   * 
   * @param w width
   * @param h height
   * @param x x pos
   * @param y y pos
   * @param imageFrameRate target framerate of the capturer
   */
  public ScreenCapturer(int w, int h, int x, int y, float imageFrameRate) {
    Screen.record(w, h, x, y, imageFrameRate);
  }

  /**
   * returns an pimage of the capture
   * 
   * @return the image of the capture
   */
  public PImage getImage() {
    return Screen.getImage();
  }

  public PImage getOriginalImage() {
    return Screen.getOriginalImage();
  }

  /**
   * sets the frame visible or invisible
   * 
   * @param bool visible or not
   */
  public void setVisible(boolean bool) {
    if (Screen.getWindow() != null) {
      Screen.getWindow().setVisible(bool);
    }
  }

  /**
   * returns true if the frame is visible and vice versa
   * 
   * @return
   */
  public boolean isVisible() {
    if (Screen.getWindow() != null) {
      return Screen.getWindow().isVisible();
    } else {
      return false;
    }
  }

  /**
   * sets the size of the capturer
   * 
   * @param w width
   * @param h height
   */
  public void setSize(int w, int h) {
    if (Screen.getWindow() != null) {
      Screen.getWindow().setSize(w, h);
    }
  }

  /**
   * retuns the current width of the capturer
   * 
   * @return
   */
  public int getWidth() {
    return Screen.width;
  }

  /**
   * returns the current height of the capturer
   * 
   * @return
   */
  public int getHeight() {
    return Screen.height;
  }

  /**
   * sets the position of the capturer relative to the computer screen
   * 
   * @param x x-coordinate
   * @param y y-coordinate
   */
  public void setLocation(int x, int y) {
    if (Screen.getWindow() != null) {
      Screen.getWindow().setLocation(x, y);
    }
  }

  /**
   * returns the Location of the applet relative to the screen.
   * 
   * @return java.awt.Point location of the window
   */
  public Point getLocation() {
    if (Screen.getWindow() != null) {
      return Screen.getWindow().getLocation();
    } else {
      return new Point(0, 0);
    }
  }

  /**
   * sets if the ScreenCapturer is always on top
   * 
   * @param isAlwaysOnTop boolean
   */
  public void setAlwaysOnTop(boolean isAlwaysOnTop) {
    Screen.getWindow().setAlwaysOnTop(isAlwaysOnTop);
  }

  /**
   * returns if the ScreenCapturer is always on top. default is true;
   * 
   * @return boolean
   */
  public boolean setAlwaysOnTop() {
    return Screen.getWindow().isAlwaysOnTop();
  }

  /**
   * returns the JFrame of the ScreenCapturer. can be used to furtherly customize the JFrame.
   * 
   * @return
   */
  public JFrame getWindow() {
    return Screen.getWindow();
  }

  /**
   * sets the jframe of the screencapturer. should be only used if you know what you are doing.
   * 
   * @param frame
   */
  public void setWindow(JFrame frame) {
    Screen.setWindow(frame);
  }
}
