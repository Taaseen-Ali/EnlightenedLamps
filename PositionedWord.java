public class PositionedWord extends Word{
    CrosswordPosition pos;
    public PositionedWord(Word w, CrosswordPosition p){
	super(w.getWord());
	pos = p;
	clue = w.getClue();
	
    }

    public PositionedWord(Word w, int x, int y, int dir){
	super(w.getWord());
	pos = new CrosswordPosition(x, y, LENGTH, dir);
	clue = w.getClue();
    }

    public PositionedWord(Word w){
	super(w.getWord());
	pos = new CrosswordPosition();
	pos.setLength(LENGTH);
    }

    public PositionedWord copy(){
	PositionedWord ret = new PositionedWord(new Word(WORD), pos);
	ret.clue = getClue();
	return ret;
    }

    public CrosswordPosition getPos(){return pos;}

}
