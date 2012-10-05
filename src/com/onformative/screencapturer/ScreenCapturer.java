package com.onformative.screencapturer;

import java.awt.Point;

import processing.core.PImage;

public class ScreenCapturer {

  /**
   * constructor initializes the capturer.
   * 
   * @param w width
   * @param h height
   * @param imageFrameRate target framerate of the capturer
   */
  public ScreenCapturer(int w, int h, int imageFrameRate) {
    Screen.record(w, h, imageFrameRate);
  }

  /**
   * returns an pimage of the capture
   * 
   * @return the image of the capture
   */
  public PImage getImage() {
    return Screen.getImage();
  }

  /**
   * sets the frame visible or invisible
   * 
   * @param bool visible or not
   */
  public void setVisible(boolean bool) {
    Screen.getWindow().setVisible(bool);
  }

  /**
   * returns true if the frame is visible and vice versa
   * 
   * @return
   */
  public boolean isVisible() {
    return Screen.getWindow().isVisible();
  }

  /**
   * sets the size of the capturer
   * 
   * @param w width
   * @param h height
   */
  public void setSize(int w, int h) {
    Screen.getWindow().setSize(w, h);
  }

  /**
   * retuns the current width of the capturer
   * 
   * @return
   */
  public int getWidth() {
    return Screen.getWindow().getWidth();
  }

  /**
   * returns the current height of the capturer
   * 
   * @return
   */
  public int getHeight() {
    return Screen.getWindow().getHeight();
  }

  /**
   * sets the position of the capturer relative to the computer screen
   * 
   * @param x x-coordinate
   * @param y y-coordinate
   */
  public void setLocation(int x, int y) {
    Screen.getWindow().setLocation(x, y);
  }

  /**
   * returns the Location of the applet relative to the screen.
   * 
   * @return java.awt.Point location of the window
   */
  public Point getLocation() {
    return Screen.getWindow().getLocation();
  }
  
}
