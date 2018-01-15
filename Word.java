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
	


    public static void main(String args[]){
	Word a = new Word("hello");
	Word b = new Word("hgkll");
	String[] ar = a.getCommonLetters(b);
	int[] ar2 = a.getLetterPos("l");
	for(String i: ar) System.out.print("[" + i + "]");
    }

    public void setClue(String s){ clue = s;}

    public String getClue(){ return clue;}
}

