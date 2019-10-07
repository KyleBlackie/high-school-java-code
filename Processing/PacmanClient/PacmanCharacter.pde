import java.awt.Color;
public abstract class PacmanCharacter implements MovementInterface {

  //Object variables (protected so that classes that extend PacmanCharacter can see them)
  protected int xLoc;
  protected int yLoc;
  protected int speed;
  protected int dir;
  protected Color c;
  protected boolean canBeEaten;
  protected boolean alive;

  //Constructors
  private PacmanCharacter() {
    alive = true;
    dir = -1;
  }

  private PacmanCharacter(int xLoc, int yLoc) {
    this();
    this.xLoc = xLoc;
    this.yLoc = yLoc;
  }

  private PacmanCharacter(int xLoc, int yLoc, Color c, boolean canBeEaten, int speed) {
    this(xLoc, yLoc);
    this.c = c;
    this.canBeEaten = canBeEaten;
    this.speed = speed;
  }

  //method for when a character dies
  public void die() {
    alive = false;
  }

  //getters
  public boolean getAlive() {
    return alive;
  }

  public boolean getCanBeEaten() {
    return canBeEaten;
  }
  
  public Color getColor(){
    return c;
  }

  //setters
  public void setCanBeEaten(boolean canBeEaten) {
    this.canBeEaten = canBeEaten;
  }
  
  public void setColor(Color c){
    this.c = c;
  }


  //ensure that character stays on the map
  public void boundries() {
    if (xLoc < 25) {
      xLoc = 25;
    }
    if (xLoc > width - 25) {
      xLoc = width - 25;
    }
    if (yLoc < 125) {
      yLoc = 125;
    }  
    if (yLoc > height - 25) {
      yLoc = height - 25;
    }
  }
  
  
  //ABSTRACT METHODS
  
  public abstract void draw();
  public abstract void reset();

  //MOVEMENT INTERFACE METHODS

  public void moveLeft() {
    xLoc -= speed;
    dir = 0;
  }

  public void moveRight() {
    xLoc += speed;
    dir = 1;
  }

  public void moveUp() {
    yLoc -= speed;
    dir = 2;
  }

  public void moveDown() {
    yLoc += speed;
    dir = 3;
  }
}