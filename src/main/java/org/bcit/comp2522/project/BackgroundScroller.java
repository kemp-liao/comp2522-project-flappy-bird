package org.bcit.comp2522.project;

import processing.core.PImage;
import processing.core.PVector;

import java.util.ArrayList;

/**
 * The BackgroundScroller scrolls the background elements.
 */
public class BackgroundScroller {

  /**
   * The image of the ground.
   */
  protected PImage groundImg;

  /**
   * Scrolling speed.
   */
  protected float vx;

  /**
   * This window.
   */
  protected Window window;

  /**
   * Adding query.
   */
  public ArrayList<Background> addQuery = new ArrayList<>();

  /**
   * Removing query.
   */
  public ArrayList<Background> removeQuery = new ArrayList<>();

  /**
   * Only run when it is true.
   */
  boolean running = true;

  /**
   * BackgroundScroller constructor.
   *
   * @param img as PImage.
   *
   * @param moveSpeed as float.
   *
   * @param window as window.
   */
  public BackgroundScroller(PImage img, float moveSpeed, Window window) {
    this.groundImg = img;
    this.vx = moveSpeed;
    this.window = window;
    PVector position = new PVector(0, 0);
    Background first = new Background(groundImg, position, vx, window);
    addQuery.add(first);
  }

  /**
   * Make a new background sticker.
   */
  public void make() {
    PVector position = new PVector(window.width, 0);
    Background g = new Background(groundImg, position, vx, window);
    addQuery.add(g);
  }

  /**
   * Make a new background sticker if the current one is over.
   */
  public void scroll() {
    if (running) {
      for (Background b : window.backgrounds) {
        if (b.getPosition().x < (window.width-b.getWidth()+Math.abs(vx)) && window.backgrounds.size() < 2) {
          make();
        }
        if (b.getPosition().x < -b.getWidth()) {
          removeQuery.add(b);
        }
      }
    }
  }

  /**
   * Stop running.
   *
   */
  public void stop() {
    this.running = false;
    for (Background b : window.backgrounds) {
      b.setRunning(running);
    }
  }
}
