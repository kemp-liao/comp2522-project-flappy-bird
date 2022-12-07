package org.bcit.comp2522.project;

import processing.core.PConstants;
import processing.core.PFont;
import processing.core.PVector;

/**
 * The Score shows the current score on the main screen.
 */
public class Score extends Text {

  /**
   * Score.
   */
  private int score = 0;

  /**
   * Score constructor.
   *
   * @param position as PVector.
   *
   * @param font as PFont.
   *
   * @param textSize as integer.
   *
   * @param window as window.
   */
  public Score(PVector position, PFont font, int textSize, Window window) {
    this.position = position;
    this.font = font;
    this.textSize = textSize;
    this.window = window;
  }

  /**
   * Draw the score.
   */
  public void draw() {
    window.textFont(font);
    window.textSize(textSize);
    window.textAlign(PConstants.CENTER);
    window.text(score, this.position.x, this.position.y);
  }

  /**
   * Score setter.
   *
   * @param score as integer.
   */
  public void setScore(int score) {
    this.score = score;
  }

  public int getScore() {
    return this.score;
  }

  /**
   * Add score by one.
   */
  public void scorePlusOne() {
    this.score += 1;
  }
}
