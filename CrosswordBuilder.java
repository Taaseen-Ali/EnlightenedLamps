import java.util.ArrayList;
class CrosswordBuilder{
    public static boolean collides(Crossword cw, PositionedWord w){
	int x = w.getPos().getX();
	int y = w.getPos().getY();

	if(w.getPos().getDirection() == CrosswordPosition.DOWN){
	    for(int i=0; i<w.getPos().getLength(); i++){
		if(!(cw.answers[i+y][x] == null)){
		    if(!cw.answers[i+y][x].equals(w.getLetter(i))){
			return true;
		    }
		}
	    }
	}

	if(w.getPos().getDirection() == CrosswordPosition.ACROSS){
	    for(int i=0; i<w.getPos().getLength(); i++){
		if(!(cw.answers[y][i+x] == null)){
		    if(!cw.answers[y][i+x].equals(w.getLetter(i))){
			return true;
		    }
		}
	    }
	}
	return false;
    }


    public static CrosswordPosition[] getInsertionSites(Crossword cw, Word w){
	ArrayList<CrosswordPosition> positions = new ArrayList<CrosswordPosition>();
	PositionedWord word = new PositionedWord(w);

	for(PositionedWord otherWord: cw.wordList){
	    System.out.println(otherWord.getWord());
	    if(otherWord.getPos().getDirection() == CrosswordPosition.DOWN){
		word.getPos().setDirection(CrosswordPosition.ACROSS);
		if(word.getCommonLetters(otherWord).length > 0){
		    int x = word.getPos().getX();
		    int y = word.getPos().getY();
		    for(String s: word.getCommonLetters(otherWord)){
			for(int i: word.getLetterPos(s)){
			    word.getPos().setY(i+y);
			    word.getPos().setX(x);
			    if(!collides(cw, word)) positions.add(word.getPos());
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
}
