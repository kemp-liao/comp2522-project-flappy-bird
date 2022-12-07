package org.bcit.comp2522.project;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import processing.core.PImage;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class LeaderBoardTest {
  @Test
  public void LeaderBoard() {
    PImage img = null;
    float moveSpeed = 0.0F;
    Window window = null;
    BackgroundScroller expected = null;
    BackgroundScroller actual = new BackgroundScroller(img, moveSpeed, window);

  }

  @Test
  void notNull(){
    assertNotNull(true, String.valueOf(true));
  }

  @Test
  void shouldShowSimpleAssertion() {
    Assertions.assertEquals(1, 1);
  }
}
