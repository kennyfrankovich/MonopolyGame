import java.util.*;

@SuppressWarnings("static-access")
public class Connect4 {
	
	public static boolean columnFull = false;
	
	enum Player {

		RED {
			
			public String toString() {
				
				return ConsoleColors.RED + "RED" + ConsoleColors.RESET;
			}
		},
		
		YELLOW {
			
			public String toString() {
				
				return ConsoleColors.YELLOW + "YELLOW" + ConsoleColors.RESET;
			}
		};

		public static String playPiece(Player player) {
			
			String color = player.toString();
			
			if (color.equals(ConsoleColors.RED + "RED" + ConsoleColors.RESET)) {
				
				return ConsoleColors.RED + "O" + ConsoleColors.RESET;
			}
			else if (color.equals(ConsoleColors.YELLOW + "YELLOW" + ConsoleColors.RESET)) {
				
				return ConsoleColors.YELLOW + "O" + ConsoleColors.RESET;
			}
			else return "O";
		}
	}
	
	public static void startGame() throws InterruptedException {
		
		Scanner scan = new Scanner(System.in);
		int choice;
		
		System.out.println();
		System.out.println("Connect 4:");
		System.out.println();
		System.out.println("Type 1 if you would like to play with two players");
		System.out.println("Type 2 if you would like to play against a randomly placing AI");
		
		choice = scan.nextInt();
		
		if (choice == 1) {
			
			Connect4TwoPlayer();
		}
		else if (choice == 2) {
			
			Connect4RandomAI();
		}
		
		scan.close();
	}
	
	
	public static void Connect4TwoPlayer() {
		
		Scanner scan = new Scanner(System.in);
		String[][] board = new String[6][7];
		Player player = Player.RED;
		
		
		//Place nothing values in each spot on the board
		
		for (int i = 0; i < 6; i++) {
			
			for (int j = 0; j < 7; j++) {
				
				board[i][j] = "_";
			}
		}
		
		while (checkForWin(board) == 'N') {
			
			System.out.println();
			System.out.println("It is " + player.toString() + "'s turn");
			
			board = runConnect4(board, player, scan);
			
			System.out.println();
			System.out.println("The " + player.toString() + " player played their piece");
			
			if (player == player.RED) player = player.YELLOW;
			else if (player == player.YELLOW) player = player.RED;
		}
		
		if (player == player.RED) player = player.YELLOW;
		else if (player == player.YELLOW) player = player.RED;
		
		printBoard(board);
		
		if (checkForWin(board) == 'R') {
			
			System.out.println();
			System.out.println("Congratulations " + player.toString() + " player! You won!");
		}
		
		else if (checkForWin(board) == 'Y') {
			
			System.out.println();
			System.out.println("Congratulations " + player.toString() + " player! You won!");
		}
		
		scan.close();
	}
	
	
	public static void Connect4RandomAI() throws InterruptedException {
		
		Scanner scan = new Scanner(System.in);
		String[][] board = new String[6][7];
		
		Player player = Player.RED;
		
		
		//Place nothing values in each spot on the board
		
		for (int i = 0; i < 6; i++) {
			
			for (int j = 0; j < 7; j++) {
				
				board[i][j] = "_";
			}
		}
		
		while (checkForWin(board) == 'N') {
			
			player = player.RED;
			
			System.out.println();
			System.out.println("It is your turn now");
			
			board = runConnect4(board, player, scan);
			
			if (checkForWin(board) != 'N') {
				
				printBoard(board);
				break;
			}
			
			printBoard(board);
			
			player = player.YELLOW;
			
			System.out.print("The AI is thinking");
			for (int i = 0; i < 2; i++) {
				
				System.out.print(".");
				Thread.sleep(1000);
			}
			System.out.println(".");
			Thread.sleep(500);
			System.out.println();
			System.out.println("The AI played it's piece.");
			System.out.println();
			
			board = runConnect4AI(board, player, scan);
		}
		
		if (checkForWin(board) == 'R') {
			
			System.out.println("Congratulations! You beat the AI!");
		}
		else {
			
			System.out.println("How did you lose? You suck");
		}
		
		
		scan.close();
	}
	
	public static int randomNum() {
		
		Random random = new Random();
		int num = 8;
		
		num = random.nextInt(7);
		
		while (num > 6) {
			
			num = random.nextInt(7);
		}
		
		return num;
	}
	
	
	
	public static String[][] runConnect4(String[][] board, Player player, Scanner scan){
		
		//Print out board with column numbers
		printBoard(board);
		
		System.out.println("Where would you like to play your piece? Columns 1 through 7");
		
		int column = scan.nextInt()-1;
		
		while (column > 7) {
			
			System.out.println("That number isn't in range. Try again.");
			column = scan.nextInt()-1;
		}
			
		board = playPiece(board, column, player);
		
		while (columnFull == true) {
			
			column = randomNum();
			board = playPiece(board, column, player);
		}
		
		checkIfFull(board);
		
		columnFull = false;
		
		
		return board;
	}
	
	
	public static String[][] runConnect4AI(String[][] board, Player player, Scanner scan){
		
		//Print out board with column numbers
		printBoard(board);
		
		int column = randomNum();
		
		board = playPiece(board, column, player);
		
		while (columnFull == true) {
			
			column = randomNum();
			board = playPiece(board, column, player);
		}
		
		checkIfFull(board);
		
		columnFull = false;
		
		return board;
	}


	public static char checkForWin(String[][] board) {
		
		// Checks verticals
		
		for (int i = 0; i < 7; i++) {
			
			for (int j = 0; j < 3; j++) {
				
				if (board[j][i].equals(ConsoleColors.RED + "O" + ConsoleColors.RESET)) {
					
					if (board[j+1][i].equals(ConsoleColors.RED + "O" + ConsoleColors.RESET)) {
						
						if (board[j+2][i].equals(ConsoleColors.RED + "O" + ConsoleColors.RESET)) {
							
							if (board[j+3][i].equals(ConsoleColors.RED + "O" + ConsoleColors.RESET)) {
								
								return 'R';
							}
						}
					}
				}
				else if (board[j][i].equals(ConsoleColors.YELLOW + "O" + ConsoleColors.RESET)) {
					
					if (board[j+1][i].equals(ConsoleColors.YELLOW + "O" + ConsoleColors.RESET)) {
						
						if (board[j+2][i].equals(ConsoleColors.YELLOW + "O" + ConsoleColors.RESET)) {
							
							if (board[j+3][i].equals(ConsoleColors.YELLOW + "O" + ConsoleColors.RESET)) {
								
								return 'Y';
							}
						}
					}
				}	
			}
		}

		
		// Checks horizontals
		
		for (int i = 0; i < 6; i++) {
			
			for (int j = 0; j < 4; j++) {
				
				if (board[i][j].equals(ConsoleColors.RED + "O" + ConsoleColors.RESET)) {
					
					if (board[i][j+1].equals(ConsoleColors.RED + "O" + ConsoleColors.RESET)) {
						
						if (board[i][j+2].equals(ConsoleColors.RED + "O" + ConsoleColors.RESET)) {
							
							if (board[i][j+3].equals(ConsoleColors.RED + "O" + ConsoleColors.RESET)) {
								
								return 'R';
							}
						}
					}
				}
				else if (board[i][j].equals(ConsoleColors.YELLOW + "O" + ConsoleColors.RESET)) {
					
					if (board[i][j+1].equals(ConsoleColors.YELLOW + "O" + ConsoleColors.RESET)) {
						
						if (board[i][j+2].equals(ConsoleColors.YELLOW + "O" + ConsoleColors.RESET)) {
							
							if (board[i][j+3].equals(ConsoleColors.YELLOW + "O" + ConsoleColors.RESET)) {
								
								return 'Y';
							}
						}
					}
				}	
			}
		}
		
		
		
		// Checks ascending diagonals
		
		for (int j = 0; j < 4; j++) {
			
			for (int i = 3; i < 6; i++) {
				
				if (board[i][j].equals(ConsoleColors.RED + "O" + ConsoleColors.RESET)) {
					
					if (board[i-1][j+1].equals(ConsoleColors.RED + "O" + ConsoleColors.RESET)) {
						
						if (board[i-2][j+2].equals(ConsoleColors.RED + "O" + ConsoleColors.RESET)) {
							
							if (board[i-3][j+3].equals(ConsoleColors.RED + "O" + ConsoleColors.RESET)) return 'R';
						}
					}
				}
				
				if (board[i][j].equals(ConsoleColors.YELLOW + "O" + ConsoleColors.RESET)) {
					
					if (board[i-1][j+1].equals(ConsoleColors.YELLOW + "O" + ConsoleColors.RESET)) {
						
						if (board[i-2][j+2].equals(ConsoleColors.YELLOW + "O" + ConsoleColors.RESET)) {
							
							if (board[i-3][j+3].equals(ConsoleColors.YELLOW + "O" + ConsoleColors.RESET)) return 'Y';
						}
					}
				}
			}
		}
		
		
		
		// Checks descending diagonals
		
		for (int j = 0; j < 4; j++) {
			
			for (int i = 0; i < 3; i++) {
				
				if (board[i][j].equals(ConsoleColors.RED + "O" + ConsoleColors.RESET)) {
					
					if (board[i+1][j+1].equals(ConsoleColors.RED + "O" + ConsoleColors.RESET)) {
						
						if (board[i+2][j+2].equals(ConsoleColors.RED + "O" + ConsoleColors.RESET)) {
							
							if (board[i+3][j+3].equals(ConsoleColors.RED + "O" + ConsoleColors.RESET)) return 'R';
						}
					}
				}
				
				if (board[i][j].equals(ConsoleColors.YELLOW + "O" + ConsoleColors.RESET)) {
					
					if (board[i+1][j+1].equals(ConsoleColors.YELLOW + "O" + ConsoleColors.RESET)) {
						
						if (board[i+2][j+2].equals(ConsoleColors.YELLOW + "O" + ConsoleColors.RESET)) {
							
							if (board[i+3][j+3].equals(ConsoleColors.YELLOW + "O" + ConsoleColors.RESET)) return 'Y';
						}
					}
				}
			}
		}
		
		return 'N';
	}
	
	
	
	
	public static String[][] playPiece(String[][] board, int column, Player player) {
		
		int temp = 0;
		boolean change = false;
		
		for (int i = 0; i < 6; i++) {
			
			if (board[i][column].equals("_")) {
				temp = i;
				change = false;
			}
			else {
				if (i == 0) {
					
					System.out.println("This colum is full. Please choose another column.");
					temp = 100;
					break;
				}
				String piece = player.playPiece(player);
				board[i-1][column] = piece;
				change = true;
				
				break;
			}
		}
		
		if (temp == 100) {

			columnFull = true;
			return board;
		}
		
		if (change == false) {
			
			String piece = player.playPiece(player);
			board[temp][column] = piece;
		}
		
		return board;
	}
	
	
	
	public static void printBoard(String[][] board) {
		
		System.out.println();
		for (int i = 0; i < 7; i++) {
					
			System.out.print(i+1 + " "); 
		}
		System.out.println();
				
		for (int i = 0; i < 6; i++) {
					
			for (int j = 0; j < 7; j++) {
						
				System.out.print(board[i][j] + " ");
			} 
			System.out.println();
		}
		System.out.println();
	}
	
	
	
	public static void checkIfFull(String[][] board) {
		
		for (int i = 0; i < 7; i++) {
			
			if (board[0][i] == "_");
			else {
				
				System.out.println();
				System.out.println("The whole board is full. The game is over.");
				System.exit(0);
			}
		}
	}
	
}
























































