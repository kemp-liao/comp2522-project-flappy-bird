package org.bcit.comp2522.project;

import java.util.Date;
import processing.core.PImage;
import processing.core.PVector;

/**
 * Singleton bird object.
 */
public class Bird extends Movable implements Collidable {

  /**
   * Player bird.
   */
  private static Bird bird;

  /**
   * Width of the bird.
   */
  private float width = 61f;

  /**
   * Height of the bird.
   */
  private float height = 43f;

  /**
   * Image of the bird.
   */
  private PImage birdImg;

  /**
   * Record the last collided time.
   */
  private Date lastCollide;

  /**
   * The delay time for next collide.
   */
  private int collideDelay = 100;

  /**
   * Running the game, TRUE = Running; FALSE = Stopped.
   */
  private boolean running = true;

  /**
   * number of remaining invincibility frames.
   */
  public int iFrame = 0;

  /**
   * when not at full health, if bird collects 10 coins, it gets exchanged for extra life.
   */
  public int coinCollected = 0;

  /**
   * Singleton Bird constructor.
   *
   * @param window as this window.
   */
  private Bird(Window window) {
    this.position = new PVector(window.width / 2 - width / 2, window.height / 2 - height / 2);
    this.direction = new PVector(1, 0).normalize();
    this.window = window;
    lastCollide = new Date();
  }

  /**
   * Get the instance for the singleton bird.
   *
   * @param window as this window.
   *
   * @return the bird instance as singleton.
   */
  public static Bird getInstance(Window window) {
    if (bird == null) {
      bird = new Bird(window);
    }
    return bird;
  }

  /**
   * Getter for current position.
   *
   * @return as PVector.
   */
  public PVector getPosition() {
    return this.position;
  }

  /**
   * Set bird image.
   *
   * @param img as PImage.
   */
  public void setBirdImg(PImage img) {
    this.birdImg = img;
  }

  /**
   * Draw the bird.
   */
  public void draw() {
    //If there are remaining iFrames, it decreases it by 1 per frame.
    if (iFrame > 0) {
      iFrame -= 1;
    }

    window.pushMatrix();
    window.translate(this.position.x + this.width / 2, this.position.y + this.height / 2);
    if (vy > 0) {
      window.rotate(0.4f);
    } else {
      window.rotate(-0.4f);
    }
    window.translate(-width / 2, -height / 2);

    //Bird flashes during invincible state.
    if (iFrame > 0 && iFrame % 10 < 5) {
      window.tint(255, 126);
    }
    window.image(birdImg, 0, 0, this.width, this.height);
    window.noTint();
    window.popMatrix();
  }

  /**
   * Make bird fly.
   */
  public void fly() {
    if (running) {
      vy = -3f;
      this.position.y += vy;
    }
  }

  /**
   * Stop running.
   */
  public void stop() {
    running = false;
    this.bird = null;
  }

  /**
   * Check if the bird touches the boundary.
   *
   * @return as boolean.
   */
  public boolean touchBoundary() {
    PVector center = new PVector(position.x + width / 2f, position.y + height / 2f);
    if (window.width - center.x <= height / 2f
            || window.height - 75 - center.y <= height / 2f
            || center.x <= width / 2f
            || center.y <= height / 2f) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * The movement due to gravity.
   */
  public void move() {
    if (!touchBoundary()) {
      vy += this.gravity;
      this.position.y += vy;
    }
  }

  /**
   * Check the bird passes the cylinder or not.
   *
   * @param c as cylinder.
   *
   * @return TRUE if passed, FALSE if unpassed.
   */
  public boolean passCheck(Cylinder c) {
    if (this.position.x > c.getPosition().x && !c.getPassed()) {
      c.setAsPassed();
      return true;
    }
    return false;
  }

  @Override
  public void collideBehaviour(Collidable c) {

  }

  @Override
  public boolean collided(Collidable c) {
    if (!running || this == c || iFrame > 0) {
      return false;
    }
    boolean collides = getBoundingBox().collide(c.getBoundingBox());
    Date now = new Date();
    if (collides) {
      if (now.getTime() - this.lastCollide.getTime() > this.collideDelay) {
        this.lastCollide = now;
        return true;
      }
    }
    return false;
  }

  @Override
  public BoundingBox getBoundingBox() {
    return new BoundingBox(this.position, this.width, this.height, window);
  }

  /**
   * Sets the number of iFrame.
   */
  public void startiFrame() {
    iFrame = 60;
  }

  /**
   * Counts the number of coin collected to check the qualification of gaining extra life.
   */
  public void addCoinCollected() {
    coinCollected += 1;
  }

  /**
   * Resets the number of coin collected as 0 after gaining one extra life.
   */
  public void resetCoinCollected() {
    coinCollected = 0;
  }

  /**
   * Getter for the number of coin collected.
   *
   * @return the number of coin collected
   */
  public int getCoinCollected() {
    return coinCollected;
  }

}
