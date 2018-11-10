import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GuessTheMovieMain {

	public static void main(String[] args) throws Exception{
		try {
			File file = new File("movies.txt"); //opens file
			Scanner scan = new Scanner(file); //scans file
			Scanner userscan = new Scanner(System.in); //takes in user input
			String movie = ""; //holds movie name
			String answer = ""; //underscores
			String user = ""; //holds user input
			String wrong = ""; //holds wrong letters
			boolean hasWon = false;
			int guesses = 0;
			int randomMovie = (int) (Math.random() *25)+1; //randomize movie
			int counter = 0; //keeps track of which
			
			System.out.println("random num is: "+ randomMovie);
			
			//chooses which movie
			while(scan.hasNextLine() && counter != randomMovie) {
				movie = scan.nextLine();
				counter++;
			}
			answer = NumUnderscores(movie); //gets the number of underscores 
			System.out.println("You are guessing: " + answer);
			
			//while the user has not won or guessed wrong 10 times
			while(!hasWon && guesses < 10) {
				System.out.println("Guess a letter: ");
				user = userscan.nextLine(); //scans for user input
				if (IsInAnswer(user)) { //if input is in the answer
					EditAnswer(user); //change output
				}else {
					wrong += user + " ";
					System.out.println("You have guessed (" + ++guesses + ") wrong letters: " + wrong);
				}
			}
			
			
			System.out.println("The movie is: " + movie);
			
			
			
			
		} catch (FileNotFoundException e) {
			System.out.println("File not found: " + e);
		}
		
		
	}
	
	private static String NumUnderscores(String movie) {
		String underscores = movie.replaceAll("[a-zA-Z]", "_"); 
		return underscores;
	}
	
	//changes answer if a char is found
	private static void EditAnswer(String answer) {
		System.out.println("edit answer");
		//TODO if there are no more underscores, hasWon = true
	}
	
	//if letter is in answer,
	private static boolean IsInAnswer(String letter) {
		//TODO if letter is in answer, return true
		return false;
	}

}
