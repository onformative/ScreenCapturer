package com.onformative.screencapturer;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.sun.awt.AWTUtilities;

import processing.core.PImage;

/**
 * Screen.java last edited: 05.10.2012 autor: marcel schwittlick
 * 
 */
@SuppressWarnings("restriction")
class Screen extends JFrame {

  private static final long serialVersionUID = 7180689585663427706L;
  public static int width, height;
  private static float windowRatio;
  private static Screen window;

  private static PImage currentImage;
  private static PImage originalImage;
  private static Thread thread;

  /**
   * the constructor initializes the new jframe of the capturer
   */
  public Screen() {
    super("Screen Capturer 0.1");

    this.getRootPane().addComponentListener(new ComponentAdapter() {
      public void componentResized(ComponentEvent e) {
        if (System.getProperty("os.name").contains("OS X")) {
          System.out.println("ScreenCapturer: Width: " + (getWidth() - 10) + " Height: "
              + (getHeight() - 32) + " Proportion: " + (float) (getWidth() - 10)
              / (float) (getHeight() - 32));

        } else {
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

      }
    });



    if (System.getProperty("os.name").contains("OS X")) {
      setSize(new Dimension((int) width + 10, (int) height + 32));
      JPanel panel = new JPanel();
      panel.setPreferredSize(new Dimension(100, 100));
      panel.setBorder(BorderFactory.createLineBorder(Color.black, 5));
      add(panel);
    } else {
      setSize(new Dimension((int) width + 10, (int) height + 33));
    }

    setLocationRelativeTo(null);
    setMinimumSize(new Dimension(50, 50));
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setAlwaysOnTop(true);
  }

  public void paintComponent(Graphics g) {
    super.paint(g);
    g.fillRect(20, 20, 40, 40);
  }

  /**
   * starts the capturer
   * 
   * @param w width of the capture
   * @param h height of the capture
   * @param imageFrameRate destinated framerate of the capture
   */
  public static void record(final int w, final int h, final float imageFrameRate) {
    Screen.width = w;
    Screen.height = h;
    windowRatio = width / height;
    JFrame.setDefaultLookAndFeelDecorated(true);

    // Create the GUI on the event-dispatching thread
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        window = new Screen();
        window.setAlwaysOnTop(true);
        if (!System.getProperty("os.name").contains("OS X")) {
          window.setUndecorated(true);
        }
        window.setVisible(true);
        window.setBackground(new Color(0, 0, 0, 0));
        window.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width - window.getWidth(), 0);
        if (System.getProperty("os.name").contains("OS X")) {
          window.getRootPane().putClientProperty("Window.shadow", Boolean.FALSE);
          window.getRootPane().putClientProperty("Window.style", "medium");
        } else {
          AWTUtilities.setWindowOpaque(window, false);
        }

        setThread(new BufferThread(1000 / imageFrameRate));
      }
    });
  }

  /**
   * starts the capturer setting its position on the screen aswell
   * 
   * @param w width
   * @param h height
   * @param x xpos
   * @param y ypos
   * @param imageFrameRate
   */
  public static void record(final int w, final int h, final int x, final int y,
      final float imageFrameRate) {
    Screen.width = w;
    Screen.height = h;
    windowRatio = width / height;
    JFrame.setDefaultLookAndFeelDecorated(true);

    // Create the GUI on the event-dispatching thread
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        window = new Screen();
        window.setAlwaysOnTop(true);
        if (!System.getProperty("os.name").contains("OS X")) {
          window.setUndecorated(true);
        }
        window.setLocation(x, y);
        window.setVisible(true);
        window.setBackground(new Color(0, 0, 0, 0));
        if (System.getProperty("os.name").contains("OS X")) {
          window.getRootPane().putClientProperty("Window.shadow", Boolean.FALSE);
          window.getRootPane().putClientProperty("Window.style", "medium");
        } else {
          AWTUtilities.setWindowOpaque(window, false);
        }

        setThread(new BufferThread(1000 / imageFrameRate));
      }
    });
  }

  public static PImage getImage() {
    if (currentImage == null) currentImage = new PImage();
    return currentImage;
  }

  public static PImage getOriginalImage() {
    if (originalImage == null) originalImage = new PImage();
    return originalImage;
  }

  public static void setCurrentImage(PImage currentImage) {
    Screen.currentImage = currentImage;
  }

  public static void setOriginalImage(PImage originalImage) {
    Screen.originalImage = originalImage;
  }

  public static Screen getWindow() {
    return window;
  }

  public static void setWindow(JFrame frame) {
    window = (Screen) frame;
  }

  public static Thread getThread() {
    return thread;
  }

  public static void setThread(Thread thread) {
    Screen.thread = thread;
  }
}
