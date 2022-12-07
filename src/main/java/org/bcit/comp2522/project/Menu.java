package org.bcit.comp2522.project;

import processing.core.PConstants;
import processing.core.PFont;
import processing.core.PImage;
import processing.core.PVector;

/**
 * main menu page.
 */
public class Menu extends Text{

  /**
   * size of the text.
   */
  private final int textSize = 70;

  /**
   * bird image.
   */
  private PImage bird;

  /**
   * main menu constructor.
   *
   * @param position as PVector
   * @param font style of the text
   * @param window this window
   */
  public Menu(PVector position, PFont font, Window window) {
    this.position = position;
    this.window = window;
    this.bird = window.loadImage("./img/bird.png");
    this.font = window.createFont("./font/ARCADE.TTF", 128);
  }


  /**
   * draws the main menu.
   */
  public void draw() {
    window.image(bird, this.position.x-61f,this.position.y-170, 122f, 86f);
    window.textFont(font);
    window.textSize(this.textSize);
    window.textAlign(PConstants.CENTER);
    window.text("Flappy Bird", this.position.x, this.position.y);
  }
}
