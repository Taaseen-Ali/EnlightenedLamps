public class Crossword{
    String[][] board;
    int size;
    String[][] answers;

    String name;
    //ClueBank clues = new ClueBank();
    public Crossword(){
	size = 15;
	board =  new String[size][size];
	answers = new String[size][size];
    }

    public void insert(PositionedWord w){
	int dir = w.getPos().getDirection();
	int x = w.getPos().getX();
	int y = w.getPos().getY();

	if(dir = CrosswordPosition.DOWN()){
	    if(y+w.getPos().getLength() < size){
		for(int i=y, i<y+w.getPos.getLength(); i++){
		    answers
	    
