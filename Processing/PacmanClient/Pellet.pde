public class Pellet {

  //Object variables
  private int points;
  private int size;
  private int xLoc;
  private int yLoc;
  private boolean special;

  //empty constructor
  public Pellet() {
  }
  
  public Pellet (int xLoc, int yLoc, int points, int size, boolean special) {
    this.xLoc = xLoc;
    this.yLoc = yLoc;
    this.special = special;
    this.points = points;
    this.size = size;
  }

  //getters -----------------------
  
  public int getPoints() {
    return points;
  }

  public boolean getSpecial() {
    return special;
  }

  //draw individual pellet
  public void draw() {
    fill(255);
    ellipse(xLoc, yLoc, size, size);
  }
  
  //see if pacman is touching pellet
  public boolean collisionDetection(Pacman pac) {
    float xDistance = Math.abs((xLoc + size/2) - pac.xLoc);
    float yDistance = Math.abs((yLoc + size/2) - pac.yLoc);
    if (xDistance < 40 && yDistance < 40) {
      return true;
    } else {
      return false;
    }
  }
}