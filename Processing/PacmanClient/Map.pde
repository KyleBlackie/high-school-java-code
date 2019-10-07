public class Map {

  //object variables
  private int score; 
  private ArrayList<Pellet> pellets;
  private PImage heart;
  private boolean playState;

  //constructor
  public Map() {
    playState = true;
    pellets = new ArrayList();
    for (int r = 0; r < 5; r++) {
      for (int c = 0; c < 6; c++) {
        if ((int)(Math.random() * 10) == 1) {
          //randomly add power pellets
          pellets.add(new Pellet(c * 100 + 50, r * 100 + 150, 20, 20, true));
        } else {
          pellets.add(new Pellet(c * 100 + 50, r * 100 + 150, 10, 10, false));
        }
      }
    }
    //create heart texture
    heart = loadImage("heart.jpg");
    heart.resize(100, 100);
  }

  //GETTERS ----------------------------------

  public ArrayList getPellets() {
    return pellets;
  }

  public boolean getPlayState() {
    return playState;
  }

  //DISPLAY METHODS ------------------------

  public void drawPellets() {
    for (int i = 0; i < pellets.size(); i++ ) {
      //draw all pellets in array list
      pellets.get(i).draw();
    }
  }

  public void displayScore() {
    //display score
    fill(255);
    text(score, width - 200, 80 );
  }


  public void displayLives() {
    for (int i = 0; i < pac.getLives(); i++) {
      image(heart, i * 100, 0);
    }
  }

  //set playState to false
  public void endGame() {
    playState = false;
  }

  public void increaseScore(int increment) {
    score += increment;
  }

  //WIN OR LOSE SCREENS

  //show "you win" screen
  public void youWin() {
    PImage youWin = loadImage("YouWin.png");
    youWin.resize(500, 400);
    image(youWin, width/2 - youWin.width/2, height/2 - youWin.height/2);
    fill(255);
    text(score, width/2 - 80, height/1.5);
  }


  //show game over screen
  public void gameOver() {
    PImage gameOver =loadImage("gameover.jpg");
    gameOver.resize(400, 300);
    image(gameOver, width/2 - gameOver.width/2, height/2 - gameOver.height/2);
  }

  //COLLISION DETECTION ---------------------------
  
  public boolean eatPelletsCheck(Pacman pac) {
    for (int i = 0; i < pellets.size(); i++) {
      if (pellets.get(i).collisionDetection(pac)) {
        score += pellets.get(i).getPoints();
        if (pellets.get(i).getSpecial()) {
          pellets.remove(i);
          return true;
        }
        pellets.remove(i);
      }
    }
    return false;
  }



}