package org.bcit.comp2522.project;

import processing.core.PImage;
import processing.core.PVector;

/**
 * Background elements.
 */
public class Background extends Movable {

  /**
   * The sticker image of the background.
   */
  private PImage groundImg;

  /**
   * The image width.
   */
  private final float WIDTH = 925;

  /**
   * The image height.
   */
  private final float HEIGHT = 720;

  /**
   * Background constructor.
   *
   * @param img image of the background
   * @param position position of the image
   * @param moveSpeed moving speed of the background as a float
   * @param window main screen window
   */
  public Background(PImage img, PVector position, float moveSpeed, Window window) {
    this.groundImg = img;
    this.vx = moveSpeed;
    this.window = window;
    this.position = position;
  }

  /**
   * Gets width of the image.
   *
   * @return WIDTH
   */
  public float getWidth() {
    return this.WIDTH;
  }

  /**
   * Gets Height of the image.
   *
   * @return HEIGHT
   */
  public float getHeight() {
    return this.HEIGHT;
  }

  /**
   * draws the ground image.
   */
  public void draw() {
    window.image(groundImg, this.position.x, this.position.y, WIDTH, HEIGHT);
  }

  /**
   * moves the position of the background image.
   */
  public void move() {
    if (running) {
      this.position.x += vx;
      this.position.y += vy;
    }
  }
}
