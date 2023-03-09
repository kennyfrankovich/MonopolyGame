
public class Property {

	private String name;
	private Player owner;
	private boolean owned;
	private int price;
	private int mortgage;
	private int mortgageReversalPrice;
	private boolean mortgaged;
	private int houses;
	private boolean set;
	private int railroadSet;
	private int rent;
	private int houseOneRent;
	private int houseTwoRent;
	private int houseThreeRent;
	private int houseFourRent;
	private int hotelRent;
	private int housePrice;
	private String type;
	private String setType;
	
	public static int freeParkingPot = 100;
	
	//-----------------------------------------------------------------------------
	
	// Constructors
	
	public Property(String name, int price, int rent, int house1, int house2, int house3, int house4, int hotel, int housePrice, int mortgageReversalPrice, String setType) {
	
		//Street Properties Constructor
		
		this.name = name;
		owner = Monopoly.temp;
		owned = false;
		this.price = price;
		this.mortgage = price/2;
		this.mortgageReversalPrice = mortgageReversalPrice;
		mortgaged = false;
		houses = 0;
		set = false;
		this.rent = rent;
		this.houseOneRent = house1;
		this.houseTwoRent = house2;
		this.houseThreeRent = house3;
		this.houseFourRent = house4;
		this.hotelRent = hotel;
		this.housePrice = housePrice;
		type = "Street";
		this.setType = setType;
		
		//System.out.println("The " + this.name + " property has been initialized.");
	}
	
	public Property(String name, int mortgage) {
		
		//Railroads Constructor
		
		owner = Monopoly.temp;
		this.name = name;
		price = 200;
		this.mortgage = mortgage;
		mortgageReversalPrice = 110;
		mortgaged = false;
		railroadSet = 0;
		type = "Railroad";
		this.setType = "Railroad";
		
		//System.out.println("The " + this.name + " property has been initialized.");
	}
	
	public Property(String name) {
		
		//Utilities Constructor
		
		owner = Monopoly.temp;
		this.name = name;
		price = 150;
		mortgage = price/2;
		mortgageReversalPrice = 83;
		set = false;
		type = "Utility";
		setType = "Utility";
		
		this.rent = 4;
		
		//System.out.println("The " + this.name + " property has been initialized.");
	}
	
	public Property(String name, int nothing, int nothing1) {

		//Community Chest/Chance Card Constructor
		this.name = name;
		type = "Card";
	}
	
	public Property(String name, int tax, int nothing, int nothing1) {
		
		//Taxes Constructor
		this.name = name;
		price = tax;
		type = "Tax";
	}
	
	public Property(String name, int nothing, int nothing1, int nothing2, int nothing3) {
		
		//Corners Constructor
		this.name = name;
		type = "Corner";
	}
	
	//-----------------------------------------------------------------------------
	
	//Methods to get values
	
	public String getName() {
		
		return name;
	}
	
	public Player getOwner() {
		
		return owner;
	}
	
	public boolean getOwned() {
		
		return owned;
	}
	
	public int getPrice() {
		
		return price;
	}
	
	public int getMortgage() {
		
		return mortgage;
	}
	
	public int getMortgageReversalPrice() {
		
		return mortgageReversalPrice;
	}
	
	public boolean getMortgaged() {
		
		return mortgaged;
	}
	
	public int getHouses() {
		
		return houses;
	}
	
	public boolean getSet() {
		
		return set;
	}
	
	public int getRailroadSet() {
		
		return railroadSet;
	}
	
	public int getRent() {
		
		return rent;
	}
	
	public int getHouseOneRent() {
		
		return houseOneRent;
	}
	
	public int getHouseTwoRent() {
		
		return houseTwoRent;
	}
	
	public int getHouseThreeRent() {
	
		return houseThreeRent;
	}
	
	public int getHouseFourRent() {
	
		return houseFourRent;
	}
	
	public int getHotelRent() {
		
		return hotelRent;
	}
	
	public int getHousePrice() {
		
		return housePrice;
	}
	
	public String getType() {
		
		return type;
	}
	
	public String getSetType() {
		
		return setType;
	}
	
	//-----------------------------------------------------------------------------
	
	// Methods to set values
	
	public void setOwner(Player owner) {
		
		this.owner = owner;
	}
	
	public void setOwned(boolean owned) {
		
		this.owned = owned;
	}

	public void setMortgaged(boolean mortgaged) {
		
		this.mortgaged = mortgaged;
	}
	
	public void setHouses(int houses) {
		
		this.houses = houses;
	}
	
	public void addHouses(int add) {
		
		houses += add;
	}
	
	public void removeHouses(int remove) {
		
		houses -= remove;
	}
	
	public void setSet(boolean set) {
		
		this.set = set;
	}
	
	
	
	//-----------------------------------------------------------------------------
	
	//Index method
	
	public static void index(Property property) {
		
		System.out.println();
		System.out.println(property.getName() + ":");
		System.out.println();
		System.out.println("The owner of this property is " + property.getOwner());
		System.out.println("The price of this property is " + property.getPrice());
		System.out.println("The mortgage value of this property " + property.getMortgage());
		System.out.println("Mortgaged: " + property.getMortgaged());
		System.out.println();
		System.out.println("There are " + property.getHouses() + " houses on this property");
		System.out.println("You own " + property.getSet() + " of the other properties");
		System.out.println();
		System.out.println("The rent without houses is " + property.getRent());
		System.out.println("The rent with 1 house is " + property.getHouseOneRent());
		System.out.println("The rent with 2 houses is " + property.getHouseTwoRent());
		System.out.println("The rent with 3 houses is " + property.getHouseThreeRent());
		System.out.println("The rent with 4 houses is " + property.getHouseFourRent());
		System.out.println("The rent with a hotel is " + property.getHotelRent());
		System.out.println();
		System.out.println("The price of a house is " + property.getHousePrice());
		System.out.println("The type of this property is: " + property.getType());

	}
}
