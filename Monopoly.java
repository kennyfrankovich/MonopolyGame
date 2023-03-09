import java.util.*;

public class Monopoly {
	
	// The Holy Grail
	
	//-----------------------------------------------------------------
	// 
	public static HashMap<Integer, Property> board = new HashMap<>();
	public static HashMap<Integer, Player> playerOrder = new HashMap<>();
	public static ArrayList<Property> properties = new ArrayList<Property>();
	//
	//-----------------------------------------------------------------	
	
	public static int round = 1;
	
	public static ArrayList<Property> browns = new ArrayList<Property>();
	public static ArrayList<Property> blues = new ArrayList<Property>();
	public static ArrayList<Property> pinks = new ArrayList<Property>();
	public static ArrayList<Property> oranges = new ArrayList<Property>();
	public static ArrayList<Property> reds = new ArrayList<Property>();
	public static ArrayList<Property> yellows = new ArrayList<Property>();
	public static ArrayList<Property> greens = new ArrayList<Property>();
	public static ArrayList<Property> darkBlues = new ArrayList<Property>();
	public static ArrayList<Property> railroads = new ArrayList<Property>();
	public static ArrayList<Property> utilities = new ArrayList<Property>();
	
	public static ArrayList<ArrayList<Property>> sets = new ArrayList<ArrayList<Property>>();
	
	public static ChanceDeck chanceDeck = new ChanceDeck();
	public static CommunityChestDeck communityChestDeck = new CommunityChestDeck();
	
	public static Player temp = new Player("Temp", 0);
	
	//-----------------------------------------------------------------------------
	
	public static void startGame() {
	
		sets.add(browns);
		sets.add(blues);
		sets.add(pinks);
		sets.add(oranges);
		sets.add(reds);
		sets.add(yellows);
		sets.add(greens);
		sets.add(darkBlues);
		sets.add(railroads);
		sets.add(utilities);
		
		// TITLE CODE!!
		System.out.println("Welcome to " + ConsoleColors.RED_BOLD + "M" + ConsoleColors.YELLOW_BOLD + "O" + ConsoleColors.GREEN_BOLD + "N" + ConsoleColors.BLUE_BOLD + "O" + ConsoleColors.PURPLE_BOLD + "P" + ConsoleColors.RED_BOLD + "O"+ ConsoleColors.YELLOW_BOLD + "L" + ConsoleColors.GREEN_BOLD + "Y" + ConsoleColors.RESET + "!!!");
		System.out.println();
		
		//sleep(1);
		
		runMonopoly();
	}
	
	//-----------------------------------------------------------------------------
	
	public static void runMonopoly() {
		
		Scanner scan = new Scanner(System.in);
		
		//Initialize all of the properties
		
		//Initialize Street Properties
		
		//Property(String name, int price, int rent, int house1, int house2, int house3, int house4, int hotel, int housePrice)
		
		Property Brown1 = new Property("Mediterranean Avenue", 60, 2, 10, 30, 90, 160, 250, 50, 33, "Brown");
		Property Brown2 = new Property("Baltic Avenue", 60, 4, 20, 60, 180, 320, 450, 50, 33, "Brown");
		
		browns.add(Brown1);
		browns.add(Brown2);
		properties.add(Brown1);
		properties.add(Brown2);
		
		
		Property Blue1 = new Property("Oriental Avenue", 100, 6, 30, 90, 270, 400, 550, 50, 55, "Blue");
		Property Blue2 = new Property("Vermont Avenue", 100, 6, 30, 90, 270, 400, 550, 50, 55, "Blue");
		Property Blue3 = new Property("Connecticut Avenue", 120, 8, 40, 100, 300, 450, 600, 50, 66, "Blue");
		
		blues.add(Blue1);
		blues.add(Blue2);
		blues.add(Blue3);
		properties.add(Blue1);
		properties.add(Blue2);
		properties.add(Blue3);
		
		
		Property Pink1 = new Property("St. Charles Place", 140, 10, 50, 150, 450, 625, 750, 100, 77, "Pink");
		Property Pink2 = new Property("States Avenue", 140, 10, 50, 150, 450, 625, 750, 100, 77, "Pink");
		Property Pink3 = new Property("Virginia Avenue", 160, 12, 60, 180, 500, 700, 900, 100, 88, "Pink");
		
		pinks.add(Pink1);
		pinks.add(Pink2);
		pinks.add(Pink3);
		properties.add(Pink1);
		properties.add(Pink2);
		properties.add(Pink3);
		
		
		Property Orange1 = new Property("St. James Place", 180, 14, 70, 200, 550, 750, 950, 100, 99, "Orange");
		Property Orange2 = new Property("Tennessee Avenue", 180, 14, 70, 200, 550, 750, 950, 100, 99, "Orange");
		Property Orange3 = new Property("New York Avenue", 200, 16, 80, 220, 600, 800, 1000, 100, 110, "Orange");
		
		oranges.add(Orange1);
		oranges.add(Orange2);
		oranges.add(Orange3);
		properties.add(Orange1);
		properties.add(Orange2);
		properties.add(Orange3);
		
		
		Property Red1 = new Property("Kentucky Avenue", 220, 18, 90, 250, 700, 875, 1050, 150, 121, "Red");
		Property Red2 = new Property("Indiana Avenue", 220, 18, 90, 250, 700, 875, 1050, 150, 121, "Red");
		Property Red3 = new Property("Illinois Avenue", 240, 20, 100, 300, 750, 925, 1100, 150, 132, "Red");
		
		reds.add(Red1);
		reds.add(Red2);
		reds.add(Red3);
		properties.add(Red1);
		properties.add(Red2);
		properties.add(Red3);
		
		
		Property Yellow1 = new Property("Atlantic Avenue", 260, 22, 110, 330, 800, 975, 1150, 150, 143, "Yellow");
		Property Yellow2 = new Property("Ventnor Avenue", 260, 22, 110, 330, 800, 975, 1150, 150, 143, "Yellow");
		Property Yellow3 = new Property("Marvin Gardens", 280, 24, 120, 360, 850, 1025, 1200, 150, 154, "Yellow");
		
		yellows.add(Yellow1);
		yellows.add(Yellow2);
		yellows.add(Yellow3);
		properties.add(Yellow1);
		properties.add(Yellow2);
		properties.add(Yellow3);
		
		
		Property Green1 = new Property("Pacific Avenue", 300, 26, 130, 390, 900, 1100, 1275, 200, 165, "Green");
		Property Green2 = new Property("North Caroline Avenue", 300, 26, 130, 390, 900, 1100, 1275, 200, 165, "Green");
		Property Green3 = new Property("Pennsylvania Avenue", 320, 38, 150, 450, 1000, 1200, 1400, 200, 176, "Green");
		
		greens.add(Green1);
		greens.add(Green2);
		greens.add(Green3);
		properties.add(Green1);
		properties.add(Green2);
		properties.add(Green3);
		
		
		Property DarkBlue1 = new Property("Park Place", 350, 35, 175, 500, 1100, 1300, 1500, 200, 193, "Dark Blue");
		Property DarkBlue2 = new Property("Broadway", 400, 50, 200, 600, 1400, 1700, 2000, 200, 220, "Dark Blue");
		
		darkBlues.add(DarkBlue1);
		darkBlues.add(DarkBlue2);
		properties.add(DarkBlue1);
		properties.add(DarkBlue2);

		//Initialize Railroads
		
		//Property(String name, int mortgage)
		
		Property Railroad1 = new Property("Reading Railroad", 100);
		Property Railroad2 = new Property("Pennsylvania Railroad", 100);
		Property Railroad3 = new Property("B&O Railroad", 100);
		Property Railroad4 = new Property("Short Line", 100);
		railroads.add(Railroad1);
		railroads.add(Railroad2);
		railroads.add(Railroad3);
		railroads.add(Railroad4);
		properties.add(Railroad1);
		properties.add(Railroad2);
		properties.add(Railroad3);
		properties.add(Railroad4);
		
		//Initialize Utilities
		
		//Property(String name) 
		
		Property Utility1 = new Property("Electric Company");
		Property Utility2 = new Property("Water Works");
		utilities.add(Utility1);
		utilities.add(Utility2);
		properties.add(Utility1);
		properties.add(Utility2);
		
		
		//Initialize Community Chest / Chance Cards
		
		//Property(String name, int nothing, int nothing1)
		
		Property CommunityChest1 = new Property("Community Chest", 0, 0);
		Property CommunityChest2 = new Property("Community Chest", 0, 0);
		Property CommunityChest3 = new Property("Community Chest", 0, 0);
		
		Property Chance1 = new Property("Chance", 0, 0);
		Property Chance2 = new Property("Chance", 0, 0);
		Property Chance3 = new Property("Chance", 0, 0);
		
		//Initialize Taxes
		//Property(String name, int tax, int nothing, int nothing1)
		
		Property Tax1 = new Property("Income Tax", 10, 0, 0);
		Property Tax2 = new Property("Luxury Tax", 75, 0, 0);

		//Initialize Corners
		//Property(String name, int nothing, int nothing1, int nothing2, int nothing3)
		
		Property GO = new Property("GO!", 0, 0, 0, 0);
		Property Jail = new Property("Jail", 0, 0, 0, 0);
		Property FreeParking = new Property("Free Parking", 0, 0, 0, 0);
		Property GoToJail = new Property("Go To Jail!", 0, 0, 0, 0);
		
		//Place all spaces into board hashmap
		
		board.put(0, GO);
		board.put(1, Brown1);
		board.put(2, CommunityChest1);
		board.put(3, Brown2);
		board.put(4, Tax1);
		board.put(5, Railroad1);
		board.put(6, Blue1);
		board.put(7, Chance1);
		board.put(8, Blue2);
		board.put(9, Blue3);
		board.put(10, Jail);
		board.put(11, Pink1);
		board.put(12, Utility1);
		board.put(13, Pink2);
		board.put(14, Pink3);
		board.put(15, Railroad2);
		board.put(16, Orange1);
		board.put(17, CommunityChest2);
		board.put(18, Orange2);
		board.put(19, Orange3);
		board.put(20, FreeParking);
		board.put(21, Red1);
		board.put(22, Chance2);
		board.put(23, Red2);
		board.put(24, Red3);
		board.put(25, Railroad3);
		board.put(26, Yellow1);
		board.put(27, Yellow2);
		board.put(28, Utility2);
		board.put(29, Yellow3);
		board.put(30, GoToJail);
		board.put(31, Green1);
		board.put(32, Green2);
		board.put(33, CommunityChest3);
		board.put(34, Green3);
		board.put(35, Railroad4);
		board.put(36, Chance3);
		board.put(37, DarkBlue1);
		board.put(38, Tax2);
		board.put(39, DarkBlue2);
		
		//Prints out gameboard and positions of each space, not needed anymore bc I know it works
		
		/*
		{
			Collection<Object> values = board.values();
			int position = 0;
			
			for (Object space : values) {
			
				System.out.println("The space at position " + position + " is taken by " + space.toString());
			}
		}
		*/
		
		
		
		//------------------------------------------------------------------
		
		//With all the properties initialized, now we start working on the players
		
		Player AIOne = new Player(ConsoleColors.RED_BOLD_BRIGHT + "AI One" + ConsoleColors.RESET);
		Player AITwo = new Player(ConsoleColors.YELLOW_BOLD_BRIGHT + "AI Two" + ConsoleColors.RESET);
		Player AIThree = new Player(ConsoleColors.GREEN_BOLD_BRIGHT + "AI Three" + ConsoleColors.RESET);
		Player AIFour = new Player(ConsoleColors.CYAN_BOLD_BRIGHT + "AI Four" + ConsoleColors.RESET);
		
		//This section can be improved to change how many AI you would like to run
		
		/*
		ArrayList<Player> playersList = new ArrayList<>();

		playersList.add(0, AIOne);
		playersList.add(1, AITwo);
		playersList.add(2, AIThree);
		playersList.add(3, AIFour);
		*/
		
		
		//------------------------------------------------------------------

		//This is where the game starts to run
		
		chooseStartOrder(AIOne, AITwo, AIThree, AIFour);
		System.out.println();
		
		//This is where the game is continually run
		
		
		System.out.println("-----------------------------------");
		System.out.println(); 
		
		while (playerOrder.size() != 1) {
			
			System.out.println("Round " + round + ":");
			System.out.println();
			System.out.println("-----------------------------------");
			System.out.println(); 
		
			//sleep(2);
			
			//Make a full round run
			
			
			for (int turn = 1; turn < playerOrder.size()+1; turn++) {
				
				System.out.println("It is now " + playerOrder.get(turn).getName() + "'s turn.");
				System.out.println();
				
				
				Player player =	playerOrder.get(turn);
			
				
				player.playRound(turn, player);
				
				if (Player.lostThisRound == false) {
					
					System.out.println();
					System.out.println(playerOrder.get(turn).getName() + "'s turn is now over.");
					
					System.out.println();
					System.out.println("-----------------------------------");
					System.out.println();
				}
				else {
					
					System.out.println();
					System.out.println("-----------------------------------");
					System.out.println();
					
					Player.lostThisRound = false;
					break;
				}
				
				
				
				//sleep(1);
			}
			
			
			
			round++;
		}
		
		Player winner = playerOrder.get(1);
		
		System.out.println("Game over! " + winner.getName() + " won the game with " + ConsoleColors.GREEN_BOLD + "$" + winner.getCash() + ConsoleColors.RESET + " dollars on round " + round + ".");
		
		
		
		
		scan.close();
	}
	
	
	
	//-----------------------------------------------------------------------------
	
	
	//Util methods
	
	public static int diceRoll() {
		
		//ADD IN SUPPORT FOR DOUBLES  
		
		Random random = new Random();
		
		return random.nextInt(11)+2;
	}
	
	
	public static void chooseStartOrder(Player AIOne, Player AITwo, Player AIThree, Player AIFour) {
		
		System.out.println();
		
		int diceRoll1 = diceRoll();
		int diceRoll2 = diceRoll();
		int diceRoll3 = diceRoll();
		int diceRoll4 = diceRoll();
		
		int order = 1;
		int[] diceRolls = {diceRoll1, diceRoll2, diceRoll3, diceRoll4};
		int max;
		int temp = 4;
		
		
		for (int j = 0; j < 4; j++) {
			
			max = Integer.MIN_VALUE;
			
			for (int i = 0; i < diceRolls.length; i++) {

				if (diceRolls[i] > max) {
				
					max = diceRolls[i];
					temp = i;
				}
			}
		
			if (temp == 0) {
			
				System.out.println(AIOne.getName() + " was put in spot " + order);
				playerOrder.put(order, AIOne);
			}
			
			else if (temp == 1) {
				
				System.out.println(AITwo.getName() + " was put in spot " + order);
				playerOrder.put(order, AITwo);
			}
			
			else if (temp == 2) { 
				
				System.out.println(AIThree.getName() + " was put in spot " + order);
				playerOrder.put(order, AIThree);
			}
			
			else if (temp == 3) {
				
				System.out.println(AIFour.getName() + " was put in spot " + order);
				playerOrder.put(order, AIFour);
			}
			
			diceRolls[temp] = 0;

			order++;
		}
		
		
	}
	
	
	public static void sleep(long time) {
		
		try {
			
			Thread.sleep(time*1000);
					
		} catch (InterruptedException e) {
					
			e.printStackTrace();
		}
	}
	
}
