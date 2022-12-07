package org.bcit.comp2522.project;

import processing.core.PImage;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * CylinderMaker that keeps making cylinder elements for the game.
 */
public class CylinderMaker {

  /**
   * How often cylinder is created.
   */
  private int period;

  /**
   * The gap between upper and lower cylinder.
   */
  private float gap;

  /**
   * The width of the cylinder.
   */
  private float width;

  /**
   * The image of cylinder.
   */
  private PImage cylinderImg;

  /**
   * The x coordinate of starting point for the cylinders.
   */
  private float startX;

  /**
   * The uppermost bound of the gap.
   */
  private final float UPPER_BOUND = 150;

  /**
   * The lowermost bound of the gap.
   */
  private final float LOWER_BOUND = 550;

  /**
   * Cylinder moving speed.
   */
  private float movingSpeed;

  /**
   * This window.
   */
  Window window;

  /**
   * Starting point of all cylinders.
   */
  PVector start;

  /**
   * Adding query for cylinders.
   */
  public ArrayList<Cylinder> addQuery = new ArrayList<>();

  /**
   * Removing query for cylinders.
   */
  public ArrayList<Cylinder> removeQuery = new ArrayList<>();

  /**
   * Only run when it is true.
   */
  boolean running = true;

  /**
   * Timer to create new cylinders.
   */
  private Timer t;

  /**
   * CylinderMaker constructor.
   *
   * @param period creating period, as integer.
   *
   * @param gap gap between upper and lower cylinders, as float.
   *
   * @param width cylinder width, as float.
   *
   * @param movingSpeed as float.
   *
   * @param img as PImage.
   *
   * @param window as window.
   */
  public CylinderMaker(int period, float gap, float width, float movingSpeed, PImage img, Window window) {
    this.period = period;
    this.gap = gap;
    this.width = width;
    this.movingSpeed = movingSpeed;
    this.startX = window.width + this.width/2;
    this.window = window;
    this.cylinderImg = img;
  }

  /**
   * Create one set of cylinders.
   */
  public void make() {
      start = new PVector(startX, (float)(Math.random()*(LOWER_BOUND-UPPER_BOUND+1)+UPPER_BOUND));
      Cylinder c = new Cylinder(start, gap, width, movingSpeed, window);
      c.setCylinderImg(cylinderImg);
      this.addQuery.add(c);
  }

  /**
   * Run the cylinder maker.
   */
  public void run() {
      t = new Timer();
      t.schedule(new TimerTask() {
        @Override
        public void run() {
          make();
        }
      }, 0, period);
  }

  /**
   * Remove the cylinder from the list if it is out of bound.
   */
  public void remove() {
    for (Cylinder rm : window.cylinders) {
      if (rm.getPosition().x < (0 - this.width/2)) {
        this.removeQuery.add(rm);
      }
    }
  }

  /**
   * Stop running.
   *
   */
  public void stop() {
    this.running = false;
    t.cancel();
    for (Cylinder c : window.cylinders) {
      c.setRunning(running);
    }
  }
}
