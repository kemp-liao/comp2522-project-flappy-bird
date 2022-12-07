package org.bcit.comp2522.project;

import processing.core.PVector;

/**
 * Abstract class for the objects that move and may be influenced by the gravity.
 */
public abstract class Movable {

  /**
   * Gravity value.
   */
  protected float gravity = 0.1f;

  /**
   * Horizontal speed.
   */
  protected float vx = 0;

  /**
   * Vertical speed.
   */
  protected float vy = 0;

  /**
   * Current position.
   */
  protected PVector position;

  /**
   * Current direction.
   */
  protected PVector direction;

  /**
   * Only run when it is true.
   */
  boolean running = true;

  /**
   * This window.
   */
  protected Window window;

  /**
   * Getter of horizontal speed.
   *
   * @return vx as float.
   */
  public float getVx() {
    return vx;
  }

  /**
   * Getter of vertical speed.
   *
   * @return vy as float.
   */
  public float getVy() {
    return vy;
  }

  /**
   * Get current position.
   *
   * @return position as PVector.
   */
  public PVector getPosition() {
    return position;
  }

  /**
   * Get current direction.
   *
   * @return direction as PVector.
   */
  public PVector getDirection() {
    return direction;
  }

  /**
   * Get gravity value.
   *
   * @return gravity as float.
   */
  public float getGravity() {
    return gravity;
  }

  /**
   * Set current position.
   *
   * @param position as PVector.
   */
  public void setPosition(PVector position) {
    this.position = position;
  }

  /**
   * Set current direction.
   *
   * @param direction as PVector.
   */
  public void setDirection(PVector direction) {
    this.direction = direction;
  }

  /**
   * Set horizontal speed.
   *
   * @param vx as float.
   */
  public void setVx(float vx) {
    this.vx = vx;
  }

  /**
   * Set vertical speed.
   *
   * @param vy as float.
   */
  public void setVy(float vy) {
    this.vy = vy;
  }

  /**
   * Set gravity value.
   *
   * @param gravity as float.
   */
  public void setGravity(float gravity) {
    this.gravity = gravity;
  }

  /**
   * Set running status.
   *
   * @param running as boolean.
   */
  public void setRunning(boolean running) {
    this.running = running;
  }

}
