package org.bcit.comp2522.project;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

/**
 * Cylinder element that consists of upper and lower cylinders.
 */
public class Cylinder extends Movable implements Collidable {

  /**
   * The gap between upper and lower cylinders.
   */
  private float gap;

  /**
   * The width of the cylinders.
   */
  private float width;

  /**
   * The height of upper or lower cylinders. Fixed number.
   */
  private final float HEIGHT = 600;

  /**
   * The image of cylinder.
   */
  private PImage cylinderImg;

  /**
   * TRUE = PASSED; FALSE = UN-PASSED.
   */
  private boolean passed;

  /**
   * Cylinder constructor.
   *
   * @param position as PVector.
   *
   * @param gap as float.
   *
   * @param width as float.
   *
   * @param movingSpeed as float.
   *
   * @param window as window.
   */
  public Cylinder(PVector position, float gap, float width, float movingSpeed, Window window) {
    this.position = position;
    this.direction = new PVector(-1,0);
    this.gap = gap;
    this.vx = movingSpeed;
    this.width = width;
    this.window = window;
    this.passed = false;
  }

  /**
   * Move the cylinder.
   */
  public void move() {
    if (running) {
      this.position.x += vx;
      this.position.y += vy;
    }
  }

  /**
   * Draw the cylinder.
   */
  public void draw() {
    //Upper cylinder
    window.pushMatrix();
    window.translate(this.position.x, this.position.y - HEIGHT/2 - gap/2);
    window.rotate(PApplet.radians(180));
    window.translate(-width/2,-HEIGHT/2);
    window.image(cylinderImg, 0,0, this.width, this.HEIGHT);
    window.popMatrix();

    //Lower cylinder
    window.image(cylinderImg, this.position.x - width/2, this.position.y + gap/2, this.width, this.HEIGHT);

  }

  /**
   * Set the image of cylinder.
   *
   * @param img as PImage.
   */
  public void setCylinderImg(PImage img) {
    this.cylinderImg = img;
  }

  /**
   * Mark the cylinder as passed.
   */
  public void setAsPassed() {
    this.passed = true;
  }

  /**
   * Get the passed status.
   *
   * @return as boolean.
   */
  public boolean getPassed() {
    return this.passed;
  }

  @Override
  public void collideBehaviour(Collidable c) {

  }

  @Override
  public boolean collided(Collidable c) {
    return false;
  }

  @Override
  public BoundingBox getBoundingBox() {
    return new BoundingBox(this.position, this.width, this.HEIGHT, this.gap, this.window);
  }
}
