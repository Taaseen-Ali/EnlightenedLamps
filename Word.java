import java.util.ArrayList;
class Word{
    final String WORD;
    final int LENGTH;
    final String[] WORDAR;
    String clue;
    
    public Word(String s){
	WORD = s;
	WORDAR = s.split("");
	LENGTH = WORDAR.length;
    }

    public String getWord() {return WORD;}
    
    public String[] getArray() {return WORDAR;}
    
    public int getLength() {return LENGTH;}

    public String getLetter(int i) {return WORDAR[i];}

	// Returns an array of the positions of letter s
    public int[] getLetterPos(String s){
	ArrayList<Integer> temp = new ArrayList<Integer>();
	for(int i=0; i<LENGTH; i++){
	    if(WORDAR[i].equals(s)){
		temp.add(i);
	    }
	}
	int[] retAr = new int[temp.size()];
	for(int i=0; i<temp.size(); i++) retAr[i] = temp.get(i);
	return retAr;
    }

	// Returns an array of common letters between this word and w
    public String[] getCommonLetters(Word w){
	ArrayList<String> temp = new ArrayList<String>();
	for(String letter: WORDAR){
	    for(String otherLetter: w.getArray()){
		if(letter.equals(otherLetter) && !temp.contains(letter)) temp.add(letter);
	    }
	}
	
	String[] retAr = new String[temp.size()];
	for(int i=0; i<temp.size(); i++) retAr[i] = temp.get(i);
	return retAr;
    }
	

    public void setClue(String s){ clue = s;}

    public String getClue(){ return clue;}
}

