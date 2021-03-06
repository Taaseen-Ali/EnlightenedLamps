class CrosswordPosition{
    private  int xcor, ycor, length, direction;
    public static final int ACROSS = 1, DOWN = 2; //constants to be used across the program
    public CrosswordPosition(int x, int y, int length, int dir){
	xcor = x;
	ycor = y;
	this.length = length;
	direction = dir;
    }

    public CrosswordPosition(){
	xcor = -1;
	ycor = -1;
	length = -1;
	direction = -1;
    }

    public int getLength() {return length;}

    public int getX() {return xcor;}

    public int getY() {return ycor;}

    public int getDirection() {return direction;}

    public void setX(int i) {xcor = i;}
    
    public void setY(int i) {ycor = i;}

    public void setDirection(int i) {direction = i;}

    public void setLength(int i) {length = i;}
    
    //returns a copy of the crossword
    public CrosswordPosition copy() {return new CrosswordPosition(xcor, ycor, length, direction);}
}

