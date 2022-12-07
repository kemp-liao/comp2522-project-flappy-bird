package org.bcit.comp2522.project;

import processing.core.PImage;
import processing.core.PVector;

/**
 * Extra lives of the user.
 */
public class Heart {

  /**
   * The width of the heart image. Fixed number.
   */
  private final float WIDTH = 30;

  /**
   * The height of heart image. Fixed number.
   */
  private final float HEIGHT = 30;

  /**
   * position of the heart image.
   */
  private final PVector position;

  /**
   * main screen.
   */
  private final Window window;

  /**
   * The image of heart.
   */
  private PImage heartImg;

  /**
    * Heart constructor.
    *
    * @param position as PVector.
    *
    * @param window as window.
    */
  public Heart(PVector position, PImage image, Window window) {
    this.position = position;
    this.heartImg = image;
    this.window = window;
  }

  /**
   * Draw the Heart.
   */
  public void draw() {
    window.image(heartImg, position.x, position.y, this.WIDTH, this.HEIGHT);
  }
}
