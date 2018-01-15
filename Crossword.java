public class Crossword{
    String[][] board;
    int size, numWords;
    String[][] answers;
    PositionedWord wordList[];
    String name;
    String clues;
    public Crossword(){
	numWords = 0;
	size = 25;
	clues = "";
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

    public boolean checkAnswers(){
	for(int i=0; i<size; i++){
	    for(int k=0; k<size; k++){
		if(answers[i][k] != board[i][k]) return false;
	    }
	}
	return true;
    }

    public boolean set(int x, int y, String s){
	if(board[y][x] == null){
	    System.out.println("(" + x + ", " + y + ") is an invalid position");
	    return false;
	}
	else board[y][x] = s;
	return true;
    }
    public void setClues(String c){ clues = CrosswordBuilder.getClues(c);}
    public void printClues(){System.out.println(clues);}
    
    public void printAnswers(){
	System.out.print("    ");
	for(int i=0; i<size; i++) System.out.print("[" + i%10 + "]");
	System.out.println();
	int row = 0;
	for(String[] s: answers){
	    if(row>=10) System.out.print("[" + row + "]");
	    else System.out.print("[" + row + " ]");
	    row++;
	    for(String p: s){
		if(p == null) System.out.print(" · ");
		else System.out.print("[" + p + "]");
	    }
	    System.out.println();
	}
    }

    public void generateBoard(){
	for(int i=0; i<size; i++){
	    for(int k=0; k<size; k++){
		if(answers[i][k]!=null) board[i][k] = " ";
	    }
	}
    }
    public void printBoard(){
	System.out.print("    ");
	for(int i=0; i<size; i++) System.out.print("[" + i%10 + "]");
	System.out.println();
	int row = 0;
	for(String[] s: board){
	    if(row>=10) System.out.print("[" + row + "]");
	    else System.out.print("[" + row + " ]");
	    row++;
	    for(String p: s){
		if(p == null) System.out.print(" · ");
		else System.out.print("[" + p + "]");
	    }
	    System.out.println();
	}
    }
    public static void main(String args[]){
	/*
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
	*/

	Crossword c = CrosswordBuilder.generateFromFile("listOfWords.txt");
	c.setClues("clues.txt");
	c.generateBoard();

	new CrosswordDriver(c).play();
	//	c.printBoard();
	//c.printClues();
	//	c.printAnswers();
	//	CrosswordBuilder.getWords("listOfWords.txt");

    }
}		
