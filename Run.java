import java.awt.*;
import java.util.Scanner;

public class Run {

	public static void smain(String[] args) throws InterruptedException {
		
		Scanner scan = new Scanner(System.in);
		int choice;
		
		System.out.println("Welcome to the Game Board! Which game would you like to play?");
		System.out.println();
		
		System.out.println("Type 1 if you would like to play " + ConsoleColors.RED_BOLD + "M" + ConsoleColors.YELLOW_BOLD + "O" + ConsoleColors.GREEN_BOLD + "N" + ConsoleColors.BLUE_BOLD + "O" + ConsoleColors.PURPLE_BOLD + "P" + ConsoleColors.RED_BOLD + "O"+ ConsoleColors.YELLOW_BOLD + "L" + ConsoleColors.GREEN_BOLD + "Y" + ConsoleColors.RESET + "!  (Does not work yet)");
		System.out.println();
		System.out.println("Type 2 if you would like to play Tic Tac Toe.");
		System.out.println("Type 3 if you would like to play Connect 4.");
		System.out.println("Type 4 if you would like to play Cookie Clicker.");
		System.out.println("Type 5 if you would like to play Hangman.");
		System.out.println("Type 6 if you would like to play Snake.");
		System.out.println();
		
		choice = scan.nextInt();
		
		if (choice == 1) {
			
			Monopoly.startGame();
		}
		
		else if (choice == 2) {
			
			TicTacToe.startGame();
		}
		
		else if (choice == 3) {
			
			Connect4.startGame();
		}
		
		else if (choice == 4) {
			
			CookieClicker.startGame();
		}
		
		scan.close();
	}
	
	
	public static void main(String[] args) throws InterruptedException {
		
		Monopoly.startGame();
	}

}
