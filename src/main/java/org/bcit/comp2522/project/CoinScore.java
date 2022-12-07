package org.bcit.comp2522.project;

import processing.core.PConstants;
import processing.core.PFont;
import processing.core.PVector;

/**
 * The number of coins collected shows on the main screen.
 */
public class CoinScore extends Text {

  /**
   * Coin Score.
   */
  private int coinScore = 0;

   /**
   * Coin Score constructor.
   *
   * @param position as PVector.
   *
   * @param font as PFont.
   *
   * @param textSize as integer.
   *
   * @param window as window.
   */
  public CoinScore(PVector position, PFont font, int textSize, Window window) {
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
    window.textAlign(PConstants.TOP);
    window.text("COIN: " + coinScore, this.position.x, this.position.y);
  }

  /**
   * Score setter.
   *
   * @param coinscore as integer.
   */
  public void setCoinScore(int coinscore) {
      this.coinScore = coinscore;
    }

  public int getCoinScore(){
    return this.coinScore;
  }

  /**
   * Add score by one.
   */
  public void scorePlusOne() {
      this.coinScore += 1;
    }

}

