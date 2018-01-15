public class Crossword{
    String[][] board;
    int size, numWords;
    String[][] answers;
    PositionedWord wordList[];
    String clues;
    public Crossword(){
	numWords = 0;
	size = 25;
	clues = "";
	board =  new String[size][size];
	answers = new String[size][size];
	wordList = new PositionedWord[25];
    }

    public void insert(PositionedWord w){
	int dir = w.getPos().getDirection();
	int x = w.getPos().getX();
	int y = w.getPos().getY();

	if(dir == CrosswordPosition.DOWN){
	    if(y+w.getPos().getLength() <= size){
		for(int i=0; i<w.getPos().getLength(); i++){
		    answers[i+y][x] = w.getLetter(i);
		}
		wordList[numWords] = w;
		numWords +=1;
	    }
	}
	else if(dir == CrosswordPosition.ACROSS){
	    if(x+w.getPos().getLength()<= size){
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
      
    public void printClues(){
	for(int i=0; i<numWords; i++){
	    PositionedWord w = wordList[i];
	    System.out.print("(" + w.getPos().getX() + ", " + w.getPos().getY() + "), ");
	    if(w.getPos().getDirection() == CrosswordPosition.DOWN) System.out.print("DOWN: ");
	    else System.out.print("ACROSS: ");
	    System.out.println(w.getClue());
	}
    }
    
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

	Crossword c = CrosswordBuilder.generateFromFile("listOfWords.txt");

	c.generateBoard();

	new CrosswordDriver(c).play();

    }
}		
