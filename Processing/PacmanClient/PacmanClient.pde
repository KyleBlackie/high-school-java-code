//Objects
MsPacman pac;
Ghost inky, blinky, pinky, clyde;
Map map;
ArrayList<Pellet> pellets;
//Variables
int direction;
void setup() {
  size(600, 600);
  noStroke();
  textSize(80);
  //instantiate objects
  map = new Map();
  pac = new MsPacman();
  inky = new Ghost(new Color(0, 255, 255));
  blinky = new Ghost(new Color(255, 0, 0));
  pinky = new Ghost(new Color(255, 184, 255));
  clyde = new Ghost(new Color(255, 184, 81));
  pellets = new ArrayList();
}

void draw() {
  //redraw background
  background(0, 0, 0);
  //check if in playstate
  if (map.getPlayState()) {
    //draw map 
    map.displayScore();
    map.drawPellets();
    map.displayLives();
    noStroke();
    //draw characters
    pac.draw();
    pinky.draw();
    inky.draw();
    clyde.draw();
    blinky.draw();
    //delay for animation
    delay(80);
    //check if pacman eats a pellet (returns true if power pellet)
    if (map.eatPelletsCheck(pac)) {
      pinky.setCanBeEaten(true);
      inky.setCanBeEaten(true);
      clyde.setCanBeEaten(true);
      blinky.setCanBeEaten(true);
      pinky.setColor(new Color(0, 0, 255));
      blinky.setColor(new Color(0, 0, 255));
      inky.setColor(new Color(0, 0, 255));
      clyde.setColor(new Color(0, 0, 255));
    }
    //check if won
    if (map.getPellets().size() <= 0) {
      map.endGame();
    }
    //keep sprites on the map
    pac.boundries();
    //apply movement patterns to ghosts
    pinky.chase(pac);
    blinky.leftRight();
    inky.upDown();
    clyde.leftRight();
    clyde.upDown();
    //check if pacman collides with a ghost
    if (inky.collisionDetection(pac) ) {
      //if the ghost can be eaten increase score and reset ghost
      if (inky.getCanBeEaten()) {
        inky.reset();
        inky.setColor(new Color(0, 255, 255));
        map.increaseScore(50);
      } else {
        //lose one life
        pac.takeDamage();
        //check for game over
        if (pac.getLives() < 1) {
          pac.die();
          map.endGame();
        } else {
          //continue and reset ghosts
          resetAllGhosts();
        }
      }
    } else if (pinky.collisionDetection(pac) ) {
      if (pinky.getCanBeEaten()) {
        pinky.reset();
        pinky.setColor(new Color(255, 184, 255));
        map.increaseScore(50);
      } else {
        pac.takeDamage();
        //check for game over
        if (pac.getLives() < 1) {
          pac.die();
          map.endGame();
        } else {
          //continue and reset ghosts
          resetAllGhosts();
        }
      }
    } else if (blinky.collisionDetection(pac) ) {
      if (blinky.getCanBeEaten()) {
        blinky.reset();
        blinky.setColor(new Color(255, 0, 0));
        map.increaseScore(50);
      } else {
        pac.takeDamage();
        //check for game over
        if (pac.getLives() < 1) {
          pac.die();
          map.endGame();
        } else {
          //continue and reset ghosts
          resetAllGhosts();
        }
      }
    } else if (clyde.collisionDetection(pac) ) {
      if (clyde.getCanBeEaten()) {
        clyde.reset();
        clyde.setColor(new Color(255, 184, 81));
        map.increaseScore(50);
      } else {
        pac.takeDamage();
        //check for game over
        if (pac.getLives() < 1) {
          pac.die();
          map.endGame();
        } else {
          //continue and reset ghosts
          resetAllGhosts();
        }
      }
    }
    //get input from direction on keyboard and move pacman accordingly
    switch(direction) {
    case UP:
      pac.moveUp();
      break;
    case DOWN:
      pac.moveDown();
      break;
    case LEFT:
      pac.moveLeft();
      break;
    case RIGHT:
      pac.moveRight();
      break;
    }
  } else {
    //end game
    background(0);
    //check if pacman is still alive
    if (pac.getAlive()) {
      map.youWin();
    } else {
      map.gameOver();
    }
    noLoop();
  }
}

void keyPressed() {
  //only assign direction if the arrow keys are pressed
  if (keyCode == UP || keyCode == DOWN || keyCode == LEFT || keyCode == RIGHT) {
    direction = keyCode;
  }
}

public void resetAllGhosts() {
  //reset positions and colours
  inky.reset();
  blinky.reset();
  pinky.reset();
  clyde.reset();
  pinky.setColor(new Color(255, 184, 255));
  blinky.setColor(new Color(255, 0, 0));
  inky.setColor(new Color(0, 255, 255));
  clyde.setColor(new Color(255, 184, 81));
}