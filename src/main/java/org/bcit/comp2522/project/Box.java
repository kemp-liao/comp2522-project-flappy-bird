package org.bcit.comp2522.project;

import processing.core.PVector;

/**
 * Box for BoundingBox.
 */
public class Box {

  /**
   * Width of the box.
   */
  private float width;

  /**
   * Height of the box.
   */
  private float height;

  /**
   * Top of the box.
   */
  private float top;

  /**
   * Right side of the box.
   */
  private float right;

  /**
   * Left side of the box.
   */
  private float left;

  /**
   * Bottom of the box.
   */
  private float bottom;

  /**
   * Top right vertex coordinate of the box.
   */
  private PVector topRight;

  /**
   * Top left vertex coordinate of the box.
   */
  private PVector topLeft;

  /**
   * Bottom right vertex coordinate of the box.
   */
  private PVector bottomRight;

  /**
   * Bottom left vertex coordinate of the box.
   */
  private PVector bottomLeft;

  /**
   * Center of the box.
   */
  private PVector center;

  /**
   * Box constructor.
   *
   * @param center as PVector.
   *
   * @param width as float.
   *
   * @param height as float.
   */
  public Box(PVector center, float width, float height) {
    this.center = center;
    this.height = height;
    this.width = width;
    //Border
    this.top = center.y - (height / 2f);
    this.bottom = center.y + (height / 2f);
    this.right = center.x + (width / 2f);
    this.left = center.x - (width / 2f);
    //Vertices
    this.topRight = new PVector(center.x + width / 2f, center.y + height / 2f);
    this.topLeft = new PVector(center.x - width / 2f, center.y + height / 2f);
    this.bottomRight = new PVector(center.x + width / 2f, center.y - height / 2f);
    this.bottomLeft = new PVector(center.x - width / 2f, center.y - height / 2f);
  }

  /**
   * Height getter.
   *
   * @return height as float.
   */
  public float getHeight() {
    return height;
  }

  /**
   * Width getter.
   *
   * @return width as float.
   */
  public float getWidth() {
    return width;
  }

  /**
   * Top line getter.
   *
   * @return top as float.
   */
  public float getTop() {
    return top;
  }

  /**
   * Bottom line getter.
   *
   * @return bottom as float.
   */
  public float getBottom() {
    return bottom;
  }

  /**
   * Left line getter.
   *
   * @return left as float.
   */
  public float getLeft() {
    return left;
  }

  /**
   * Right line getter.
   *
   * @return right as float.
   */
  public float getRight() {
    return right;
  }

  /**
   * Get top left vertex coordinate.
   *
   * @return top left vertex as PVector.
   */
  public PVector getTopLeft() {
    return topLeft;
  }

  /**
   * Get top right vertex coordinate.
   *
   * @return top right vertex as PVector.
   */
  public PVector getTopRight() {
    return topRight;
  }

  /**
   * Get bottom left vertex coordinate.
   *
   * @return bottom left vertex as PVector.
   */
  public PVector getBottomLeft() {
    return bottomLeft;
  }

  /**
   * Get bottom right vertex coordinate.
   *
   * @return bottom right vertex as PVector.
   */
  public PVector getBottomRight() {
    return bottomRight;
  }

  /**
   * Get box center vertex coordinate.
   *
   * @return center vertex as PVector.
   */
  public PVector getCenter() {
    return center;
  }
}
