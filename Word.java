import java.util.ArrayList;
class Word{
    final String WORD;
    final int LENGTH;
    final String[] WORDAR;

    public Word(String s){
	WORD = s;
	WORDAR = s.split("");
	LENGTH = WORDAR.length;
    }

    public String getWord() {return WORD;}
    
    public String[] getArray() {return WORDAR;}
    
    public int getLength() {return LENGTH;}

    public String getLetter(int i) {return WORDAR[i];}
    
    public int[] getCommonLetterPos(Word w){
        ArrayList<int> retAr = new ArrayList<int>();
	for(int i=0; i < Math.min(this.getLength(), w.getLength()); i++){
	    if(this.getLetter(i).equals(w.getLetter(i))){
		retAr.add(i);
	    }
	}
	return retAr.toArray();
    }
}

