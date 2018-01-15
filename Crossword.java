public class Crossword{
    String[][] board;
    int size, numWords;
    String[][] answers;
    PositionedWord wordList[];
    String name;
    //ClueBank clues = new ClueBank();
    public Crossword(){
	numWords = 0;
	size = 25;
	board =  new String[size][size];
	answers = new String[size][size];
	wordList = new PositionedWord[25];
	wordList[0] = new PositionedWord(new Word("yup"), 1,1,1);
    }

    public void insert(PositionedWord w){
	int dir = w.getPos().getDirection();
	int x = w.getPos().getX();
	int y = w.getPos().getY();

	if(dir == CrosswordPosition.DOWN){
	    if(y+w.getPos().getLength() <= size){
		System.out.println("DOWN");
		System.out.println(w.getWord());
		for(int i=0; i<w.getPos().getLength(); i++){
		    answers[i+y][x] = w.getLetter(i);
		}
		wordList[numWords] = w;
		numWords +=1;
	    }
	}
	else if(dir == CrosswordPosition.ACROSS){
	    if(x+w.getPos().getLength()<= size){
		System.out.println("ACROSS");
		System.out.println(w.getWord());
		for(int i=0; i<w.getPos().getLength(); i++){
		    answers[y][i+x] = w.getLetter(i);
		}
		wordList[numWords] = w;
		numWords+=1;
	    }
	}

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
	Word  hiker, friend, brown, goodbye, crying, coding, happiness, stuyvesant, purple, interesting, rowdy, socioeconomic, zebra, thahmina;
	hiker = new Word("hiker");
	friend = new Word("friend");
	brown = new Word("brown");
	goodbye = new Word("goodbye");
	crying = new Word("crying");
	coding = new Word("coding");
	stuyvesant = new Word("stuyvesant");
	purple = new Word("purple");
        interesting = new Word("interesting");
        rowdy = new Word("rowdy");
	socioeconomic = new Word("socioeconomic");
	zebra = new Word("zebra");
	happiness = new Word("happiness");
	thahmina = new Word("thahmina");
	
	Word[] words = {hiker, friend, goodbye, crying, brown, coding,
			happiness, stuyvesant, purple, interesting, rowdy, socioeconomic, zebra, thahmina}; 
	
	PositionedWord hello = new PositionedWord(new Word("hello"), new CrosswordPosition(5,8,5,CrosswordPosition.DOWN));
	PositionedWord shit = new PositionedWord(new Word("shit"), new CrosswordPosition(7,5,4,CrosswordPosition.DOWN));

	PositionedWord frnd = new PositionedWord(friend, new CrosswordPosition(2,9,6, CrosswordPosition.ACROSS));
	c.insert(hello);

	c.print();
	CrosswordBuilder.insert(c, words);
	c.print();


    }
}		
