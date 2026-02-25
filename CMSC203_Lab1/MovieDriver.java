package gpa;
import java.util.Scanner;
public class MovieDriver {

	public static void main(String[] args) {
		
		boolean repeat = true;
		
		//Task#2 writing a loop
		while(repeat) {
			//create a new object of type scanner that reads from the keyboard
			Scanner userInput = new Scanner(System.in);
			
			//System.out.println(enteredInput);
			
			//create a new movie object
			Movie film = new Movie();
			
			//Prompt the user to enter the title of a movie
			System.out.println("Enter the name of a movie");
			
			//read in the line that the user types
			String movieName =  userInput.nextLine();
			
			//set the title in the movie object
			film.setTitle(movieName);
			
			//Prompt the user to enter the movie's rating
			System.out.println("Enter the movie's rating");
			
			//read in the line that the user types
			String movieRating = userInput.nextLine();
			
			//set the rating in the movie object
			film.setRating(movieRating);
			
			//Prompt the User to enter the number of tickets sold at the theater
			System.out.println("How many tickets were sold?");
			
			//read in the integer that the user types
			int ticetsSold = userInput.nextInt();
			
			//set the number of tickets sold in the movie object
			film.setSoldTickets(ticetsSold);
			
			//print out the information using the movie's toString method
			System.out.println(film.toString());
			
			
			
			
			
			System.out.println("Do you want to enter another? (y or n)");
			String repeated = userInput.next();
			
			switch(repeated) {
			case "y":
				repeat =true;
				break;
			case "n":
				repeat = false;
				System.out.println("Goodbye!");
				break;
			
			}
			
		}
		
		
		
		
		
		System.exit(0);

	}

}
