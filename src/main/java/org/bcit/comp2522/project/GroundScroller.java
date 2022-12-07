package org.bcit.comp2522.project;

import java.util.ArrayList;

import processing.core.PImage;
import processing.core.PVector;

/**
 * The GroundScroller scrolls the ground elements.
 */
public class GroundScroller {

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
  public ArrayList<Ground> addQuery = new ArrayList<>();

  /**
   * Removing query.
   */
  public ArrayList<Ground> removeQuery = new ArrayList<>();

  /**
   * Only run when it is true.
   */
  boolean running = true;

  /**
   * GroundScroller constructor.
   *
   * @param img as PImage.
   *
   * @param moveSpeed as float.
   *
   * @param window as window.
   */
  public GroundScroller(PImage img, float moveSpeed, Window window) {
    this.groundImg = img;
    this.vx = moveSpeed;
    this.window = window;
    PVector position = new PVector(0, window.height-70);
    Ground first = new Ground(groundImg, position, vx, window);
    addQuery.add(first);
  }

  /**
   * Make a new ground sticker.
   */
  public void make() {
    PVector position = new PVector(window.width, window.height-70);
    Ground g = new Ground(groundImg, position, vx, window);
    addQuery.add(g);
  }

  /**
   * Make a new ground sticker if the current one is over.
   */
  public void scroll() {
    if (running) {
      for (Ground g : window.grounds) {
        if (g.getPosition().x < (window.width-g.getWidth()+Math.abs(vx)) && window.grounds.size() < 2) {
          make();
        }
        if (g.getPosition().x < -g.getWidth()) {
          removeQuery.add(g);
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
    for (Ground g : window.grounds) {
      g.setRunning(running);
    }
  }
}
