public class PositionedWord extends Word{
    CrosswordPosition pos;
    public PositionedWord(Word w, CrosswordPosition p){
	super(w.getWord());
	pos = p;
	
    }

    public CrosswordPosition getPos(){return pos;}
}
