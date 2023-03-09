import java.util.*;

public class Player {

	public static boolean lostThisRound = false;
	
	private String name;
	private int cash;
	private int position;
	private ArrayList<Property> properties;
	private int getOutOfJailCards;
	private boolean inJail;
	
	public boolean ownBrowns;
	public boolean ownBlues;
	public boolean ownPinks;
	public boolean ownOranges;
	public boolean ownReds;
	public boolean ownYellows;
	public boolean ownGreens;
	public boolean ownDarkBlues;
	public int ownRailroads;
	public boolean ownUtilities;
	
	public ArrayList<Property> ownedBrowns = new ArrayList<Property>();
	public ArrayList<Property> ownedBlues = new ArrayList<Property>();
	public ArrayList<Property> ownedPinks = new ArrayList<Property>();
	public ArrayList<Property> ownedOranges = new ArrayList<Property>();
	public ArrayList<Property> ownedReds = new ArrayList<Property>();
	public ArrayList<Property> ownedYellows = new ArrayList<Property>();
	public ArrayList<Property> ownedGreens = new ArrayList<Property>();
	public ArrayList<Property> ownedDarkBlues = new ArrayList<Property>();
	public ArrayList<Property> ownedRailroads = new ArrayList<Property>();
	public ArrayList<Property> ownedUtilities = new ArrayList<Property>();
	
	public ArrayList<ArrayList<Property>> ownedSets = new ArrayList<ArrayList<Property>>();	
	
	//-----------------------------------------------------------------------------

	//Constructor 
	
	public Player(String name) {
		
		this.name = name;
		cash = 1500;
		position = 0;
		properties = new ArrayList<Property>();
		getOutOfJailCards = 0;
		inJail = false;
		
		ownedSets.add(ownedBrowns);
		ownedSets.add(ownedBlues);
		ownedSets.add(ownedPinks);
		ownedSets.add(ownedOranges);
		ownedSets.add(ownedReds);
		ownedSets.add(ownedYellows);
		ownedSets.add(ownedGreens);
		ownedSets.add(ownedDarkBlues);
		ownedSets.add(ownedRailroads);
		ownedSets.add(ownedUtilities);
		
		ownBrowns = false;
		ownBlues = false;
		ownPinks = false;
		ownOranges = false;
		ownReds = false;
		ownYellows = false;
		ownGreens = false;
		ownDarkBlues = false;
		ownRailroads = 0;
		ownUtilities = false;

		//System.out.println("The " + this.name + " player has been initialized.");
	}
	
	public Player(String name, int nothing) {
		
		
	}
	
	//-----------------------------------------------------------------------------
	
	//Methods to get values
	
	public String getName() {
		
		return name;
	}
	
	public int getPosition() {
		
		return position;
	}
	
	public int getCash() {
		
		return cash;
	}
		
	public ArrayList<Property> getProperties(){
		
		return properties;
	}
	
	public void printAllProperties() {
		
		//boolean start = true; // part of other commented stuff right below
		
		for (Property property : properties) {
			
			System.out.println(property.getName());
			
			//Print out with commas in one line
			
			/*
			if (start == true) {
				System.out.print(property.getName());
				start = false;
			}
			else {
				System.out.print(", " + property.getName());
			}
			*/
		}
		System.out.println();
		System.out.println();
	}
	
	//-----------------------------------------------------------------------------
	
	//Methods to set values
	
	public void sendToJailPosition() {
		
		position = 10;
	}
	
	public void setPosition(int position) { // Used for cards
		
		if (position < this.position) {
			
			cash += 200;
			System.out.println();
			System.out.println(name + " passsed GO! and collected " + ConsoleColors.GREEN_BOLD + "$200" + ConsoleColors.RESET);
		}
		
		
		this.position = position;
	}
	
	public void setPositionBackwards(int amountBackwards) {
		
		position = position - amountBackwards;
	}
	
	public void setNextPosition(int diceRoll) { //For everything but jail
		
		if (position + diceRoll < 39) {
			
			this.position = position + diceRoll;
		}
		else {
			
			cash += 200;
			System.out.println(name + " passsed GO! and collected " + ConsoleColors.GREEN_BOLD + "$200" + ConsoleColors.RESET);
			System.out.println();
			int spot = position + diceRoll - 39;
			this.position = spot;
		}
	}
	
	public void setNameWithOrder(String order) {
		
		this.name = name + order;
	}
	
	public void setInJail(boolean inJail) {
		
		this.inJail = inJail;
	}
	
	
	
	public void addProperty(Property property) {
		
		if (!properties.contains(property)) {
			
			properties.add(property);
		} 
		else {
			
			System.out.println("Duplicate property - has not been added");
			return;
		}
		
		
		//Find the correct owned set in ownedSets
		int setIndex = 0;
		boolean found = false;
		
		for (ArrayList<Property> set : Monopoly.sets) {
			
			try {
				Property tempProperty = set.get(0);
				String tempSetType = tempProperty.getSetType();
				String propertySetType = property.getSetType();
				
				if (tempSetType.equals(propertySetType)) {
						
					found = true;
					break;
				}
				
				setIndex++;
			} catch (Exception e) {
				
				System.out.println("set had nothing !!!!!!!!!!!!!!!!!!!");
			}
		}
		
		//Add the property to the right set
		ArrayList<Property> foundSet = ownedSets.get(setIndex);
		foundSet.add(property);
		
	}
	
	

	public void removeCash(int amount) {
		
		this.cash = cash - amount;
	}
	
	
	public void addCash(int amount) {
		
		this.cash = cash + amount;
	}
	
	
	
	
	public int useGetOutOfJailCard() {
		
		if (getOutOfJailCards >= 1) {
			
			getOutOfJailCards--;
			return getOutOfJailCards+1;
		}
		else return getOutOfJailCards;
	}
	
	
	//-----------------------------------------------------------------------------
	
	//Play Game methods
	
	public void playRound(int turn, Player player) {
		
		
		if (inJail == true) {
			
			inJailDecisionHandler();
			return;
		}
		//Get dice roll for our position this round
		int diceRoll = Monopoly.diceRoll();
		System.out.println(name + " rolled a " + diceRoll);
		System.out.println();

		//Move our player
		setNextPosition(diceRoll);
		printCash();
		System.out.println();
		
		//Get name of space we are on and print it out
		Property property = Monopoly.board.get(position);
		System.out.println(name + " is now on space " + position + ", " + property.getName());

		//Get type of property
		String type = property.getType();
		
		//Time to make the decisions
		
		if (type.equals("Street")) {
			
			streetPropertyDecisionHandler(property, player, turn);
		}
		
		else if (type.equals("Railroad")) {
			
			railroadPropertyDecisionHandler(property, player, turn);
		}
		
		else if (type.equals("Utility")) {
			
			utilityPropertyDecisionHandler(property, player, turn);
		}
		
		else if (type.equals("Card")) {
			
			cardPropertyDecisionHandler(property, player, turn);
		}
		
		else if (type.equals("Tax")) { 
			
			taxPropertyDecisionHandler(property, turn);
		}
		
		else if (type.equals("Corner")) {
			
			cornerDecisionHandler(property);
		}
		
		if (lostThisRound) return;
		
		//Check for extra properties
		checkProperties();
		
		//Check if own sets
		checkForSets();		

		//try to trade with players for sets
		trade();
		
		//check to see if you made any new sets
		checkForSets();
		
		// buy houses if applicable
		buyHouses();
		
		//print all properties
		printProperties();
		
		
		
		
		
		
		
		
		
	}
	
	//-----------------------------------------------------------------------------

	//Decision Handlers
	
	
	//Handles Street Properties
	
	public void streetPropertyDecisionHandler(Property property, Player player, int turn) {
		
		int price = property.getPrice();
		boolean propertyIsOwned = property.getOwned();
		
		if (propertyIsOwned == true) {
			
			int houses = property.getHouses();
			Player owner = property.getOwner();
			int rent;
			
			
			if (owner.equals(player)) {
				
				return;
			}
			
			//Check how many houses
			
			if (houses == 0) {
				
				rent = property.getRent();
			}
			
			else if (houses == 1) {
				
				rent = property.getHouseOneRent();
			}
			
			else if (houses == 2) {
				
				rent = property.getHouseTwoRent();
			}
			
			else if (houses == 3) {
				
				rent = property.getHouseThreeRent();
			}
			
			else if (houses == 4) {
				
				rent = property.getHouseFourRent();
			}
			
			else {
				
				rent = property.getHotelRent();
			}
			
			//Use rent variable to pay rent or lose
			
			if (rent < cash) {
				
				payRent(owner, rent); //make payrent !!!!				
			}
			
			else {
				
				manageMortgages(rent, owner, turn, "Street", property);
				
			}
			
			return;
		}
		
		
		// AI strategy is to buy everything it lands on if it can !!
		
		else if (price < cash) {
			
			buyProperty(property, player, price);
		}
		
		else;
		
	}
	
	public void railroadPropertyDecisionHandler(Property railroad, Player player, int turn) {
		
		int price = railroad.getPrice();
		boolean propertyIsOwned = railroad.getOwned();
		
		if (propertyIsOwned == true) {
			
			Player owner = railroad.getOwner();
			int set = owner.ownedRailroads.size();
			int rent = 50;
			
			if (owner.equals(player)) {
				
				return;
			}
			
			if (set == 1) {
				
				rent = 50;
			}
			else if (set == 2) {
				
				rent = 100;
			}
			else if (set == 3) {
				
				rent = 150;
			}
			else if (set == 4) {
				
				rent = 200;
			}
			
			//Use rent variable to pay rent or lose
			
			if (rent < cash) {
				
				payRent(owner, rent); 				
			}
			
			else {
				
				manageMortgages(rent, owner, turn, "Railroad", railroad);
			}
			
			return;
		}
		
		
		// AI strategy is to buy everything it lands on if it can !!
		
		else if (price < cash) {
			
			buyProperty(railroad, player, price);
		}
		
		else;
		
		
		
		
	}
	
	
		
	public void utilityPropertyDecisionHandler(Property utility, Player player, int turn) {
	
		int price = utility.getPrice();
		boolean propertyIsOwned = utility.getOwned();
		int rent = 0;
		
		if (propertyIsOwned == true) {
			
			Player owner = utility.getOwner();
			
			if (owner.equals(player)) {
				
				return;
			}
		
			if (owner.ownUtilities == false) {
					
				rent = Monopoly.diceRoll() * 4;
				
				if (rent < cash) {
					
					payRent(owner, rent);
				}
				else {
					
					manageMortgages(rent, owner, turn, "Utility", utility);
				}
			}
			
			else {
					
				rent = Monopoly.diceRoll() * 10;
				
				if (rent < cash) {
					
					payRent(owner, rent);
				}
				else {
					
					manageMortgages(rent, owner, turn, "Utility", utility);
				}
			}	
		}
		
		
		else if (price < cash) {
			
			buyProperty(utility, player, price);
		}
		
	}
	
	
	public void cardPropertyDecisionHandler(Property space, Player realPlayer, int turn) {
		
		//fuck this shit
		
		String name = space.getName();
		
		if (name.equals("Community Chest")) {
			
			CommunityChestDeck deck = Monopoly.communityChestDeck;
			Card card = deck.drawCard();
			Card.CardType realType = card.getType();
			String type = realType.toString();
			
			System.out.println();
			System.out.println(this.name + " drew: " + card.getText());
			
			if (type.equals("ADVANCE_TO_GO")) {
				
				setPosition(0);
			}
			
			else if (type.equals("BANK_ERROR")) {
				
				cash += 200;
				System.out.println();
				printCash();
			}
			
			else if (type.equals("DOCTOR_FEES")) {
				
				if (cash > 50) {
					
					cash -= 50;
				}
				else {
					
					cardManageMortgages(50, turn);
				}
				
				System.out.println();
				printCash();
			}
			
			else if (type.equals("SALE_OF_STOCK")) {
				
				cash += 50;
				System.out.println();
				printCash();
			}
			
			else if (type.equals("GET_OUT_OF_JAIL_FREE")) {
				
				getOutOfJailCards++;
			}
			
			else if (type.equals("GO_TO_JAIL")) {
				
				sendToJailPosition();
				inJail = true;
			}
			
			else if (type.equals("GRAND_OPERA_NIGHT")) {
				
				for (int i = 0; i <= Monopoly.playerOrder.size(); i++) {

					
					// make this later
					// collect 50 from all players
				}
			}
			
			else if (type.equals("HOLIDAY_FUND_MATURES")) {
				
				cash += 100;
				System.out.println();
				printCash();
			}
			
			else if (type.equals("INCOME_TAX_REFUND")) {
				
				cash += 20;
				System.out.println();
				printCash();
			}
			
			else if (type.equals("BIRTHDAY")) {
				
				for (int i = 1; i <= Monopoly.playerOrder.size(); i++) {
					
					Player player = Monopoly.playerOrder.get(i);
					
					//collect 10 from all players
					
				}
			}
			
			else if (type.equals("LIFE_INSURANCE_MATURES")) {
				
				cash += 100;
				System.out.println();
				printCash();
			}
			
			else if (type.equals("HOSPITAL_FEES")) {
				
				if (cash > 100) {
					
					cash -= 100;
				}
				else { 
					
					cardManageMortgages(100, turn);
				}
				
				System.out.println();
				printCash();
			}
			
			else if (type.equals("SCHOOL_FEES")) {
				
				if (cash > 50) {
					
					cash -= 50;
				}
				else { 
					
					cardManageMortgages(50, turn);
				}
				
				System.out.println();
				printCash();
			}
			
			else if (type.equals("CONSULTANCY_FEE")) {
				
				if (cash > 25) {
					
					cash -= 25;
				}
				else {
					
					cardManageMortgages(25, turn);
				}
				
				System.out.println();
				printCash();
			}
			
			else if (type.equals("STREET_REPAIRS")) {
				
				int totalHouses = 0;
				
				for (Property property : properties) {
					
					totalHouses += property.getHouses();
				}
				
				int amount = totalHouses * 40;
				
				if (cash > amount) {
					
					cash -= amount;
				}
				
				else {
					
					cardManageMortgages(amount, turn);
				}
				
				System.out.println();
				printCash();
			}
			
			else if (type.equals("BEAUTY_CONTEST")) {
				
				cash += 10;
				System.out.println();
				printCash();
			}
			
			else if (type.equals("INHERITANCE")) {
				
				cash += 100;
				System.out.println();
				printCash();
			}
			
			
			deck.returnCard(card);
		}
		
		
		//Chance deck manager
		
		else if (name.equals("Chance")) {
			
			ChanceDeck deck = Monopoly.chanceDeck;
			Card card = deck.drawCard();
			Card.CardType realType = card.getType();
			String type = realType.toString();
			
			System.out.println();
			System.out.println(this.name + " drew: " + card.getText());
			
			
			if (type.equals("ADVANCE_TO_GO")) {
			
				setPosition(0);
			}
			
			else if (type.equals("ADVANCE_TO_LOCATION")) {
				
				int location = card.getLocation();
				setPosition(location);
			}
			
			else if (type.equals("ADVANCE_TO_NEAREST_UTILITY")) {
				
				Property property = Monopoly.board.get(position);
				int nextPosition = position;
				
				while (!property.getType().equals("Utility")) {
					
					if (nextPosition == 39) {
						
						nextPosition = 0;
					}
					else {
					
						nextPosition++;
					}
					property = Monopoly.board.get(nextPosition);
				}
				
				setPosition(nextPosition);
				System.out.println();
				System.out.println(this.name + " has been sent to " + property.getName());
				utilityPropertyDecisionHandler(property, realPlayer, turn);
			}
			
			else if (type.equals("ADVANCE_TO_NEAREST_RAILROAD")) {
				
				Property property = Monopoly.board.get(position);
				int nextPosition = position;
				
				while (!property.getType().equals("Railroad")) {
					
					if (nextPosition == 39) {
						
						nextPosition = 0;
					}
					else {
					
						nextPosition++;
					}
					property = Monopoly.board.get(nextPosition);
				}
				
				setPosition(nextPosition);
				System.out.println();
				System.out.println(this.name + " has been sent to " + property.getName());
				railroadPropertyDecisionHandler(property, realPlayer, turn);
			}
			
			else if (type.equals("BANK_PAYS_DIVIDEND")) {
				
				cash += 50;
				System.out.println();
				printCash();
			}
			
			else if (type.equals("GET_OUT_OF_JAIL_FREE")) {
				
				getOutOfJailCards++;
			}
			
			else if (type.equals("GO_BACK_3_SPACES")) {
				
				setPositionBackwards(3);
			}
			
			else if (type.equals("GO_TO_JAIL")) {
				
				sendToJailPosition();
				inJail = true;
			}
			
			else if (type.equals("MAKE_REPAIRS")) {
				
				int totalHouses = 0;
				
				for (Property property : properties) {
					
					totalHouses += property.getHouses();
				}
				
				int amount = totalHouses * 40;
				
				if (cash > amount) {
					
					cash -= amount;
				}
				
				else {
					
					cardManageMortgages(amount, turn);
				}
				
				System.out.println();
				printCash();
			}
			
			else if (type.equals("PAY_POOR_TAX")) {
				
				if (cash > 15) {
					
					cash -= 15;
				}
				else {
					
					cardManageMortgages(15, turn);
				}
				
				System.out.println();
				printCash();
			}
			
			else if (type.equals("TAKE_TRIP")) {
				
				setPosition(5);
			}
			
			else if (type.equals("TAKE_WALK")) {
				
				setPosition(39);
			}
			
			else if (type.equals("ELECTED_CHAIRMAN")) {
				
				int amount = Monopoly.playerOrder.size() * 50;
				
				if (cash > amount) {
					
					for (int i = 1; i <= Monopoly.playerOrder.size(); i++) {
						
						Player player = Monopoly.playerOrder.get(i);
						
						payPlayer(player, 50);
					}
				}
				else {
					
					//cardManageMortgages();
				}
				
				// paying each player 50
				
				
				
				
			}
			
			else if (type.equals("BUILDING_AND_LOAN_MATURES")) {
				
				cash += 150;
				System.out.println();
				printCash();
			}
			
			
			deck.returnCard(card);
		}
		
	}
	
	public void taxPropertyDecisionHandler(Property tax, int turn) {
		
		String name = tax.getName();

		if (name.equals("Income Tax")) {
			
			
			if (cash/10 > 200) {
				
				Property.freeParkingPot += 200;
				cash -= 200;
				System.out.println();
				System.out.println(this.name + " chose to pay " + ConsoleColors.GREEN_BOLD + "$200" + ConsoleColors.RESET + " instead of 10% of their total cash, which is " + ConsoleColors.GREEN_BOLD + "$" + cash + ConsoleColors.RESET + ", to the Free Parking Pot");
			}
			else {
				
				System.out.println();
				System.out.println(this.name + " chose to pay " + ConsoleColors.GREEN_BOLD + "$" + cash/10 + ConsoleColors.RESET + ", 10% of their cash to the Free Parking Pot");
				Property.freeParkingPot += cash/10;
				cash -= cash/10;
			}
			
		}
		else if (name.equals("Luxury Tax")) {
			
			if (cash > 75) {
				
				removeCash(75);
				Property.freeParkingPot += 75;
				System.out.println();
				System.out.println(this.name + " paid " + ConsoleColors.GREEN_BOLD + "$75" + ConsoleColors.RESET + " to the Free Parking Pot");
			}
			else {
				
				//manageMortgages(int rent, Player owner, int turn, String type, Property realProperty)
				Player fakePlayer = new Player("");
				manageMortgages(turn, fakePlayer, turn, "Tax", tax);
			}
		}
	}

	public void cornerDecisionHandler(Property corner) {
		
		String name = corner.getName();
		
		if (name.equals("GO!")); //handled by setPosition()
		
		else if (name.equals("Free Parking")) {
			
			//Collecing Free Parking Pot - house rule
			cash += Property.freeParkingPot;
			System.out.println();
			System.out.println(this.name + " collected the Free Parking Pot, which was " + ConsoleColors.GREEN_BOLD + "$" + Property.freeParkingPot + ConsoleColors.RESET);
			Property.freeParkingPot = 100;
		}
		
		else if (name.equals("Go To Jail!")) {
			
			sendToJailPosition();
			inJail = true;
			System.out.println();
			System.out.println(this.name + " has been sent to Jail.");
		}
		
		else if (name.equals("Jail")) {
			
			System.out.println();
			System.out.println(this.name + " is not in the pen, phew");
		}
	}
	
	
	public void inJailDecisionHandler() {
		
		if (getOutOfJailCards > 0) {
			
			getOutOfJailCards--;
			System.out.println();
			System.out.println(name + " used a Get Out of Jail Free card and is no longer in Jail");
			inJail = false;			
			return;
		}
		
		
		
		System.out.println(name + " is in Jail and is choosing between rolling for their fate or paying to get out");
		System.out.println();
		
		if (cash * 15 >= 50) {
			
			System.out.println(name + " chose to spend " + ConsoleColors.GREEN_BOLD + "$50" + ConsoleColors.RESET + " of his " + ConsoleColors.GREEN_BOLD + "$" + cash + ConsoleColors.RESET + " to get out of Jail this round");
			inJail = false;
		}
		else {
			
			System.out.println(name + " decided to roll dice for his fate in Jail");
			boolean result = jailDiceRollDoubles();
			
			if (result  == true) {
				
				inJail = false;
				System.out.println();
				System.out.println(name + " rolled doubles and got out of Jail!");
			}
			else {
				
				System.out.println();
				System.out.println(name + " failed and didnt roll doubles what a loser");
			}
		}
	}

	//-----------------------------------------------------------------------------

	//Methods to manage assets
	
	public void buyProperty(Property property, Player player, int price) {
		
		//Set values so property is owned
		property.setOwned(true);
		property.setOwner(player);
		addProperty(property);
		
		//Print out AI bought property
		System.out.println();
		System.out.println(name + " bought " + property.getName() + ", costing them " + ConsoleColors.GREEN_BOLD + "$" + price + ConsoleColors.RESET + " dollars");
		
		//Set cash correctly
		removeCash(price);
		System.out.println();
		System.out.println(name + " now has " + ConsoleColors.GREEN_BOLD + "$" + cash + ConsoleColors.RESET + " dollars");
	}
	
	public void buyHouses() {
		
		int tempHouses = 0;
		
		//costs 50 for houses
		if (ownBrowns == true) {
			
			if (ownedBrowns.get(0).getHouses() == 5); // if full of houses skip
			
			else if (cash > 100) {
			
				for (Property property : ownedBrowns) {
					
					property.addHouses(1);
					tempHouses = property.getHouses();
				}
				cash -= 100;
				System.out.println();
				System.out.println(name + " bought houses on the Browns. They now have " + tempHouses + " houses");
			}
		}
		
		
		//costs 50 for houses
		if (ownBlues == true) {
			
			if (ownedBlues.get(0).getHouses() == 5); // if full of houses skip
			
			else if (cash > 150) {
				
				for (Property property : ownedBlues) {
					
					property.addHouses(1);
					tempHouses = property.getHouses();
				}
				cash -= 150;
				System.out.println();
				System.out.println(name + " bought houses on the Blues. They now have " + tempHouses + " houses");
			}
		}
		
		
		//costs 100 for houses
		if (ownPinks == true) {
			
			if (ownedPinks.get(0).getHouses() == 5); // if full of houses skip
			
			if (cash > 300) {
				
				for (Property property : ownedPinks) {
					
					property.addHouses(1);
					tempHouses = property.getHouses();
				}
				cash -= 300;
				System.out.println();
				System.out.println(name + " bought houses on the Pinks. They now have " + tempHouses + " houses");
			}
		}

		
		//costs 100 for houses
		if (ownOranges == true) {
			
			if (ownedOranges.get(0).getHouses() == 5); // if full of houses skip
			
			else if (cash > 300) {
				
				for (Property property : ownedOranges) {
					
					property.addHouses(1);
					tempHouses = property.getHouses();
				}
				cash -= 300;
				System.out.println();
				System.out.println(name + " bought houses on the Oranges. They now have " + tempHouses + " houses");
			}
		}
		

		//costs 150 for houses
		if (ownReds == true) {
		
			if (ownedReds.get(0).getHouses() == 5); // if full of houses skip
			
			else if (cash > 450) {
				
				for (Property property : ownedReds) {
					
					property.addHouses(1);
					tempHouses = property.getHouses();
				}
				cash -= 450;
				System.out.println();
				System.out.println(name + " bought houses on the Reds. They now have " + tempHouses + " houses");
			}
		}

		
		//costs 150 for houses
		if (ownYellows == true) {
			
			if (ownedYellows.get(0).getHouses() == 5); // if full of houses skip
			
			else if (cash > 450) {
				
				for (Property property : ownedYellows) {
					
					property.addHouses(1);
					tempHouses = property.getHouses();
				}
				cash -= 450;
				System.out.println();
				System.out.println(name + " bought houses on the Yellows. They now have " + tempHouses + " houses");
			}
		}
		
		
		//costs 200 for houses
		if (ownGreens == true) {

			if (ownedGreens.get(0).getHouses() == 5); // if full of houses skip
			
			else if (cash > 600) {
				
				for (Property property : ownedGreens) {
					
					property.addHouses(1);
					tempHouses = property.getHouses();
				}
				cash -= 600;
				System.out.println();
				System.out.println(name + " bought houses on the Greens. They now have " + tempHouses + " houses");
			}
		}

		
		//costs 200 for houses
		if (ownDarkBlues == true) {
			
			if (ownedDarkBlues.get(0).getHouses() == 5); // if full of houses skip
			
			else if (cash > 400) {
				
				for (Property property : ownedDarkBlues) {
					
					property.addHouses(1);
					tempHouses = property.getHouses();
				}
				cash -= 400;
				System.out.println();
				System.out.println(name + " bought houses on the Dark Blues. They now have " + tempHouses + " houses");
			}
		}
	}
	
	
	
	
	public void trade() {
		
		//this method is used to try and make the AIs trade with each other for more sets
		
		ArrayList<Property> usableProperties = new ArrayList<Property>(properties);
		Iterator<Property> Iterator1 = usableProperties.iterator();
		
		ArrayList<Property> set;
		Player owner, currentPlayer;
		Property property;
		int price;
		
		//get rid of all properties that are in a set
		
		/*
		for (Property lol : properties) {
			
			System.out.println(lol.getName());
		}
		System.out.println();
		*/
		
		while (Iterator1.hasNext()) {
			
			property = Iterator1.next();
			
			if (property.getSet()) {
				
				//System.out.println(property.getName());
				Iterator1.remove();
			}
		}
		
		Iterator<Property> Iterator2 = usableProperties.iterator();
		
		
		while (Iterator2.hasNext()) {
			
			//get property and set of property
			property = Iterator2.next();
			//System.out.println(property.getName());
			currentPlayer = property.getOwner();
			set = getSetProperties(property);
			set.remove(property); //remove owned properties from the set
			
			//iterator for the properties in the set that aren't owned by current player
			Iterator<Property> itr = set.iterator();
			
			//iterate through each property in set to buy them
			while (itr.hasNext()) {

				property = itr.next();
				owner = property.getOwner();
				
				price = (int) (property.getPrice()*1.5);
				
				if (owner.equals(Monopoly.temp) || owner.equals(currentPlayer)); //if no one owns the property skip it
				
				//buy the property if they have more than twice the price
				else if (cash > price * 2) {
					
					tradeProperty(currentPlayer, owner, price, property);
				}
			}
			
			
			//fill
		}
		
		
		
		
		
		
		
	}
	
	
	public void tradeProperty(Player player, Player owner, int price, Property property) {
		
		payPlayer(owner, price);
		cash -= price;
		property.setOwner(player);
		System.out.println();
		System.out.println(name + " bought " + property.getName() + " from " + owner.getName() + " for " + ConsoleColors.GREEN_BOLD + "$" + price + ConsoleColors.RESET);
	}
	
	
	//checks properties to make sure there aren't any duplicates
	public void checkProperties() {
		
		Property propertyCheck;
		
		for (int i = 0; i < properties.size(); i++) {
			
			propertyCheck = properties.get(i);
			
			for (int j = i + 1; j < properties.size(); j++) {
				
				if (propertyCheck.equals(properties.get(j))) {
					
					properties.remove(j);
				}
			}
		}
	}
	
	
	
	public void checkForSets() {
		
		//ArrayList<Property> totalProperties = new ArrayList<Property>(Monopoly.properties);
		
		ArrayList<Property> ownedProperties = new ArrayList<Property>(properties);
		ArrayList<Property> set;
		String type;
		
		ListIterator<Property> iterator = ownedProperties.listIterator();
		
		while (iterator.hasNext()) {
			
			Property property = iterator.next();
			type = property.getSetType();
			
			if (!property.getSet()) {
				
				if (type.equals("Brown")) {
					
					set = getSetProperties(property);
					
					if (ownedProperties.containsAll(set)) {
						
						int houses = Monopoly.browns.get(0).getHouses();
						System.out.println();
						System.out.println(name + " owns the Browns set with " + houses + " houses");
						ownBrowns = true;
					
						for (Property tempProperty : Monopoly.browns) {
							
							tempProperty.setSet(true);
						}
					}
				}
				
				else if (type.equals("Blue")) {
					
					set = getSetProperties(property);
					
					if (ownedProperties.containsAll(set)) {
	
						int houses = Monopoly.blues.get(0).getHouses();
						System.out.println();
						System.out.println(name + " owns the Blues set with " + houses + " houses");
						ownBlues = true;
						
						for (Property tempProperty : Monopoly.blues) {
							
							tempProperty.setSet(true);
						}
					}
				}
				
				else if (type.equals("Pink")) {
					
					set = getSetProperties(property);
					
					if (ownedProperties.containsAll(set)) {
						
						int houses = Monopoly.pinks.get(0).getHouses();
						System.out.println();
						System.out.println(name + " owns the Pinks set with " + houses + " houses");
						ownPinks = true;
						
						for (Property tempProperty : Monopoly.pinks) {
							
							tempProperty.setSet(true);
						}
					}
				}
				
				else if (type.equals("Orange")) {
					
					set = getSetProperties(property);
					
					if (ownedProperties.containsAll(set)) {
						
						int houses = Monopoly.oranges.get(0).getHouses();
						System.out.println();
						System.out.println(name + " owns the Oranges set with " + houses + " houses");
						ownOranges = true;
						
						for (Property tempProperty : Monopoly.oranges) {
							
							tempProperty.setSet(true);
						}
					}
				}
				
				else if (type.equals("Red")) {
					
					set = getSetProperties(property);
					
					if (ownedProperties.containsAll(set)) {
						
						int houses = Monopoly.reds.get(0).getHouses();
						System.out.println();
						System.out.println(name + " owns the Reds set with " + houses + " houses");
						ownReds = true;
						
						for (Property tempProperty : Monopoly.reds) {
							
							tempProperty.setSet(true);
						}
					}
				}
				
				else if (type.equals("Yellow")) {
					
					set = getSetProperties(property);
					
					if (ownedProperties.containsAll(set)) {
						
						int houses = Monopoly.yellows.get(0).getHouses();
						System.out.println();
						System.out.println(name + " owns the Yellows set with " + houses + " houses");
						ownYellows = true;
						
						for (Property tempProperty : Monopoly.yellows) {
							
							tempProperty.setSet(true);
						}
					}
				}
				
				else if (type.equals("Green")) {
					
					set = getSetProperties(property);
					
					if (ownedProperties.containsAll(set)) {
						
						int houses = Monopoly.greens.get(0).getHouses();
						System.out.println();
						System.out.println(name + " owns the Greens set with " + houses + " houses");
						ownGreens = true;
						
						for (Property tempProperty : Monopoly.greens) {
							
							tempProperty.setSet(true);
						}
					}
				}
				
				else if (type.equals("Dark Blue")) {
					
					set = getSetProperties(property);
					
					if (ownedProperties.containsAll(set)) {
						
						int houses = Monopoly.darkBlues.get(0).getHouses();
						System.out.println();
						System.out.println(name + " owns the Dark Blues set with " + houses + " houses");
						ownDarkBlues = true; 
						
						for (Property tempProperty : Monopoly.darkBlues) {
							
							tempProperty.setSet(true);
						}
					}
				}
				
				else if (type.equals("Railroad")) {
					
					set = getSetProperties(property);
					
					if (ownedProperties.containsAll(set)) {
						
						System.out.println();
						System.out.println(name + " owns the Railroads set");
						ownRailroads = 4;
					}
				}
				
				else if (type.equals("Utility")) {
					
					set = getSetProperties(property);
					
					if (ownedProperties.containsAll(set)) {
						
						System.out.println();
						System.out.println(name + " owns the Utilities set");
						ownUtilities = true;
					}
				}
				
			}
		}
		
	}
	
	public ArrayList<Property> getSetProperties(Property property) {
		
		String type = property.getSetType();
		
		if (type.equals("Brown")) {
			
			return Monopoly.browns;
		}
		
		else if (type.equals("Blue")) {
			
			return Monopoly.blues;
		}
		
		else if (type.equals("Pink")) {
			
			return Monopoly.pinks;
		}
		
		else if (type.equals("Orange")) {
			
			return Monopoly.oranges;
		}
		
		else if (type.equals("Red")) {
			
			return Monopoly.reds;
		}
		
		else if (type.equals("Yellow")) {
			
			return Monopoly.yellows;
		}
		
		else if (type.equals("Green")) {
			
			return Monopoly.greens;
		}
		
		else if (type.equals("Dark Blue")) {
			
			return Monopoly.darkBlues;
		}
		
		else if (type.equals("Railroad")) {
			
			return Monopoly.railroads;
		}
		
		else if (type.equals("Utility")) {
			
			return Monopoly.utilities;
		}
		
		else return null;
	}
	
	
	
	public void payRent(Player owner, int rent) {
		
		removeCash(rent);
		owner.addCash(rent);
		System.out.println();
		System.out.println(name + " paid " + ConsoleColors.GREEN_BOLD + "$" + rent + ConsoleColors.RESET + " to " + owner.getName());
		System.out.println();
		printCash();
	}
	
	public void payPlayer(Player player, int rent) {
		
		removeCash(rent);
		player.addCash(rent);
		System.out.println();
		System.out.println(name + " paid " + ConsoleColors.GREEN_BOLD + "$" + rent + ConsoleColors.RESET + " to " + player.getName());
		System.out.println();
		printCash();
	}
	
	
	public void manageMortgages(int rent, Player owner, int turn, String type, Property realProperty) {
		
		ArrayList<Property> copyOfProperties = new ArrayList<Property>(properties);
		Iterator<Property> itr = copyOfProperties.iterator();
		int tempMortgage;
		
		// Get rid of all properties that the player has a full set of
		
		if (ownBrowns == true) {
			
			while (itr.hasNext()) {
				
				Property property = itr.next();
				
				if (property.getSetType().equals("Brown")) {
					
					itr.remove();
				}
			}
		}
		
		if (ownBlues == true) {
			
			while (itr.hasNext()) {
				
				Property property = itr.next();
			
				if (property.getSetType().equals("Blue")) {
					
					itr.remove();
				}
			}
		}
		
		if (ownPinks == true) {
			
			while (itr.hasNext()) {
				
				Property property = itr.next();
				
				if (property.getSetType().equals("Pink")) {
					
					itr.remove();
				}
			}
		}
		
		if (ownOranges == true) {
			
			while (itr.hasNext()) {
				
				Property property = itr.next();
				
				if (property.getSetType().equals("Orange")) {
					
					itr.remove();
				}
			}
		}
		
		if (ownReds == true) {
			
			while (itr.hasNext()) {
				
				Property property = itr.next();
				
				if (property.getSetType().equals("Red")) {
					
					itr.remove();
				}
			}
		}
		
		if (ownYellows == true) {
			
			while (itr.hasNext()) {
				
				Property property = itr.next();
				
				if (property.getSetType().equals("Yellow")) {
					
					itr.remove();
				}
			}
		}
		
		if (ownGreens == true) {
			
			while (itr.hasNext()) {
				
				Property property = itr.next();
				
				if (property.getSetType().equals("Pink")) {
					
					itr.remove();
				}
			}
		}
		
		if (ownDarkBlues == true) {
			
			while (itr.hasNext()) {
				
				Property property = itr.next();
				
				if (property.getSetType().equals("Dark Blue")) {
					
					itr.remove();
				}
			}
		}
		
		if (ownRailroads >= 1) {
			
			while (itr.hasNext()) {
				
				Property property = itr.next();
				
				if (property.getSetType().equals("Railroad")) {
					
					itr.remove();
				}
			}
		}
		
		if (ownUtilities == true) {
			
			while (itr.hasNext()) {
				
				Property property = itr.next();
				
				if (property.getSetType().equals("Utility")) {
					
					itr.remove();
				}
			}
		}
		
		
		//Make sure you didnt get rid of all properties
		// if you did then we reset the copyOfProperties for all of them
		if (copyOfProperties.size() == 0) {
			
			copyOfProperties = new ArrayList<Property>(properties);
		}
		
		
		//remove properties that have houses on them
		while (itr.hasNext()) {
			
			Property property = itr.next();
			
			if (property.getHouses() >= 1) {
				
				itr.remove();
			}
		}
		
		//reorder properties to make it ready for choosing which property to mortgage
		reorderPropertiesByRent(copyOfProperties);
		
				
		
		//now to mortgage the property or manage the houses
		
		
		
		boolean fixed = false;
		
		for (Property property : copyOfProperties) {
			
			if (property.getHouses() >= 1); //if there are houses on property skip it
			
			else {

				fixed = true;
				
				tempMortgage = property.getMortgage();
				
				if (tempMortgage > rent) {
					
					property.setMortgaged(true);
					cash += tempMortgage;
					
					if (type.equals("Street") || type.equals("Railroad") || type.equals("Utility")) {
						
						payRent(owner, rent);
						System.out.println();
						System.out.println(name + " mortgaged " + property.getName() + " and was able to pay the rent");					
					}
					
					else if (type.equals("Tax")) {
						
						taxPropertyDecisionHandler(realProperty, turn);
					}
					
					else if (type.equals("Card")) {
						
						System.out.println("fix this Player line 894 manageMortgages() if type.equals(card)");
						System.exit(0);
					}
					
					return;
				}	
			}
		}
		
		
		if (fixed == false) {
			
			ArrayList<Property> set;
			int tempHouseValue, tempValue; 
			String tempType;
			
			for (Property property : copyOfProperties) {
				
				//values 
				tempHouseValue = property.getHousePrice();
				tempType = property.getSetType();
				
				//value of one house on all properties in the set
				if (tempType.equals("Brown") || tempType.equals("Dark Blue")) {
					
					tempValue = tempHouseValue * 2; 
				}
				else {
					
					tempValue = tempHouseValue * 3;
				}
				
				
				if (tempValue > rent) {
					
					fixed = true;
					
					set = getSetProperties(property);
					
					for (Property setProperty : set) {
						
						setProperty.removeHouses(1);
					}
					
					cash += tempValue;
					
					if (type.equals("Street") || type.equals("Railroad") || type.equals("Utility")) {
						
						payRent(owner, rent);
						System.out.println();
						System.out.println(name + " mortgaged " + property.getName() + " and was able to pay the rent");					
					}

					else if (type.equals("Tax")) {
							
						taxPropertyDecisionHandler(realProperty, turn);
					}
						
					return;
				}	
			}
		}
		
		
		
		if (fixed == false) {
			
			ArrayList<Property> set;
			int tempHouseValue, tempValue, tempHouses, tempTotalValue; 
			String tempType;
			
			for (Property property : copyOfProperties) {
				
				//get values
				tempHouses = property.getHouses();
				tempHouseValue = property.getHousePrice();
				tempType = property.getSetType();
				
				//make it the value of a house on all properties in set
				if (tempType.equals("Brown") || tempType.equals("Dark Blue")) {
					
					tempValue = tempHouseValue * 2; 
				}
				else {
					
					tempValue = tempHouseValue * 3;
				}
				
				//total value of all houses in set
				tempTotalValue = tempHouses * tempValue;
				
				if (tempTotalValue > rent) {
					
					fixed = true;
					
					set = getSetProperties(property);
					
					for (Property setProperty : set) {
						
						setProperty.removeHouses(1);
					}
					
					cash += tempTotalValue;
					
					if (type.equals("Street") || type.equals("Railroad") || type.equals("Utility")) {
						
						payRent(owner, rent);
						System.out.println();
						System.out.println(name + " mortgaged " + property.getName() + " and was able to pay the rent");					
					}

					else if (type.equals("Tax")) {
							
						taxPropertyDecisionHandler(realProperty, turn);
					}
						
					return;
				}	
			}
		}
		
		
		System.out.println();
		System.out.println("The rent is too much and player " + name + " cannot pay it.");
		System.out.println();
		lose(turn);
		
	}
	
	
	
	public void cardManageMortgages(int amount, int turn) {
		
		ArrayList<Property> copyOfProperties = new ArrayList<Property>(properties);
		Iterator<Property> itr = copyOfProperties.iterator();
		int tempMortgage;
		
		// Get rid of all properties that the player has a full set of
		
		if (ownBrowns == true) {
			
			while (itr.hasNext()) {
				
				Property property = itr.next();
				
				if (property.getSetType().equals("Brown")) {
					
					itr.remove();
				}
			}
		}
		
		if (ownBlues == true) {
			
			while (itr.hasNext()) {
				
				Property property = itr.next();
			
				if (property.getSetType().equals("Blue")) {
					
					itr.remove();
				}
			}
		}
		
		if (ownPinks == true) {
			
			while (itr.hasNext()) {
				
				Property property = itr.next();
				
				if (property.getSetType().equals("Pink")) {
					
					itr.remove();
				}
			}
		}
		
		if (ownOranges == true) {
			
			while (itr.hasNext()) {
				
				Property property = itr.next();
				
				if (property.getSetType().equals("Orange")) {
					
					itr.remove();
				}
			}
		}
		
		if (ownReds == true) {
			
			while (itr.hasNext()) {
				
				Property property = itr.next();
				
				if (property.getSetType().equals("Red")) {
					
					itr.remove();
				}
			}
		}
		
		if (ownYellows == true) {
			
			while (itr.hasNext()) {
				
				Property property = itr.next();
				
				if (property.getSetType().equals("Yellow")) {
					
					itr.remove();
				}
			}
		}
		
		if (ownGreens == true) {
			
			while (itr.hasNext()) {
				
				Property property = itr.next();
				
				if (property.getSetType().equals("Pink")) {
					
					itr.remove();
				}
			}
		}
		
		if (ownDarkBlues == true) {
			
			while (itr.hasNext()) {
				
				Property property = itr.next();
				
				if (property.getSetType().equals("Dark Blue")) {
					
					itr.remove();
				}
			}
		}
		
		if (ownRailroads >= 1) {
			
			while (itr.hasNext()) {
				
				Property property = itr.next();
				
				if (property.getSetType().equals("Railroad")) {
					
					itr.remove();
				}
			}
		}
		
		if (ownUtilities == true) {
			
			while (itr.hasNext()) {
				
				Property property = itr.next();
				
				if (property.getSetType().equals("Utility")) {
					
					itr.remove();
				}
			}
		}
		
		
		//Make sure you didnt get rid of all properties
		// if you did then we reset the copyOfProperties for all of them
		if (copyOfProperties.size() == 0) {
			
			copyOfProperties = new ArrayList<Property>(properties);
		}
		
		//reorder properties to make it ready for choosing which property to mortgage
		reorderPropertiesByRent(copyOfProperties);
		
		
		
		//now to mortgage properties
		
		
		
		
		boolean fixed = false;
		
		for (Property property : copyOfProperties) {
			
			tempMortgage = property.getMortgage();
			
			if (tempMortgage > amount) {
				
				fixed = true;
				
				property.setMortgaged(true);
				cash += tempMortgage;
				
				System.out.println();
				System.out.println(name + " mortgaged " + property.getName() + " and was able to pay the amount");
				
				cash -= amount;
				Property.freeParkingPot += amount;
				
				return;
			}
		}
		

		if (fixed == false) {
			
			ArrayList<Property> set;
			int tempHouseValue, tempValue; 
			String tempType;
			
			for (Property property : copyOfProperties) {
				
				//values 
				tempHouseValue = property.getHousePrice();
				tempType = property.getSetType();
				
				//value of one house on all properties in the set
				if (tempType.equals("Brown") || tempType.equals("Dark Blue")) {
					
					tempValue = tempHouseValue * 2; 
				}
				else {
					
					tempValue = tempHouseValue * 3;
				}
				
				
				if (tempValue > amount) {
					
					fixed = true;
					
					set = getSetProperties(property);
					
					for (Property setProperty : set) {
						
						setProperty.removeHouses(1);
					}

					cash += tempValue;
					
					System.out.println();
					System.out.println(name + " mortgaged " + property.getName() + " and was able to pay the amount");
					
					cash -= amount;
					Property.freeParkingPot += amount;
					
					return;					
				}	
			}
		}
		
		
		
		if (fixed == false) {
			
			ArrayList<Property> set;
			int tempHouseValue, tempValue, tempHouses, tempTotalValue; 
			String tempType;
			
			for (Property property : copyOfProperties) {
				
				//get values
				tempHouses = property.getHouses();
				tempHouseValue = property.getHousePrice();
				tempType = property.getSetType();
				
				//make it the value of a house on all properties in set
				if (tempType.equals("Brown") || tempType.equals("Dark Blue")) {
					
					tempValue = tempHouseValue * 2; 
				}
				else {
					
					tempValue = tempHouseValue * 3;
				}
				
				//total value of all houses in set
				tempTotalValue = tempHouses * tempValue;
				
				if (tempTotalValue > amount) {
					
					fixed = true;
					
					set = getSetProperties(property);
					
					for (Property setProperty : set) {
						
						setProperty.removeHouses(1);
					}

					cash += tempTotalValue;
					
					System.out.println();
					System.out.println(name + " mortgaged " + property.getName() + " and was able to pay the amount");
					
					cash -= amount;
					Property.freeParkingPot += amount;
					
					return;					
				}	
			}
		}
		
		System.out.println();
		System.out.println("The rent is too much and player " + name + " cannot pay it.");
		System.out.println();
		lose(turn);
	}
	

	public void printCash() {
		
		System.out.println(name + " has " + ConsoleColors.GREEN_BOLD + "$" + cash + ConsoleColors.RESET + " in their account");
	}
	
	public void printProperties() {
		
		System.out.println();
		System.out.println(name + "'s owned properties:");
		System.out.println();
		
		for (Property property : properties) {
			
			System.out.println("- " + property.getName() + " - " + property.getSetType());
		}
	}
	
	
	//-----------------------------------------------------------------------------
	
	//Methods to handle players losing
	
	public void manageAssetsAfterLoss() {
		
		for (Property property : properties) {
			
			System.out.println(property.getName() + " no longer has an owner"); // wrong, in monopoly the bank auctions it off but ill deal with that later
			property.setOwned(false);
			property.setOwner(null);
		}
		System.out.println();
		
	}
	
	
	public int getRealRent(Property property) {
		
		int houses = property.getHouses();
		
		if (houses == 0) {
			
			return property.getRent();
		}
		
		else if (houses == 1) {
			
			return property.getHouseOneRent();
		}
		
		else if (houses == 2) {
			
			return property.getHouseTwoRent();
		}
		
		else if (houses == 3) {
			
			return property.getHouseThreeRent();
		}
		
		else if (houses == 4) {
			
			return property.getHouseFourRent();
		}
		else return 1000000;
	}
	
	
	//Reorders the player order hashMap
	
	public void reorderPlayerOrder() {
		
		int size = Monopoly.playerOrder.size();
		
		if (size == 3) {
			
			reorderThreePlayers();
		}
		
		else if (size == 2) {
			
			reorderTwoPlayers();
		}
		if (size == 1) {
			
			reorderOnePlayer();
		}
		
		//if size = 1, game is won
	}

	public void reorderThreePlayers() {
		
		Player temp;
		
		if (Monopoly.playerOrder.get(1) == null) {
			
			temp = Monopoly.playerOrder.get(2);
			Monopoly.playerOrder.put(1, temp);
			
			temp = Monopoly.playerOrder.get(3);
			Monopoly.playerOrder.put(2, temp);
			
			temp = Monopoly.playerOrder.get(4);
			Monopoly.playerOrder.put(3, temp);
			
			Monopoly.playerOrder.remove(4);
		}
		
		else if (Monopoly.playerOrder.get(2) == null) {
			
			temp = Monopoly.playerOrder.get(3);
			Monopoly.playerOrder.put(2, temp);
			
			temp = Monopoly.playerOrder.get(4);
			Monopoly.playerOrder.put(3, temp);
			
			Monopoly.playerOrder.remove(4);
		}
		
		else if (Monopoly.playerOrder.get(3) == null) {
			
			temp = Monopoly.playerOrder.get(4);
			Monopoly.playerOrder.put(3, temp);
			
			Monopoly.playerOrder.remove(4);
		}
	}
	
	public void reorderTwoPlayers() {
		
		Player temp;
		
		if (Monopoly.playerOrder.get(1) == null) {
			
			temp = Monopoly.playerOrder.get(2);
			Monopoly.playerOrder.put(1, temp);
			
			temp = Monopoly.playerOrder.get(3);
			Monopoly.playerOrder.put(2, temp);
			
			Monopoly.playerOrder.remove(3);
		}
		
		else if (Monopoly.playerOrder.get(2) == null) {
			
			temp = Monopoly.playerOrder.get(3);
			Monopoly.playerOrder.put(2, temp);
			
			Monopoly.playerOrder.remove(3);
		}
	}
	
	public void reorderOnePlayer() {
		
		Player temp;
		
		if (Monopoly.playerOrder.get(1) == null) {
			
			temp = Monopoly.playerOrder.get(2);
			Monopoly.playerOrder.put(1, temp);
			
			Monopoly.playerOrder.remove(2);
		}
	
	}
	
	public void sendToJail() {
		
		sendToJailPosition();
		System.out.println();
		inJail = true;
		System.out.println(name + " has been sent to Jail");
	}
	
	public static void reorderPropertiesByRent(ArrayList<Property> properties) {
	   
		// Sort the list of properties using a Comparator
	    Collections.sort(properties, new Comparator<Property>() {
	      
	    	@Override
	    	public int compare(Property p1, Property p2) {
	    		
	    		// Compare the rent of the two properties
	    		return p1.getRent() - p2.getRent();
	        }
	    });
	}
	
	
	
	//-----------------------------------------------------------------------------
	
	//Methods to handle players losing
	
	public void lose(int turn) {
		
		//manage the assets the Player owned
		manageAssetsAfterLoss();
		lostThisRound = true;
		
		System.out.println("-----------------------------------");
		System.out.println();
		System.out.println(name + " has lost on round " + Monopoly.round + " and will now be removed");
		
		
		//Checks if he is winning
		if (Monopoly.playerOrder.size()-1 == 1) {
			
			Monopoly.playerOrder.remove(turn);
			reorderPlayerOrder();
			return;
		}
		
		else;
		
				
		
		System.out.println();
		System.out.println("The order before removal:");
		
		{
			Collection<Player> values = Monopoly.playerOrder.values();
			int position = 1;
			
			for (Player space : values) {
			
				System.out.println("The player in order " + position + " is taken by " + space.getName());
				position++;
			}
		}
		
		System.out.println();
		
		Monopoly.playerOrder.remove(turn);
		reorderPlayerOrder();
		
		//p
		System.out.println("The order after removal:");
		{
			Collection<Player> values = Monopoly.playerOrder.values();
			int position = 1;
			
			for (Player space : values) {
			
				System.out.println("The player in order " + position + " is taken by " + space.getName());
				position++;
			}
		}
		
		
		//Sleep for 10 seconds to read who lost
		
		sleep(10);
	}
	
	public boolean jailDiceRollDoubles() {
		
		Random random = new Random();
		
		int diceRoll1 = random.nextInt(6)+1;
		int diceRoll2 = random.nextInt(6)+1;
		
		System.out.println();
		System.out.println(name + " rolled a " + diceRoll1);

		System.out.println();
		System.out.println(name + " rolled a " + diceRoll2);
		if (diceRoll1 == diceRoll2) {
			
			return true;
		}
		else {
			
			return false;
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






















