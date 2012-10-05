package com.onformative.screencapturer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.MouseInfo;
import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.sun.awt.AWTUtilities;

import processing.core.PImage;

/**
 * Screen.java last edited: 05.10.2012 autor: marcel schwittlick
 * 
 */
class Screen extends JFrame {

  private static final long serialVersionUID = 7180689585663427706L;
  private static float width, height;
  private static float windowRatio;
  private static Screen window;

  private static PImage currentImage;
  private static Thread thread;

  /**
   * the constructor initializes the new jframe of the capturer
   */
  public Screen() {
    super("Screen Capturer 0.1");
    this.getRootPane().addComponentListener(new ComponentAdapter() {
      public void componentResized(ComponentEvent e) {
        if (MouseInfo.getPointerInfo().getLocation().x < getLocation().x + getWidth() - 50
            && MouseInfo.getPointerInfo().getLocation().y > getLocation().y + getHeight() - 50) {
          // bottom resized
          setSize((int) (getHeight() * windowRatio) + 10, getHeight() + 33);
        } else if (MouseInfo.getPointerInfo().getLocation().x > getLocation().x + getWidth() - 50
            && MouseInfo.getPointerInfo().getLocation().y < getLocation().y + getHeight() - 50) {
          // right resized
          setSize((int) getWidth() + 10, (int) (getWidth() / windowRatio) + 33);
        }
        System.out.println("ScreenCapturer: Width: " + (getWidth() - 10) + " Height: "
            + (getHeight() - 33) + " Proportion: " + (float) (getWidth() - 10)
            / (float) (getHeight() - 33));
      }
    });

    setSize(new Dimension((int) width + 10, (int) height + 33));
    setLocationRelativeTo(null);
    setMinimumSize(new Dimension(50, 50));
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setAlwaysOnTop(true);
  }

  /**
   * starts the capturer
   * 
   * @param w width of the capture
   * @param h height of the capture
   * @param imageFrameRate destinated framerate of the capture
   */
  public static void record(final float w, final float h, final int imageFrameRate) {
    width = w;
    height = h;
    windowRatio = width / height;
    JFrame.setDefaultLookAndFeelDecorated(true);

    // Create the GUI on the event-dispatching thread
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        window = new Screen();
        window.setUndecorated(true);

        if (System.getProperty("os.name").contains("OS X")) {
          window.getRootPane().putClientProperty("Window.alpha", new Float(0.2f));
        }
        // Display the window.
        window.setVisible(true);
        window.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width - window.getWidth(), 0);
        AWTUtilities.setWindowOpaque(window, false);
        window.setBackground(new Color(0, 0, 0, 0));
        thread = new BufferThread(1000 / imageFrameRate);
      }
    });
  }

  /**
   * returns a pimage of the current capture
   * 
   * @return PImage
   */
  public static PImage getImage() {
    if (currentImage == null) currentImage = new PImage();
    return currentImage;
  }

  public static void setCurrentImage(PImage currentImage) {
    Screen.currentImage = currentImage;
  }

  public static Screen getWindow() {
    return window;
  }
}
