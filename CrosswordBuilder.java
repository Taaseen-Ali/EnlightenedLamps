import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

class CrosswordBuilder{

    //returns true if w's CrosswordPosiiton conflicts with the words already placed in cw
    //eg. w intersects a word in cw without a common letter
    public static boolean collides(Crossword cw, PositionedWord w){
	int x = w.getPos().getX();
	int y = w.getPos().getY();
	int size = w.getPos().getLength();

	if(w.getPos().getDirection() ==  CrosswordPosition.DOWN){ //if the word is vertical
	    for(int i=0; i<size; i++){
		if((size+y) >= cw.size || y < 0 ||  //check to see if the wotd is within the crossword bounds
		   ((cw.answers[i+y][x] != null) && !cw.answers[i+y][x].equals(w.getLetter(i)))){  //iterate through a...
		    return true;                                                            //collumn to see if there are any letter conflicts
		}
	    }
	}

	//do the same thing if the word is horizontal, except iterate through a row
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
		   
    //returns true if the CrosswordPosition of w causes w to be directly adjacent to
    //the words already placed in cw
    public static boolean hasAdjacentWords(Crossword cw, PositionedWord w){
	PositionedWord otherWord;
	boolean ret = false;
	int size = w.getPos().getLength();
	int x = w.getPos().getX();
	int y = w.getPos().getY();
	
        for(int i=0; i<cw.numWords; i++){
	    //if there is ever an adjacent word positioning found, return true and exit the function
	    if(ret == true) return true;
	    otherWord = cw.wordList[i];
	    int otherX = otherWord.getPos().getX();
	    int otherY = otherWord.getPos().getY();
	    int otherSize = otherWord.getPos().getLength();

	    //Where 'hello' is  w
	    /*to check word positions similar to

	    h e y
	      h
	      e
	      l
	      l
	      o

	      do the following:
	     */
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
		
		 /*to check word positions similar to

		   h h 
		   e e 
		   l y
		   l
		   o

		   do the following:
		 */

		if(otherWord.getPos().getDirection() == CrosswordPosition.DOWN){
		    if(otherX <= x+1 && otherX >= x-1){
			ret = (otherY < y+size && otherY+otherSize-1 >= y);
		    }
		    if(otherY+otherSize-1 == y-1 || otherY == y+size){
			ret = (otherX == x);
		    }
		}	    
	    }

	    /*to check word positions similar to
	     
	      y
	      e
	      h h e l l o

	      do the following:
	     */

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

		 /*to check word positions similar to
	     
		   h e y
		     h e l l o

		   do the following:
		 */

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
	//return true if word falls into one of the above categories
	return ret;
     
    }
    
    //returns an array of CrosswordPositions with ALL viable positions to which word can be
    //inserted into the crossword
    public static CrosswordPosition[] getInsertionSites(Crossword cw, Word w){
	ArrayList<CrosswordPosition> positions = new ArrayList<CrosswordPosition>();
	PositionedWord word = new PositionedWord(w);
	if(cw.numWords==0){
	    CrosswordPosition[] start = {new CrosswordPosition(cw.size/2, cw.size/2,
							       word.getPos().getLength(),
							       CrosswordPosition.ACROSS)};
	    return start;
	}
	
	for(int k=0; k<cw.numWords; k++){ //for every word in the crossword
	    PositionedWord otherWord = cw.wordList[k];
	    if(otherWord.getPos().getDirection() == CrosswordPosition.DOWN){  //compare orientation of the word in the crossword
		word.getPos().setDirection(CrosswordPosition.ACROSS);         // to the word you want to insert. Based on the diff orientations,
		if(word.getCommonLetters(otherWord).length > 0){              // do the folowing
		    int x = otherWord.getPos().getX();
		    int y = otherWord.getPos().getY();
		    for(String s: word.getCommonLetters(otherWord)){         //get all of the common letters between the two words
			for(int i: otherWord.getLetterPos(s)){               //get the positions of these leters
			    word.getPos().setY(i+y);                        
			    word.getPos().setX(x-word.getLetterPos(s)[0]);  
			    if(!collides(cw, word) && !hasAdjacentWords(cw, word)){  //for each intersection, see if there are any colisions/ adjacent words
				positions.add(word.getPos().copy());                 //if not, add this position to the list of viable positions
			    }
			}
		    }
		}
	    }

	    if(otherWord.getPos().getDirection() == CrosswordPosition.ACROSS){ //for opposite orientation: (same steps followed as before, just with reversed x/y)
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

	CrosswordPosition[] retAr = new CrosswordPosition[positions.size()];  //copy the arraylist to an array and return the array
	for(int i=0; i< positions.size(); i++){
	    retAr[i] = positions.get(i);
	}
	return retAr;
    }

    //inserts an array of words into a crossword
    public static ArrayList<Word> insert(Crossword cw, Word[] words){
	ArrayList<Word> toInsert = new ArrayList<Word>();
	for(Word w: words){         //copy the array into an arraylist
	    toInsert.add(w);
	}
	int numTries=0;
	int size=toInsert.size();
	while(toInsert.size()>0){       //while there are still words in toInsert
	    size = toInsert.size();
	    for(int i=0; i<toInsert.size(); i++){  //try to insert them in order of appearance
		Word w = toInsert.get(i);
		CrosswordPosition[] pos = getInsertionSites(cw, w);
		if(pos.length>0){
		    cw.insert(new PositionedWord(w, pos[(int)(Math.random()*pos.length)]));
		    toInsert.remove(w);
		    numTries=0;
		}
	     }
	    int newSize = toInsert.size();
	    if(size==newSize) numTries++;   //if no words were able to be inserted, add one to numtries
	    if(numTries>1) break;  //if numtries > 1, return as there are no more possible insertion sites left for the remaining words
	}   //if there are still possible insertion sites, keep going from the begining of the remaining list
	return toInsert;
    }

    //gets word from a file
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

    //gets clues from a file
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

    //generates a new random crossword from scratch from a file of word
    public static Crossword generateFromFile(String f){
	Crossword c = new Crossword();  //make new crossword
	String[] clues = getClues("clues.txt"); //get the clues
	Word[] words = getWords(f);  //get the words
	for(int i=0; i<words.length; i++)  
	    words[i].setClue(clues[i]);  //link the clues with the corresponding words
	insert(c, words);  //inseert the words into the crossword randomly
	return c; //return generated crossword
    }
}

