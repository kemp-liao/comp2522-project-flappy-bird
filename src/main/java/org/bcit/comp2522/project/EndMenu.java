package org.bcit.comp2522.project;

import processing.core.PConstants;
import processing.core.PFont;
import processing.core.PImage;
import processing.core.PVector;

/**
 * End menu page.
 */
public class EndMenu extends Text{

  /**
   * size of the text.
   */
  private final int textSize = 40;

  /**
   * game over image.
   */
  private PImage gameOver;

  /**
   * end menu constructor.
   *
   * @param position as PVector
   * @param font style of text
   * @param window this window
   */
  public EndMenu(PVector position, PFont font, Window window) {
    this.position = position;
    this.window = window;
    this.gameOver = window.loadImage("./img/gameover.png");
    this.font = font;
  }

  /**
   * draws the end menu.
   */
  public void draw() {
    window.image(gameOver, this.position.x-61f,this.position.y-170, 122f, 86f);
    window.textFont(font);
    window.textSize(this.textSize);
    window.textAlign(PConstants.CENTER);
    window.text("Total Score: " + this.window.score.getScore()
            + "\nTotal Coin Score: " + this.window.coinscore.getCoinScore(), this.position.x, this.position.y);
  }
}
