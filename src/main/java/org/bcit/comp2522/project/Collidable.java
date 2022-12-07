package org.bcit.comp2522.project;

/**
 * The collidable objects interface.
 */
public interface Collidable {

  /**
   * Collide behaviour.
   * @param c
   */
  void collideBehaviour(Collidable c);

  /**
   * Check if collided or not.
   *
   * @param c as another collidable object.
   *
   * @return as boolean.
   */
  boolean collided(Collidable c);

  /**
   * Get bounding box for collide check.
   *
   * @return as BoundingBox.
   */
  BoundingBox getBoundingBox();
}
