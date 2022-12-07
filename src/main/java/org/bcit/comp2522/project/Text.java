package org.bcit.comp2522.project;

import processing.core.PFont;
import processing.core.PVector;

/**
 * Abstract class that displays text on screen.
 */
public abstract class Text {

  /**
   * Text position.
   */
  protected PVector position;

  /**
   * Text font.
   */
  protected PFont font;

  /**
   * Text size.
   */
  protected int textSize;

  /**
   * Text string.
   */
  protected String text;

  /**
   * This window.
   */
  Window window;

}
