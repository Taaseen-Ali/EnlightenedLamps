class CrosswordPosition{
    private final  int XCOR, YCOR, LENGTH;
    private final String DIRECTION;

    public CrosswordPosition(int x, int y, int length, String dir){
	XCOR = x;
	YCOR = y;
	LENGTH = length;
	DIRECTION = dir;
    }

    public int getLength() {return LENGTH;}

    public int getX() {return XCOR;}

    public int getY() {return YCOR;}

    public String getDirection() {return DIRECTION;}
}

