import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GuessTheMovieMain {
	static boolean hasWon = false;
	private static Scanner scan;
	private static Scanner userscan;
	private static StringBuilder builder;
	
	public static void main(String[] args) throws Exception{
		try {
			File file = new File("movies.txt"); //opens file
			scan = new Scanner(file);
			userscan = new Scanner(System.in);
			String movie = ""; //holds movie name
			String answer = ""; //underscores
			String userIn = ""; //holds user input
			String wrong = ""; //holds wrong letters
			int guesses = 0;
			int randomMovie = (int) (Math.random() *25)+1; //randomize movie
			int counter = 0; //keeps track of which
			
//			System.out.println("random num is: "+ randomMovie); //for testing purposes
			
			//chooses which movie
			while(scan.hasNextLine() && counter != randomMovie) {
				movie = scan.nextLine();
				counter++;
			}
			answer = NumUnderscores(movie); //gets the number of underscores 
			
			//while the user has not won or guessed wrong 10 times
			while(!hasWon && guesses < 10) {
				System.out.println("You are guessing: " + answer);
				System.out.println("Guess a letter: ");
				userIn = userscan.nextLine(); //scans for user input
				if(userIn.length() != 1) {
					System.out.println("Type only 1 letter, try again.");
				}
				else if (IsInAnswer(movie, userIn)) { //if input is in the answer
					answer = EditAnswer(movie, answer, userIn); //change output
				}else {
					wrong += userIn + " "; //appends the wrong letter to wrong output
					guesses++;
				}
				System.out.println("You have guessed (" + guesses + ") wrong letters: " + wrong);
			}
			if(hasWon) {
				System.out.println("You Win! You have guessed " + movie + " correctly!");
			}else {
				System.out.println("Sorry, the movie was: " + movie);
			}
			
		} catch (FileNotFoundException e) {
			System.out.println("File not found: " + e);
		}
		
		
	}
	
	//replace movie title with underscores
	private static String NumUnderscores(String movie) {
		String underscores = movie.replaceAll("[a-zA-Z]", "_"); 
		return underscores;
	}
	
	//changes answer if a char is found
	//if there are no more underscores, hasWon = true
	private static String EditAnswer(String movie, String answer, String letter) {
		builder = new StringBuilder();
		char edit[] = answer.toCharArray(); //contains to-be edited chars
		char ch; //contains char to-be replaced
		String chletter = ""; //to compare using String.valueOf()
		
		for(int i=0; i<movie.length();i++) { //traverse thru String
			ch = movie.charAt(i);
			chletter = String.valueOf(ch);
			if(chletter.equals(letter)) {
				edit[i] = ch;
			}
		}
		for(char value : edit) {
			builder.append(value);
		}
		String editted = builder.toString();
		if(!editted.contains("_")) hasWon = true;
		return editted; 
	}
	
	//if letter is in answer, return true
	private static boolean IsInAnswer(String movie, String letter) {
		if(movie.contains(letter)) {
			return true;
		}
		return false;
	}

}
