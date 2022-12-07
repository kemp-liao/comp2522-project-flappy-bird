package org.bcit.comp2522.project;

import processing.core.PConstants;
import processing.core.PFont;
import processing.core.PVector;

public class Button extends Text{

  public Button(PVector position, PFont font, String text, int textSize, Window window) {
    this.position = position;
    this.text = text;
    this.window = window;
    this.textSize = textSize;
    this.font = font;
  }

  public void draw() {
    window.textFont(font);
    window.textSize(this.textSize);
    window.textAlign(PConstants.CENTER);
    window.text(text, this.position.x, this.position.y);
  }

  public boolean checkClick() {
    PVector mousePosition = new PVector(window.mouseX, window.mouseY);
    if (mousePosition.x<position.x+100 && mousePosition.x> position.x-100 && mousePosition.y<position.y+30 && mousePosition.y>position.y-30) {
      return true;
    }
    return false;
  }
}
