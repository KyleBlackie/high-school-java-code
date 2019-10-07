public class MsPacman extends Pacman {

  //Constructors
  public MsPacman() {
    super(500, 500);
  }

  public MsPacman(int xLoc, int yLoc) {
    super(xLoc, yLoc);
  }

  //Override pacmans draw procedure
  public void draw() {
    //fill with the proper colours
    fill(c.getRed(), c.getGreen(), c.getBlue());
    if (openMouth) {
      //animate based off of direction
      switch(dir) {
      case 2:
        arc(xLoc, yLoc, SIZE, SIZE, radians (325), radians(575));
        fill(255, 0, 0);
        rect(xLoc, yLoc + 10, 20, 10);
        break;
      case 3:
        arc(xLoc, yLoc, SIZE, SIZE, radians (130), radians(410));
        fill(255, 0, 0);
        rect(xLoc, yLoc - 20, 20, 10);
        break;
      case 1:
        arc(xLoc, yLoc, SIZE, SIZE, QUARTER_PI, 2 * PI - QUARTER_PI);
        fill(255, 0, 0);
        rect(xLoc - 20, yLoc - 20, 20, 10);
        break;
      case 0:
        arc(xLoc, yLoc, SIZE, SIZE, radians (235), radians(500));
        fill(255, 0, 0);
        rect(xLoc, yLoc - 20, 20, 10);
        break;
      default:
        ellipse(xLoc, yLoc, SIZE, SIZE);
        fill(255, 0, 0);
        rect(xLoc, yLoc - 20, 20, 10);
        break;
      }
    } else {
      ellipse(xLoc, yLoc, SIZE, SIZE);
      fill(255, 0, 0);
      switch(dir) {
      case 2:
        rect(xLoc, yLoc + 10, 20, 10);
        break;
      case 3:
        rect(xLoc, yLoc - 20, 20, 10);
        break;
      case 1:
        rect(xLoc - 20, yLoc - 20, 20, 10);
        break;
      case 0:
        rect(xLoc, yLoc - 20, 20, 10);
        break;
      default:
        rect(xLoc, yLoc - 20, 20, 10);
        break;
      }
    }    
    openMouth = !openMouth;
  }
}