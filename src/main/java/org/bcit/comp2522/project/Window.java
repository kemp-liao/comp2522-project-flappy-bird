package org.bcit.comp2522.project;

import java.util.ArrayList;
import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;
import processing.core.PVector;
import processing.event.KeyEvent;

/**
 * Main running window.
 */
public class Window extends PApplet {

  /**
   * Player bird.
   */
  public Bird bird;

  /**
   * The list for background stickers.
   */
  public ArrayList<Background> backgrounds;

  /**
   * The list for ground stickers.
   */
  public ArrayList<Ground> grounds;

  /**
   * The list for cylinders.
   */
  public ArrayList<Cylinder> cylinders;

  /**
   * The list for coins.
   */
  public ArrayList<Coin> coins;

  /**
   * Cylinder maker.
   */
  public CylinderMaker cMaker;

  /**
   * Background scroller that scrolls the background.
   */
  public BackgroundScroller bScroller;

  /**
   * Coin maker.
   */
  public CoinMaker coinMaker;

  /**
   * Ground scroller that scrolls the ground.
   */
  public GroundScroller gScroller;

  /**
   * Display score on the main screen.
   */
  public Score score;

  /**
   * Display the number of collected coins on the main screen.
   */
  public CoinScore coinscore;

  /**
   * Define the gap size of upper and lower cylinders.
   */
  public final float CYLINDER_GAP = 150;

  /**
   * Define the cylinder width.
   */
  public final float CYLINDER_WIDTH = 70;

  /**
   * Define the coin width.
   */
  public final float COIN_WIDTH = 20;

  /**
   * Define the game scrolling speed, negative number for moving towards left.
   */
  public final float SCROLLING_SPEED = -2;

  /**
   * The cylinder creating period, controlled by the scrolling speed above.
   */
  public final int OBJECT_CREATING_PERIOD = (int) Math.abs(2500 / (SCROLLING_SPEED / 2));

  /**
   * The status of the game.
   * 0 - Menu page
   * 1 - Game
   * 2 - Leaderboard
   * 3 - Game over page
   */
  public int status = 0;

  /**
   * menu page.
   */
  public Menu menu;

  /**
   * leaderboard page.
   */
  public Leaderboard leaderboard;

  /**
   * end menu page.
   */
  public EndMenu endMenu;

  /**
   * start button.
   */
  public Button startButton;

  /**
   * leaderboard button.
   */
  public Button leaderboardButton;

  /**
   * back to menu button.
   */
  public Button backToMenu;

  /**
   * Displays the number of remaining lives.
   */
  public HeartDisplay heartDisplay;

  /**
   * number of remaining lives.
   */
  public int lifeState = 3;

  /**
   * Sets up the game with all the necessary elements.
   */
  public void setupGame() {
    //Initialize array list
    backgrounds = new ArrayList<>();
    grounds = new ArrayList<>();
    cylinders = new ArrayList<>();
    coins = new ArrayList<>();

    //Get an instance of the bird
    bird = Bird.getInstance(this);
    bird.setBirdImg(loadImage("./img/bird.png"));

    //Set up the moving cylinders
    PImage cylinderImg = loadImage("./img/cylinder.png");
    cMaker = new CylinderMaker(OBJECT_CREATING_PERIOD, CYLINDER_GAP,
            CYLINDER_WIDTH, SCROLLING_SPEED, cylinderImg, this);
    cMaker.run();

    //Set up the moving coins
    PImage coinImg = loadImage("./img/coin.png");
    coinMaker = new CoinMaker(OBJECT_CREATING_PERIOD, COIN_WIDTH, SCROLLING_SPEED, coinImg, this);
    coinMaker.run();

    //Ground setup
    PImage groundImg = loadImage("./img/ground.jpg");
    gScroller = new GroundScroller(groundImg, SCROLLING_SPEED, this);

    //Background setup
    PImage backgroundImg = loadImage("./img/background.jpg");
    bScroller = new BackgroundScroller(backgroundImg, SCROLLING_SPEED, this);

    //Score display setup
    PFont scoreFont = createFont("./font/ARCADE.TTF", 128);
    score = new Score(new PVector(this.width / 2, this.height / 2 - 200), scoreFont, 70, this);

    //Coin score display setup
    PFont coinScoreFont = createFont("./font/ARCADE.TTF", 100);
    coinscore = new CoinScore(new PVector(this.width - 120, this.height - 670),
            coinScoreFont, 30, this);

    //Heart display setup
    PImage heartImg = loadImage("./img/heart.png");
    heartDisplay = new HeartDisplay(heartImg, this);
  }

  /**
   * draws the game in every frame.
   */
  public void drawGame() {
    background(0);

    //Add and remove cylinders
    for (Cylinder c : cMaker.addQuery) {
      this.cylinders.add(c);
    }
    cMaker.addQuery.clear();
    cMaker.remove();
    for (Cylinder c : cMaker.removeQuery) {
      this.cylinders.remove(c);
    }
    cMaker.removeQuery.clear();

    //Add and remove coins
    for (Coin coin : coinMaker.addCoinQuery) {
      this.coins.add(coin);
    }
    coinMaker.addCoinQuery.clear();
    coinMaker.remove();
    for (Coin coin : coinMaker.removeCoinQuery) {
      this.coins.remove(coin);
    }
    coinMaker.removeCoinQuery.clear();

    //Add and remove ground stickers
    gScroller.scroll();
    for (Ground g : gScroller.addQuery) {
      this.grounds.add(g);
    }
    gScroller.addQuery.clear();
    for (Ground g : gScroller.removeQuery) {
      this.grounds.remove(g);
    }
    gScroller.removeQuery.clear();

    //Add and remove ground stickers
    bScroller.scroll();
    for (Background b : bScroller.addQuery) {
      this.backgrounds.add(b);
    }
    bScroller.addQuery.clear();
    for (Background b : bScroller.removeQuery) {
      this.backgrounds.remove(b);
    }
    bScroller.removeQuery.clear();

    //Draw background
    for (Background b : backgrounds) {
      b.draw();
      b.move();
    }

    //Draw cylinders
    for (Cylinder c : cylinders) {
      c.draw();
      c.move();
      //When the bird passes cylinder, it gains one score.
      if (bird.passCheck(c)) {
        score.scorePlusOne();
      }
      //When the bird collides with a cylinder, it loses one life and becomes invincible for a moment.
      if (bird.collided(c)) {
        lifeState -= 1;
        bird.startiFrame();
      }
    }

    //When the bird loses all hearts, game is over.
    if (lifeState == 0) {
      System.out.println("GAME OVER");
      gameOver();
    }

    //Draw coins
    for (Coin coin : coins) {
      coin.draw();
      coin.move();
      //when the bird collides with a coin, coin gets disappeared and gives one coin score point.
      if (bird.collided(coin)) {
        coinscore.scorePlusOne();
        coinMaker.removeCoinImage();
        //when the player is not at full health, collecting 10 coins gives one life back.
        if (lifeState < 3) {
          bird.addCoinCollected();
          if (bird.getCoinCollected() == 10) {
            lifeState += 1;
            bird.resetCoinCollected();
          }
        }
      }
    }

    //Draw the bird
    bird.draw();
    bird.move();

    //Draw ground
    for (Ground g : grounds) {
      g.draw();
      g.move();
      if (bird.collided(g)) {
        lifeState -= 1;
        bird.startiFrame();
      }
    }

    //Draw score
    score.draw();

    //Draw coin score
    coinscore.draw();

    //Draw heart display
    heartDisplay.draw();
  }

  /**
   * sets up the main menu page with start and leaderboard button.
   */
  public void setupMenu() {
    PFont menuFont = createFont("./font/ARCADE.TTF", 128);
    menu = new Menu(new PVector(this.width / 2, this.height / 2 - 40), menuFont, this);
    startButton = new Button(new PVector(this.width / 2, this.height / 2 + 60),
            menuFont, "Start", 40, this);
    leaderboardButton = new Button(new PVector(this.width / 2, this.height / 2 + 160),
            menuFont, "Leaderboard", 40, this);
  }

  /**
   * draws the main menu page.
   */
  public void drawMenu() {
    background(0);
    menu.draw();
    startButton.draw();
    leaderboardButton.draw();
  }

  /**
   * sets up the end menu page with back to menu button.
   */
  public void setupEndMenu() {
    PFont menuFont = createFont("./font/ARCADE.TTF", 128);
    endMenu = new EndMenu(new PVector(this.width / 2, this.height / 2 - 40), menuFont, this);
    backToMenu = new Button(new PVector(this.width / 2, this.height / 2 + 60), menuFont, "\nBack to the menu", 35, this);
  }

  /**
   * draws the end menu page.
   */
  public void drawEndMenu() {
    background(0);
    endMenu.draw();
    backToMenu.draw();
  }

  /**
   * sets up the leaderboard page by reading score data from JSON file.
   */
  public void setupLeaderboard() {
    PFont font = createFont("./font/ARCADE.TTF", 128);
    leaderboard = new Leaderboard(font, this);
    Record r = new Record(this);
    leaderboard.registerListener(r);
    leaderboard.getDataFromFile();
    backToMenu = new Button(new PVector(this.width / 2, this.height - 50),
            font, "Back", 40, this);
  }

  /**
   * draws leaderboard page.
   */
  public void drawLeaderboard() {
    background(0);
    leaderboard.draw();
    backToMenu.draw();
  }

  /**
   * Setup of the game.
   */
  public void setup() {
    switch (status) {
      case 0:
        setupMenu();
        break;
      case 1:
        setupGame();
        break;
      case 2:
        setupLeaderboard();
        break;
      case 3:
        setupEndMenu();
        break;
      default:
        System.out.println("Error");
    }
  }

  /**
   * Calls when the game is over.
   */
  public void gameOver() {
    bird.stop();
    cMaker.stop();
    gScroller.stop();
    bScroller.stop();
    coinMaker.stop();
    this.background(255, 0, 0);
    //Show the game over page
    status = 3;
    setupEndMenu();
    endMenu.draw();
    lifeState = 3;
    //Sets up leaderboard again to update it
    setupLeaderboard();
    //Updates the top ten scores again with the newly gained score at the end of the game.
    leaderboard.recordTopTen(score.getScore());
  }

  /**
   * Called when the space key is pressed.
   *
   * @param event is the keyboard event.
   */
  @Override
  public void keyPressed(KeyEvent event) {
    if (status == 1) {
      super.keyPressed(event);
      if (bird == null) {
        return;
      }
      if (event.getKey() == ' ') {
        bird.fly();
      }
    }
  }

  /**
   * Called when the mouse is clicked.
   */
  public void mousePressed() {
    switch (status) {
      case 0:
        if (startButton.checkClick()) {
          setupGame();
          status = 1;
        }
        if (leaderboardButton.checkClick()) {
          setupLeaderboard();
          status = 2;
        }
        break;
      case 1:
        if (bird == null) {
          return;
        }
        bird.fly();
        break;
      case 2:
        if (backToMenu.checkClick()) {
          setupMenu();
          status = 0;
        }
        break;
      case 3:
        if (backToMenu.checkClick()) {
          setupGame();
          status = 0;
        }
      default:
        System.out.println("Error");
    }
  }

  /**
   * Draw function that draws each frame.
   */
  public void draw() {
    switch (status) {
      case 0:
        drawMenu();
        break;
      case 1:
        drawGame();
        break;
      case 2:
        drawLeaderboard();
        break;
      case 3:
        drawEndMenu();
        break;
      default:
        System.out.println("Error");
    }
  }

  /**
   * Set up the window.
   */
  public void settings() {
    size(480, 720);
  }

  /**
   * Main function that drives the program.
   *
   * @param passedArgs arguments from command line.
   */
  public static void main(String[] passedArgs) {
    String[] appletArgs = new String[]{"window"};
    Window window = new Window();
    PApplet.runSketch(appletArgs, window);
  }
}
