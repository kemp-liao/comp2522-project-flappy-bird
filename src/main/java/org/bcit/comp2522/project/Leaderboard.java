package org.bcit.comp2522.project;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import org.json.simple.JSONObject;
import processing.core.PConstants;
import processing.core.PFont;
import processing.core.PVector;

/**
 * This class is used to create a leaderboard page.
 */
public class Leaderboard extends Text {

  /**
   * record class to get the score data from JSON file.
   */
  private Record r;

  /**
   * Arraylist of user scores.
   */
  public ArrayList<Long> record;

  /**
   * Leaderboard constructor.
   *
   * @param font font of the texts
   * @param window this window
   */
  public Leaderboard(PFont font, Window window) {
    this.font = font;
    this.window = window;
    this.textSize = 50;
    this.position = new PVector(this.window.width / 2, 150);
    this.record = new ArrayList<>();
  }

  /**
   * draws the leaderboard page with top ten score values.
   */
  public void draw() {
    window.textFont(font);
    window.textSize(this.textSize);
    window.textAlign(PConstants.CENTER);
    window.text("Leaderboard", this.position.x, this.position.y);
    window.textSize(this.textSize - 20);
    for (int i = 0; i < 10; i++) {
      window.text("Rank#" + (i + 1) + " "
              + record.get(i), this.position.x, this.position.y + 40 * (i + 1));
    }
  }

  /**
   * getting the arraylist of score values.
   *
   * @return record as a long arraylist
   */
  public ArrayList<Long> getRecord() {
    return record;
  }

  /**
   * Used to set up record object.
   *
   * @param r record object
   */
  public void registerListener(Record r) {
    this.r = r;
  }

  /**
   * gets score data from the JSON file.
   */
  public void getDataFromFile() {
    if (this.r != null) {
      r.readJSON();
    }
  }

  /**
   * Updates the top ten scores by comparing it with the new score gained.
   *
   * @param score newly gained score as an integer
   */
  public void recordTopTen(int score) {
    //takes out the existing score values from the leaderboard
    ArrayList<Long> records = window.leaderboard.getRecord();
    //saves the newly achieved score value at the end of the game
    long currentScore = score;
    //if there is no existing score value, saves the current score value.
    if (records.size() == 0) {
      records.add(currentScore);
    } else { // if there are score values, compares them with newly achieved score and adds it in the appropriate place
      for (int i = 0; i < records.size(); i++) {
        if (records.get(i) < currentScore) {
          records.add(i, currentScore);
          break;
        }
      }
    }
    //creates JSON object
    JSONObject jsonObject = new JSONObject();
    //puts key(rank#) and value(score) pairs in the JSONObject
    for (int i = 0; i < records.size(); i++) {
      jsonObject.put("rank" + (i + 1), records.get(i));
    }
    //rewrites the JSON file with newly created JSONObject
    try (Writer writer = new FileWriter("./save/leaderboard.json")) {
      writer.write(jsonObject.toJSONString());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
