package org.bcit.comp2522.project;

import processing.core.PImage;
import processing.core.PVector;

/**
 * Ground elements.
 */
public class Ground extends Movable implements Collidable{

  /**
   * The sticker image of the ground.
   */
  private PImage groundImg;

  /**
   * The image width.
   */
  private final float WIDTH = 925;

  /**
   * The image height.
   */
  private final float HEIGHT = 160;

  public Ground(PImage img, PVector position, float moveSpeed, Window window) {
    this.groundImg = img;
    this.vx = moveSpeed;
    this.window = window;
    this.position = position;
  }


  public float getWidth() {
    return this.WIDTH;
  }

  public float getHeight() {
    return this.HEIGHT;
  }

  public void draw() {
    window.image(groundImg, this.position.x, this.position.y, WIDTH, HEIGHT);
  }

  public void move() {
    if (running) {
      this.position.x += vx;
      this.position.y += vy;
    }
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
    return new BoundingBox(this.position, this.WIDTH, this.HEIGHT, window);
  }
}
