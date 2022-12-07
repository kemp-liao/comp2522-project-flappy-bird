package org.bcit.comp2522.project;

import processing.core.PVector;

/**
 * BoundingBox that used to check collision.
 */
public class BoundingBox extends Movable {

  /**
   * Box for bounding checking.
   */
  private Box box;

  /**
   * Box for bounding checking, especially for lower cylinder.
   */
  private Box boxLower;

  /**
   * BoundingBox constructor for bird and ground.
   *
   * @param position as PVector.
   *
   * @param width as float.
   *
   * @param height as float.
   *
   */
  public BoundingBox(PVector position, float width, float height, Window window) {
    this.window = window;
    box = new Box(new PVector(position.x + width/2f, position.y + height/2f), width, height);
  }

  /**
   * BoundingBox constructor for cylinders.
   *
   * @param position as PVector.
   *
   * @param width as float.
   *
   * @param height as float.
   *
   * @param gap as float.
   *
   * @param window as this window.
   *
   */
  public BoundingBox(PVector position, float width, float height, float gap, Window window) {
    this.window = window;
    box = new Box(new PVector(position.x, position.y - height/2f - gap/2f), width, height);
    boxLower = new Box(new PVector(position.x, position.y + height/2f + gap/2f), width, height);
  }

  /**
   * Draw the bounding box if needed.
   */
  public void draw() {
    //Draw center point
    window.fill(255, 0, 0);
    window.ellipse(box.getCenter().x, box.getCenter().y, 10, 10);
    //Draw box
    window.stroke(255, 0, 0);
    window.line(box.getTopLeft().x, box.getTopLeft().y, box.getTopRight().x, box.getTopRight().y);
    window.line(box.getTopRight().x, box.getTopRight().y, box.getBottomRight().x, box.getBottomRight().y);
    window.line(box.getBottomLeft().x, box.getBottomLeft().y, box.getBottomRight().x, box.getBottomRight().y);
    window.line(box.getTopLeft().x, box.getTopLeft().y, box.getBottomLeft().x, box.getBottomLeft().y);
    //If lower box exists, draw it
    if (boxLower != null) {
      window.line(boxLower.getTopLeft().x, boxLower.getTopLeft().y, boxLower.getTopRight().x, boxLower.getTopRight().y);
      window.line(boxLower.getTopRight().x, boxLower.getTopRight().y, boxLower.getBottomRight().x, boxLower.getBottomRight().y);
      window.line(boxLower.getBottomLeft().x, boxLower.getBottomLeft().y, boxLower.getBottomRight().x, boxLower.getBottomRight().y);
      window.line(boxLower.getTopLeft().x, boxLower.getTopLeft().y, boxLower.getBottomLeft().x, boxLower.getBottomLeft().y);
    }
  }

  /**
   * Check if current bounding box collides with another bounding box b.
   *
   * @param b as another bounding box.
   *
   * @return as boolean.
   */
  public boolean collide(BoundingBox b) {
    boolean collideLower = false;
    boolean collideUpper = !(box.getBottom() < b.getBox().getTop()
            || box.getTop() > b.getBox().getBottom()
            || box.getRight() < b.getBox().getLeft()
            || box.getLeft() > b.getBox().getRight()
    );
    if (b.getBoxLower() != null) {
      collideLower = !(box.getBottom() < b.getBoxLower().getTop()
              || box.getTop() > b.getBoxLower().getBottom()
              || box.getRight() < b.getBoxLower().getLeft()
              || box.getLeft() > b.getBoxLower().getRight()
      );
    }
    return (collideUpper || collideLower);
  }

  /**
   * Get the box for bounding check.
   *
   * @return as box.
   */
  public Box getBox() {
    return this.box;
  }

  /**
   * Get the lower box for lower cylinder only.
   *
   * @return as box.
   */
  public Box getBoxLower() {
    return this.boxLower;
  }
}
