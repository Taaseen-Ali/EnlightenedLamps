import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

class CrosswordBuilder{
    public static boolean collides(Crossword cw, PositionedWord w){
	int x = w.getPos().getX();
	int y = w.getPos().getY();
	int size = w.getPos().getLength();

	if(w.getPos().getDirection() ==  CrosswordPosition.DOWN){
	    for(int i=0; i<size; i++){
		if((size+y) >= cw.size || y < 0 ||
		   ((cw.answers[i+y][x] != null) && !cw.answers[i+y][x].equals(w.getLetter(i)))){
		    return true;
		}
	    }
	}
	
	if(w.getPos().getDirection() ==  CrosswordPosition.ACROSS){
	    for(int i=0; i<size; i++){
		if((size+x) >= cw.size || x < 0 ||
		   ((cw.answers[y][x+i] != null) &&  !cw.answers[y][x+i].equals(w.getLetter(i)))){
		    return true;
		}
	    }
	}
	return false;

    }
		   

    public static boolean hasAdjacentWords(Crossword cw, PositionedWord w){
	PositionedWord otherWord;
	boolean ret = false;
	int size = w.getPos().getLength();
	int x = w.getPos().getX();
	int y = w.getPos().getY();
	
        for(int i=0; i<cw.numWords; i++){
	    if(ret == true) return true;
	    otherWord = cw.wordList[i];
	    int otherX = otherWord.getPos().getX();
	    int otherY = otherWord.getPos().getY();
	    int otherSize = otherWord.getPos().getLength();
	    
	    if(w.getPos().getDirection() == CrosswordPosition.DOWN){
		if(otherWord.getPos().getDirection() == CrosswordPosition.ACROSS){
		    if(otherY>=y && otherY <=y+size-1){
			ret = (otherX == x+1)||
			    (otherX+otherSize-1 == x-1);
		    }
		    if(otherY == y-1 || otherY == y+size){
			ret = (otherX+otherSize-1 >= x && otherX<=x);
		    }
		}

		if(otherWord.getPos().getDirection() == CrosswordPosition.DOWN){
		    if(otherX <= x+1 && otherX >= x-1){
			ret = (otherY < y+size && otherY+otherSize-1 >= y);
		    }
		    if(otherY+otherSize-1 == y-1 || otherY == y+size){
			ret = (otherX == x);
		    }
		}	    
	    }

	     if(w.getPos().getDirection() == CrosswordPosition.ACROSS){
		if(otherWord.getPos().getDirection() == CrosswordPosition.DOWN){
		    if(otherX>=x && otherX <=x+size-1){
			ret = (otherY == y+1)||
			    (otherY+otherSize-1 == y-1);
		    }
		    if(otherX == x-1 || otherX == x+size){
			ret = (otherY+otherSize-1 >= y && otherY<=y);
		    }
		}

		if(otherWord.getPos().getDirection() == CrosswordPosition.ACROSS){
		    if(otherY <= y+1 && otherY >= y-1){
			ret = (otherX < x+size && otherX+otherSize-1 >= x);
		    }
		    if(otherX+otherSize-1 == x-1 || otherX == x+size){
			ret = (otherY == y);
		    }
		}	    
	    }
	}
	return ret;
     
    }
  public static CrosswordPosition[] getInsertionSites(Crossword cw, Word w){
	ArrayList<CrosswordPosition> positions = new ArrayList<CrosswordPosition>();
	PositionedWord word = new PositionedWord(w);
	if(cw.numWords==0){
	    CrosswordPosition[] start = {new CrosswordPosition(cw.size/2, cw.size/2,
							       word.getPos().getLength(),
							       CrosswordPosition.ACROSS)};
	    return start;
	}
	
	for(int k=0; k<cw.numWords; k++){
	    PositionedWord otherWord = cw.wordList[k];
	    if(otherWord.getPos().getDirection() == CrosswordPosition.DOWN){
		word.getPos().setDirection(CrosswordPosition.ACROSS);
		if(word.getCommonLetters(otherWord).length > 0){
		    int x = otherWord.getPos().getX();
		    int y = otherWord.getPos().getY();
		    for(String s: word.getCommonLetters(otherWord)){
			for(int i: otherWord.getLetterPos(s)){
			    word.getPos().setY(i+y);
			    word.getPos().setX(x-word.getLetterPos(s)[0]);
			    if(!collides(cw, word) && !hasAdjacentWords(cw, word)){
				positions.add(word.getPos().copy());
			    }
			}
		    }
		}
	    }

	    if(otherWord.getPos().getDirection() == CrosswordPosition.ACROSS){
		word.getPos().setDirection(CrosswordPosition.DOWN);
		if(word.getCommonLetters(otherWord).length > 0){
		    int x = otherWord.getPos().getX();
		    int y = otherWord.getPos().getY();
		    for(String s: word.getCommonLetters(otherWord)){
			for(int i: otherWord.getLetterPos(s)){
			    word.getPos().setY(y-word.getLetterPos(s)[0]);
			    word.getPos().setX(i+x);
			    if(!collides(cw, word) && !hasAdjacentWords(cw, word)){
				positions.add(word.getPos().copy());
			    }
			}
		    }
		}
	    }
	}

	CrosswordPosition[] retAr = new CrosswordPosition[positions.size()];
	for(int i=0; i< positions.size(); i++){
	    retAr[i] = positions.get(i);
	}
	return retAr;
    }

    
    public static ArrayList<Word> insert(Crossword cw, Word[] words){
	ArrayList<Word> toInsert = new ArrayList<Word>();
	for(Word w: words){
	    toInsert.add(w);
	}
	int numTries=0;
	int size=toInsert.size();
	while(toInsert.size()>0){
	    size = toInsert.size();
	    for(int i=0; i<toInsert.size(); i++){
		Word w = toInsert.get(i);
		CrosswordPosition[] pos = getInsertionSites(cw, w);
		if(pos.length>0){
		    cw.insert(new PositionedWord(w, pos[(int)(Math.random()*pos.length)]));
		    toInsert.remove(w);
		    numTries=0;
		}
	     }
	    int newSize = toInsert.size();
	    if(size==newSize) numTries++;
	    if(numTries>1) break;
	}
	return toInsert;
    }

    public static Word[] getWords(String f){
	ArrayList<Word> words = new ArrayList<Word>();
	Word[] ret = new Word[0];
	File file = new File(f);

	try{
	    Scanner scanner = new Scanner(file);
	    while (scanner.hasNextLine()) {
		String line = scanner.nextLine();
		words.add(new Word(line));
	    }
	    ret = new Word[words.size()];
	    for(int i=0; i<words.size(); i++){
		ret[i] = words.get(i);
	    }
	}

	catch (FileNotFoundException e){
	    System.out.println("file no bueno");
	}
	
	return ret;
    }

    public static String[] getClues(String f){
	ArrayList<String> clues = new ArrayList<String>();
	File file = new File(f);
	String ret[];
	try{
	    Scanner scanner = new Scanner(file);
	    while (scanner.hasNextLine()) {
		String line = scanner.nextLine();
		clues.add(line);
	    }
	}

	catch (FileNotFoundException e){
	    System.out.println("file no bueno");
	}

	ret = new String[clues.size()];
	
	for(int i=0; i<clues.size(); i++){
	    ret[i] = clues.get(i);
	}
	return ret;
    }
	
    public static Crossword generateFromFile(String f){
	Crossword c = new Crossword();
	String[] clues = getClues("clues.txt");
	Word[] words = getWords(f);
	for(int i=0; i<words.length; i++)
	    words[i].setClue(clues[i]);
	insert(c, words);
	return c;
    }
}

