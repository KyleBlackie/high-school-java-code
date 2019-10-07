public class Ghost extends PacmanCharacter { 

  //Class Variables
  private final static int SIZE = 50;
  //Object variables
  boolean movingRight, movingUp;
  //Constructors
  public Ghost() {
    this(new Color(255, 0, 255));
  }

  //give a colour for ghost constructor and it will set a default x,y location
  public Ghost(Color c) {
    this(200, 200, c);
  }

  //if client wants to specify x and y locations
  public Ghost(int xLoc, int yLoc, Color c) {
    super(xLoc, yLoc, c, false, 5);
  }


  //draw procedure
  public void draw() {
    fill(c.getRed(), c.getGreen(), c.getBlue());
    rect(xLoc, yLoc, SIZE, SIZE);
    ellipse(xLoc + SIZE/2, yLoc, SIZE, SIZE);
    fill(255);
    ellipse(xLoc + SIZE/4, yLoc, SIZE/3, SIZE/3);
    ellipse(xLoc + SIZE/1.4, yLoc, SIZE/3, SIZE/3);
    fill(0);
    ellipse(xLoc + SIZE/4, yLoc, SIZE/6, SIZE/6);
    ellipse(xLoc + SIZE/1.4, yLoc, SIZE/6, SIZE/6);
  }

  //reset all ghosts
  public void reset() {
    xLoc = 200;
    yLoc = 200;
    canBeEaten = false;
  }

  //Check if in contact with pacman
  public boolean collisionDetection(Pacman pac) {
    float xDistance = Math.abs((xLoc + SIZE/2) - pac.xLoc);
    float yDistance = Math.abs((yLoc + SIZE/2) - pac.yLoc);
    if (xDistance < 50 && yDistance < 50) {
      return true;
    } else {
      return false;
    }
  }

  //GHOST MOVEMENT PATTERNS----------------------------

  public void leftRight() {
    if (movingRight) {
      moveRight();
      if (xLoc > width - SIZE) {
        movingRight = false;
      }
    } else {
      moveLeft();
      if (xLoc < SIZE/2) {
        movingRight = true;
      }
    }
  }

  public void upDown() {
    if (movingUp) {
      moveUp();
      if (yLoc < SIZE + 50) {
        movingUp = false;
      }
    } else {
      moveDown();
      if (yLoc > height - SIZE) {
        movingUp = true;
      }
    }
  }

  //Continuously follow pacman
  public void chase(Pacman pac) {
    if (xLoc < pac.xLoc) {
      moveRight();
    } else if (xLoc > pac.xLoc) {
      moveLeft();
    } else if (yLoc < pac.yLoc) {
      moveDown();
    } else if (yLoc > pac.yLoc) {
      moveUp();
    }
  }
}