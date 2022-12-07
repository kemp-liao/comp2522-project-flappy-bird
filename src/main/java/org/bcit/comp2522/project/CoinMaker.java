package org.bcit.comp2522.project;

import processing.core.PImage;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * CoinMaker that keeps making coin elements for the game.
 */
public class CoinMaker {

    /**
     * How often coin is created.
     */
    private int period;

    /**
     * The width of the coin.
     */
    private float width;

    /**
     * The image of coin.
     */
    private PImage coinImg;

    /**
     * The x coordinate of starting point for the coins.
     */
    private float startX;

    /**
     * The uppermost bound of the gap.
     */
    private final float UPPER_BOUND = 50;

    /**
     * The lowermost bound of the gap.
     */
    private final float LOWER_BOUND = 600;

    /**
     * Coin moving speed.
     */
    private float movingSpeed;

    /**
     * This window.
     */
    Window window;

    /**
     * Starting point of all coins.
     */
    PVector start;

    /**
     * Adding query for coins.
     */
    public ArrayList<Coin> addCoinQuery = new ArrayList<>();

    /**
     * Removing query for coins.
     */
    public ArrayList<Coin> removeCoinQuery = new ArrayList<>();

    /**
     * Only runs when it is true.
     */
    boolean running = true;

    /**
     * Timer to create new coins.
     */
    private Timer t;

    /**
     * CoinMaker constructor.
     *
     * @param period creating period, as integer.
     *
     * @param width cylinder width, as float.
     *
     * @param movingSpeed as float.
     *
     * @param img as PImage.
     *
     * @param window as window.
     */
    public CoinMaker(int period, float width, float movingSpeed, PImage img, Window window) {
      this.period = period;
      this.width = width;
      this.movingSpeed = movingSpeed;
      this.startX = window.width + this.width/2;
      this.window = window;
      this.coinImg = img;
    }

    /**
     * Creates one set of coins.
     */
    public void make() {
      start = new PVector(startX, (float)(Math.random()*(LOWER_BOUND-UPPER_BOUND+1)+UPPER_BOUND));
      Coin c = new Coin(start, movingSpeed, window);
      c.setCoinImg(coinImg);
      this.addCoinQuery.add(c);
    }

    /**
     * Runs the coin maker.
     */
    public void run() {
      t = new Timer();
      t.schedule(new TimerTask() {
        @Override
        public void run() {
          make();
        }
      }, (int)(-movingSpeed/2)*1300, period);
    }

    /**
     * Removes the coin from the list if it is out of bound.
     */
    public void remove() {
      for (Coin rm : window.coins) {
        if (rm.getPosition().x < (0 - this.width/2)) {
          this.removeCoinQuery.add(rm);
        }
      }
    }

  /**
   * Removes coin image.
   */
  public void removeCoinImage() {
      for (Coin rm : window.coins) {
        this.removeCoinQuery.add(rm);
      }
    }

    /**
     * Stop running.
     */
    public void stop() {
      this.running = false;
      t.cancel();
      for (Coin c : window.coins) {
        c.setRunning(running);
      }
    }
  }


