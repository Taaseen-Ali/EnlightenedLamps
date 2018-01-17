import cs1.Keyboard;
class Woo{
    public static void main(String[] args){
	System.out.println("Woo!");
	System.out.println("Upon running this program you will create a randonly generated crossword");
	System.out.println("from the words listed in 'listOfWord.txt' and 'clues.txt.'");
	System.out.println();
	System.out.println("Before you continue make sure that 'listOfWords.txt' and 'clues.txt' exist");
	System.out.println("and that they contain the same number of lines (samples have been provided");
	System.out.println("for you) Each clue in 'clue.txt' corresponds to the word on the same line in");
	System.out.println("'listOfWords.txt'");
	System.out.println();
	boolean running = true;
	while(running){
	    System.out.println("Type 'next' when you are finished editing/viewing necessary files:");
	    Keyboard.readString();
	    System.out.println("generating crossword...");
	    Crossword c = CrosswordBuilder.generateFromFile("listOfWords.txt");
	    c.generateBoard();
	    
	    System.out.println("Would you like to view the crossword before playing it? (enter number)");
	    System.out.println("1)Yes");
	    System.out.println("2)No");
	    System.out.print(":");
	    
	    if(Keyboard.readInt() == 1){
		c.printBoard();
	    }

	    System.out.println("What would you like to do now??");
	    System.out.println("1)Regenerate new crossword randomly");
	    System.out.println("2)play this crossword");
	    System.out.println("3)quit");
	    int ans = Keyboard.readInt();
	    if(ans == 3){
		running = false;
		break;
	    }

	    
	    if(ans == 2){
		new CrosswordDriver(c).play();
	    }
	    
	 
	}
    }
}
