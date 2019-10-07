public class Pacman extends PacmanCharacter {

  //Class Variables
  protected final static int SIZE = 50;

  //Object variables
  private int lives;
  protected boolean openMouth;


  //Constructors
  public Pacman() {
    this(500, 500);
  }

  public Pacman(int xLoc, int yLoc) {
    super(xLoc, yLoc, new Color(255, 255, 0), true, 10);
    lives = 3;
  }

  //Getters
  public int getLives() {
    return lives;
  }

  //procedure for when pacman touches a normal ghost
  public void takeDamage() {
    delay(1000);
    xLoc = 500;
    yLoc = 500;
    lives--;
    delay(500);
  }


  //draw pacman
  public void draw() {
    fill(c.getRed(), c.getGreen(), c.getBlue());
    if (openMouth) {
      switch(dir) {
      case 2:
        arc(xLoc, yLoc, SIZE, SIZE, radians (325), radians(575));
        break;
      case 3:
        arc(xLoc, yLoc, SIZE, SIZE, radians (130), radians(410));
        break;
      case 1:
        arc(xLoc, yLoc, SIZE, SIZE, QUARTER_PI, 2 * PI - QUARTER_PI);
        break;
      case 0:
        arc(xLoc, yLoc, SIZE, SIZE, radians (235), radians(500));
        break;
      default:
        ellipse(xLoc, yLoc, SIZE, SIZE);
        break;
      }
    } else {
      ellipse(xLoc, yLoc, SIZE, SIZE);
    }    
    openMouth = !openMouth;
  }
  
  public void reset(){
    xLoc = 500;
    yLoc = 500;
  }
}