import java.util.*;

@SuppressWarnings("static-access")
public class TicTacToe {

	enum Player {

		X {
			
			public String toString() {
				
				return "X";
			}
		},
		
		O {
			
			public String toString() {
				
				return "O";
			}
		}
	}
	
	public static void startGame() throws InterruptedException {
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Tic Tac Toe:");
		System.out.println();
		System.out.println("Type 1 to play with two players.");
		System.out.println("Type 2 to play against a randomly placing AI."); 
		//System.out.println("Type 3 to play against an active AI. (Not ready yet)");
		
		int choice = scan.nextInt();
		if (choice == 1) {
			
			ticTacToeTwoPlayer();
			
		}
		else if (choice == 2) {
			
			ticTacToeRandomAI();
		}
		
		scan.close();
	}
	
	//----------------------------------------------------------
	
	public static void ticTacToeRandomAI() throws InterruptedException {
		
		Scanner scan = new Scanner(System.in);
		char[][] board = new char[3][3];
		Player player = Player.X;
	
		System.out.println();
		
		for (int i = 0; i < 3; i++) {
			
			for (int j = 0; j < 3; j++) {
				
				board[i][j] = '_';
			}
		}
		
		for (int i = 0; i<3; i++) {
			
			for (int j = 0; j<3; j++) {
				
				System.out.print(board[i][j] + " ");
			}	
			System.out.println();
		}
		System.out.println();
		
		while (checkForWin(board) == 'N') {
			
			board = runRandomAI(board, player, scan);
		}
		
		if (checkForWin(board) == 'X') {
			
			System.out.println("You beat the AI! Congratulations!");
		}
		else if (checkForWin(board) == 'O') {
			
			System.out.println("You suck, how did you let the computer beat you?");
		}
		else if (checkForWin(board) == 'C') {
			
			System.out.println("Cat's game!");
			
		}
		
		scan.close();
	}
	
	
	public static char[][] runRandomAI(char[][] board, Player player, Scanner scan) throws InterruptedException {
		
		int x = 3;
		int y = 3;
		
		System.out.println();
		System.out.println("Where do you want to play your piece? (Values between 1 - 3)");
		
		while (x > 2) {
			
			x = scan.nextInt()-1;
			if (x > 2) {
			
				System.out.println("That is out of range silly!");		
			}
		}
		while (y > 2) {
			
			y = scan.nextInt()-1;
			if (y > 2) {
			
				System.out.println("That is out of range silly!");
			}
		}
		
		System.out.println();
		
		//PLAY THE PIECES
		
		board = playPiece(board, x, y, player);
		System.out.println("You played your piece.");
		System.out.println();
		
		if (checkForWin(board) == 'N');
		else {
			
			for (int i = 0; i<3; i++) {
				
				for (int j = 0; j<3; j++) {
					
					System.out.print(board[i][j] + " ");
				}	
				System.out.println();
			}
			System.out.println();
			
			return board;
		}
		
		for (int i = 0; i<3; i++) {
			
			for (int j = 0; j<3; j++) {
				
				System.out.print(board[i][j] + " ");
			}	
			System.out.println();
		}
		System.out.println();
		
		
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
		
		board = randomAI(board);
		
		//PRINT OUT BOARD 
	
		for (int i = 0; i<3; i++) {
			
			for (int j = 0; j<3; j++) {
				
				System.out.print(board[i][j] + " ");
			}	
			System.out.println();
		}
		System.out.println();
		
		return board;
	}

	public static void ticTacToeTwoPlayer() {
		
		Scanner scan = new Scanner(System.in);
		char[][] board = new char[3][3];
		Player player = Player.X;
		
		System.out.println();
		
		for (int i = 0; i < 3; i++) {
			
			for (int j = 0; j < 3; j++) {
				
				board[i][j] = '_';
			}
		}
		
		
		
		for (int i = 0; i < 3; i++) {
			
			for (int j = 0; j<3; j++) {
				
				System.out.print(board[i][j] + " ");
			}	
		
			System.out.println();
		}
		
		System.out.println();
		
		while (checkForWin(board) == 'N') {
			
			board = run2Player(board, player, scan);
			
			if (player == player.X) player = player.O;
			else if (player == player.O) player = player.X;
		}
		
		scan.close();
	}
	
	
	
	
	
	public static char[][] run2Player(char[][] board, Player player, Scanner scan) {
		
		int x = 3;
		int y = 3;
		
		System.out.println();
		System.out.println("Where do you want to play your piece? (Values between 1 - 3)");
		
		//FIX THIS TO MAKE IT NOT BREAK
		//int lol = scan.nextInt();
		//System.out.println(lol);
		while (x > 2) {
			
			x = scan.nextInt()-1;
			if (x > 2) {
			
				System.out.println("That is out of range silly!");		
			}
		}
		
		while (y > 2) {
			
			y = scan.nextInt()-1;
			if (y > 2) {
			
				System.out.println("That is out of range silly!");
			}
		}
		
		System.out.println();
		
		board = playPiece(board, x, y, player);
		System.out.println("Player " + player.toString() + " played their piece.");
		System.out.println();
		
		for (int i = 0; i<3; i++) {
			
			for (int j = 0; j<3; j++) {
				
				System.out.print(board[i][j] + " ");
			}	
			System.out.println();
		}
		System.out.println();
	
		if (checkForWin(board) == 'N');
		else if (checkForWin(board) == 'C') System.out.println("Cat's game!");
		else {
			
			System.out.println("Congratulations! Player " + player.toString() + " won the game!");
			
		}
		
		return board;		
	}
	
	
	

	public static char checkForWin(char[][] board) {
		
		int xxcount = 0, yxcount = 0, zxcount = 0, axcount = 0, bxcount = 0, cxcount = 0;
		int xocount = 0, yocount = 0, zocount = 0, aocount = 0, bocount = 0, cocount = 0;
		
		for (int i = 0; i < 3; i++) {
			
			// XCOUNT CHECKER
			
			if (board[i][0] == 'X') {
				
				xxcount++;
			}
			
			if (board[i][1] == 'X') {
				
				yxcount++;
			}
			
			if (board[i][2] == 'X') {
				
				zxcount++;
			}
			
			if (board[0][i] == 'X') { 
				
				axcount++;
			}
			
			if (board[1][i] == 'X') {
				
				bxcount++;
			}
			
			if (board[2][i] == 'X') {
				
				cxcount++;
			}
			
			// OCOUNT CHECKER
			
			if (board[i][0] == 'O') {
				
				xocount++;
			}
			
			if (board[i][1] == 'O') {
				
				yocount++;
			}
			
			if (board[i][2] == 'O') {
				
				zocount++;
			}
			
			if (board[0][i] == 'O') { 
				
				aocount++;
			}
			
			if (board[1][i] == 'O') {
				
				bocount++;
			}
			
			if (board[2][i] == 'O') {
				
				cocount++;
			}
			
			
			if (xxcount == 3 || yxcount == 3 || zxcount == 3 || axcount == 3 || bxcount == 3 || cxcount == 3) {
				
				break;
			}
			
			if (xocount == 3 || yocount == 3 || zocount == 3 || aocount == 3 || bocount == 3 || cocount == 3) {
				
				break;
			}
		}
		
		if (xxcount == 3 || yxcount == 3 || zxcount == 3 || axcount == 3 || bxcount == 3 || cxcount == 3) {
			
			return 'X';
		}
		
		if (xocount == 3 || yocount == 3 || zocount == 3 || aocount == 3 || bocount == 3 || cocount == 3) {
			
			return 'O';
		}
		
		if (board[0][0] == 'X' && board[1][1] == 'X' && board[2][2] == 'X') {
			
			return 'X';
		}
		
		if (board[0][2] == 'X' && board[1][1] == 'X' && board[2][0] == 'X') {
			
			return 'X';
		}
		
		if (board[0][0] == 'O' && board[1][1] == 'O' && board[2][2] == 'O') {
			
			return 'O';
		}
		
		if (board[0][2] == 'O' && board[1][1] == 'O' && board[2][0] == 'O') {
			
			return 'O';
		}
		
		int catChecker = 0;
		
		for (int i = 0; i < 3; i++) {
			
			for (int j = 0; j < 3; j++) {
				
				if (board[i][j] == '_') catChecker++;
			}	
		}
		
		if (catChecker == 0) return 'C';
		
		return 'N';
	}
	
	public static char[][] randomAI(char[][] board){
		
		int loop = 0;
		int num = 5, num2 = 5;
		
		while (loop == 0) {
			
			Random random = new Random();
			
			num = random.nextInt(3);
			num2 = random.nextInt(3);
		
			if (board[num][num2] == 'X' || board[num][num2] == 'O');
			else { 
				
				board[num][num2] = 'O';
				break;
			}
			
		}
		
		return board;
	}
	
	public static char[][] playPiece(char[][] board, int x, int y, Player player) {
		
		boolean check = false;
		
		while (check == false) {
			
			if (board[x][y] == '_') {
			
				if (player == player.X) {
					
					check = true;
					board[x][y] = 'X';
				}
				else if (player == player.O) {
					
					check = true;
					board[x][y] = 'O';
				}
			}
		
			else if (board[x][y] == 'X') {
				
				System.out.println("This place has already been taken by an X!");
			}
		
			else if (board[x][y] == 'O') {
			
				System.out.println("This place has already been taken by an O!");
			}
		
		}
		return board;
	}
}


