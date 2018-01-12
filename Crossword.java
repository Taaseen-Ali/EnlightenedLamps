public class Crossword{
    String[][] board;
    int size, numWords;
    String[][] answers;
    PositionedWord wordList[];
    String name;
    //ClueBank clues = new ClueBank();
    public Crossword(){
	numWords = 0;
	size = 15;
	board =  new String[size][size];
	answers = new String[size][size];
	wordList = new PositionedWord[25];
    }

    public void insert(PositionedWord w){
	int dir = w.getPos().getDirection();
	int x = w.getPos().getX();
	int y = w.getPos().getY();

	if(dir == CrosswordPosition.DOWN){
	    if(y+w.getPos().getLength() < size){
		for(int i=0; i<w.getPos().getLength(); i++){
		    answers[i+y][x] = w.getLetter(i);
		}
	    }
	}
	else if(dir == CrosswordPosition.ACROSS){
	    if(x+w.getPos().getLength() < size){
		for(int i=0; i<w.getPos().getLength(); i++){
		    answers[y][i+x] = w.getLetter(i);
		}
	    }
	}
	wordList[numWords++] = w;	
    }

    public void print(){
	for(String[] s: answers){
	    for(String p: s){
		if(p == null) System.out.print("[ ]");
		else System.out.print("[" + p + "]");
	    }
	    System.out.println();
	}
    }

    public static void main(String args[]){
	Crossword c = new Crossword();
	PositionedWord hello = new PositionedWord(new Word("hello"), new CrosswordPosition(9,6,5,CrosswordPosition.DOWN));
	PositionedWord hiker = new PositionedWord(new Word("hiker"), new CrosswordPosition(6,6,5,CrosswordPosition.ACROSS));

	Word hiker2 = new Word("hiker");
	c.insert(hello);
	System.out.println(CrosswordBuilder.collides(c, hiker));
	System.out.println(CrosswordBuilder.getInsertionSites(c, hiker2).length);
	//c.insert(hiker);
	c.print();

	//	CrosswordBuilder.collides(c, new
    }
}		
