package org.bcit.comp2522.project;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

/**
 * The coins that can be collected.
 */
public class Coin extends Movable implements Collidable{

    /**
     * The width of the coins. Fixed number.
     */
    private final float WIDTH = 50;

    /**
     * The height of coins. Fixed number.
     */
    private final float HEIGHT = 50;

    /**
     * The image of coin.
     */
    private PImage coinImg;

    /**
     * TRUE = PASSED; FALSE = UN-PASSED.
     */
    private boolean passed;

    /**
     * Coin constructor.
     *
     * @param position as PVector.
     *
     * @param movingSpeed as float.
     *
     * @param window as window.
     */
    public Coin(PVector position, float movingSpeed, Window window) {
      this.position = position;
      this.vx = movingSpeed;
      this.window = window;
      this.passed = false;
    }

    /**
     * Move the coin.
     */
    public void move() {
      if (running) {
        this.position.x += vx;
        this.position.y += vy;
      }
    }

    /**
     * Draw the coin.
     */
    public void draw() {
      window.pushMatrix();
      window.translate(this.position.x+WIDTH/2, this.position.y+HEIGHT/2);
      window.translate(-WIDTH/2,-HEIGHT/2);
      window.image(coinImg, 0,0, this.WIDTH, this.HEIGHT);
      window.popMatrix();
    }

    /**
     * Set the image of coin.
     *
     * @param img as PImage.
     */
    public void setCoinImg(PImage img) {
      this.coinImg = img;
    }

    /**
     * Mark the coin as passed.
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
      return new BoundingBox(this.position, this.WIDTH, this.HEIGHT, this.window);
    }
  }

