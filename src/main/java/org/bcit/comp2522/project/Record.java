package org.bcit.comp2522.project;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.*;

/**
 * This class reads the saved scores in JSON file and records it in an arraylist.
 */
public class Record {

  /**
   * main running window.
   */
  Window window;

  /**
   * Record constructor.
   *
   * @param window as window
   */
  public Record(Window window) {
    this.window = window;
  }

  /**
   * Reads and parses JSON file to get score values, and adds it to an arraylist.
   */
  public void readJSON() {
    JSONParser parser = new JSONParser();
    try (Reader reader = new FileReader("./save/leaderboard.json")) {
      JSONObject jsonObject = (JSONObject) parser.parse(reader);
      //Gets the score for rank 1 to 10
      for (int i = 1; i < 11; i++) {
        long score = (long) jsonObject.get("rank" + i);
        window.leaderboard.getRecord().add(score);
      }
    } catch (IOException e) {
      e.printStackTrace();
    } catch (ParseException e) {
      e.printStackTrace();
    }
  }
}
