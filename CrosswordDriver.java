import cs1.Keyboard;
public class CrosswordDriver {
    Crossword cw;
    public CrosswordDriver(Crossword c){cw = c;}
	
    public void play(){
	System.out.println("============================||Welcome to Crossword!||===========================");
	System.out.println("================================================================================");
	System.out.println("||                               HELP DESK                                    ||");
	System.out.println("================================================================================");
	help();
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

    public void help(){
	System.out.println("You can navigate through the interface by typing in one of the following");
	System.out.println("keywords and pressing enter to do different things;");

	System.out.println("set:            follow the prompts to enter x and y coordinates and the letter which");
	System.out.println("                you would like to enter");
	
	System.out.println("set multiple:   will allow for multiple sets consecutively (to break out of");
	System.out.println("                set multiple please type 'quit' when asked for a letter");

	System.out.println("check answers:  will check if the answers you have put in are correct");

	System.out.println("show clues:     print out clues and corresponding positions");

	System.out.println("show:           will print out the board");

	System.out.println("show answer:    will print out crossword with answers (you filthy cheater!)");

	System.out.println("quit:           will exit from the crossword game (but why woud you do that?)");;

	System.out.println("help:           will bring up this page");;
	
    }
    
    public void printBoard() {
	System.out.println("================================================================================");
	System.out.println("||                                    BOARD                                   ||");
	System.out.println("================================================================================");
	System.out.println("                                  |10|                                          ");
	System.out.println();
	cw.printBoard();
    }
    //get user input for all the arguments required for set and pass is to the Crossword.set() function
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

    //keep setting until set returns false
    public void setMultiple(){
	while(setAnswer()){}
    }
	
    public void showClues() {
	System.out.println("================================================================================");
	System.out.println("||                                     CLUES                                  ||");
	System.out.println("================================================================================");
	cw.printClues();
    }

    //match a user input to a function
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
	case "help":
	    help();
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
