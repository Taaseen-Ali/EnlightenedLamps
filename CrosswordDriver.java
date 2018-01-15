import cs1.Keyboard;
public class CrosswordDriver {
    Crossword cw;
    public CrosswordDriver(Crossword c){cw = c;}
	
    public void play(){
	System.out.println("============================||Welcome to Crossword!||===========================");
	printBoard();
	showClues();
	String k;
	while(true){
	    System.out.println("================================================================================");
	    System.out.println("||                                                                            ||");
	    System.out.println("================================================================================");
	    System.out.println();
	    System.out.print("|Enter a command|$");
	    k = Keyboard.readString();
	    if(k.equals("quit")) return;
	    keyword(k);
	}
    }
	
    public void printBoard() {
	System.out.println("================================================================================");
	System.out.println("||                                    BOARD                                   ||");
	System.out.println("================================================================================");
	System.out.println();
	cw.printBoard();
    }
	
    public boolean  setAnswer() {
	int x, y;
	String s;
	System.out.print("x: ");
	x = Keyboard.readInt();
	System.out.print("y: ");
	y = Keyboard.readInt();
	System.out.print("Letter: ");
	s = Keyboard.readString();
	if(s.length() != 1){
	    System.out.println("Invalid String/Quiting Set");
	    return false;
	}
	cw.set(x,y,s);
	printBoard();
	return true;
	
    }

    public void setMultiple(){
	while(setAnswer()){}
    }
	
    public void showClues() {
	cw.printClues();
    }
	
    public void keyword(String s) {
        switch(s){
	case "set":
	    setAnswer();
	    break;
	case "set multiple":
	    setMultiple();
	    break;
	case "show clues":
	    showClues();
	    break;
	case "show answers":
	    showAnswers();
	    break;
	case "check answers":
	    if(checkCrossword())System.out.println("You win:");
	    System.out.println("no win yet");
	    break;
	case "show":
	    printBoard();
	    break;
	default:
	    System.out.println("Keyword not found");
	    break;
	}
	    
	
	    
    }
	
    public void showAnswers() {
	System.out.println("================================================================================");
	System.out.println("||                                  ANSWERS                                   ||");
	System.out.println("================================================================================");
	cw.printAnswers();
    }
	
    public boolean checkCrossword() {
	return cw.checkAnswers();
    }
	
}
