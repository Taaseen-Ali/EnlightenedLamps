public class PositionedWord extends Word{
    CrosswordPosition pos;
    public PositionedWord(Word w, CrosswordPosition p){
	super(w.getWord());
	pos = p;
	
    }

    public PositionedWord(Word w, int x, int y, int dir){
	super(w.getWord());
	pos = new CrosswordPosition(x, y, LENGTH, dir);
    }

    public PositionedWord(Word w){
	super(w.getWord());
	pos = new CrosswordPosition();
    }

    public CrosswordPosition getPos(){return pos;}
}
