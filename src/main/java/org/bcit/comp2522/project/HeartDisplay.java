package org.bcit.comp2522.project;

import java.util.ArrayList;
import processing.core.PImage;
import processing.core.PVector;

/**
 * The number of hearts shows on the main screen.
 */
public class HeartDisplay extends Text {

  /**
   * The image of heart.
   */
  private PImage heartImg;

  /**
   * first life.
   */
  private PVector heart1;

  /**
   * second life.
   */
  private PVector heart2;

  /**
   * third life.
   */
  private PVector heart3;

  /**
   * HeartDisplay constructor.
   *
   * @param img img of heart.
   * @param window as window.
   */
  public HeartDisplay(PImage img, Window window) {
    this.heartImg = img;
    this.window = window;
  }

  /**
   * Draws hearts according to the number of remaining life.
   */
  public void draw() {
    heart1 = new PVector(15, 25);
    heart2 = new PVector(45, 25);
    heart3 = new PVector(75, 25);
    Heart firstHeart = new Heart(heart1, heartImg, window);
    Heart secondHeart = new Heart(heart2, heartImg, window);
    Heart thirdHeart = new Heart(heart3, heartImg, window);
    if (window.lifeState == 3) {
      firstHeart.draw();
      secondHeart.draw();
      thirdHeart.draw();
    } else if (window.lifeState == 2) {
      firstHeart.draw();
      secondHeart.draw();
    } else {
      firstHeart.draw();
    }
  }
}

