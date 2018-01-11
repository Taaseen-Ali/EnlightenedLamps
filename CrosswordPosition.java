class CrosswordPosition{
    private final  int XCOR, YCOR, LENGTH;
    public static final int ACROSS = 1, DOWN = 2;
    public final int DIRECTION;
    public CrosswordPosition(int x, int y, int length, int dir){
	XCOR = x;
	YCOR = y;
	LENGTH = length;
	DIRECTION = dir;
    }

    public int getLength() {return LENGTH;}

    public int getX() {return XCOR;}

    public int getY() {return YCOR;}

    public int getDirection() {return DIRECTION;}
}

