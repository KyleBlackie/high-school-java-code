void setup() {
  //set screen size and colour
  background(0);
  size(600, 600);
  rectMode(CORNERS);
  //variables
  int len = 300;
  int startX = width/2;
  int startY = height;
  //Start rescursive method
  branch(startX, startY, startX, startY - len, len);
  rec(width/2 - len/2, height/2 - len/2, width/2 + len/2, height/2 + len/2, len/2);
}

void branch(int xBot, int yBot, int xTop, int yTop, int len) {
  //randomize stroke weight and colour
  strokeWeight((float)(Math.random()*3) + 1);
  stroke((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255));
  line(xBot, yBot, xTop, yTop);
  //while length is grater than five, draw four more branches
  if (len > 5) {
    branch(xTop, yTop, xTop + len/2, yTop - len/2, len/2);
    branch(xTop, yTop, xTop - len/2, yTop - len/2, len/2);
    branch(xTop, yTop, xTop + len/2, yTop + len/2, len/2);
    branch(xTop, yTop, xTop - len/2, yTop + len/2, len/2);
  }
}

void rec(int LeftX, int LeftY, int RightX, int RightY, int len) {
  //fill rectangles with random colours
  fill((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255));
  //draw rectangle
  rect(LeftX, LeftY, RightX,RightY);
  //if the length is greater than 20 draw four lines to connect the rectangles
  if(len > 20){
    line(LeftX,LeftY, LeftX + len/2, LeftY + len/2);
    line(LeftY, RightX, RightX - len/2, LeftY + len/2);
    line(RightX,RightY, RightX - len/2, RightY - len/2);
    line(RightX, LeftY, RightX - len/2, LeftY + len/2);
    //recursively call rec again
    rec(LeftX + len/2, LeftY + len/2, RightX - len/2,RightY - len/2, len /2);
  }
}