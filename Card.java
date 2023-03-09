public class Card {
	
	private String text;
	private CardType type;
	private int location; // Only used for ADVANCE_TO_LOCATION cards

	public enum CardType {
		 
		ADVANCE_TO_GO,
		ADVANCE_TO_LOCATION,
		ADVANCE_TO_NEAREST_UTILITY,
		ADVANCE_TO_NEAREST_RAILROAD,
		BANK_PAYS_DIVIDEND,
		GET_OUT_OF_JAIL_FREE,
		GO_BACK_3_SPACES,
		GO_TO_JAIL,
		MAKE_REPAIRS,
		PAY_POOR_TAX,
		TAKE_TRIP,
		TAKE_WALK,
		ELECTED_CHAIRMAN,
		BUILDING_AND_LOAN_MATURES,
		BANK_ERROR,
		DOCTOR_FEES,
		SALE_OF_STOCK,
		GRAND_OPERA_NIGHT,
		HOLIDAY_FUND_MATURES,
		INCOME_TAX_REFUND,
		BIRTHDAY,
		LIFE_INSURANCE_MATURES,
		HOSPITAL_FEES,
		SCHOOL_FEES,
		CONSULTANCY_FEE,
		STREET_REPAIRS,
		BEAUTY_CONTEST,
		INHERITANCE
	}

	//------------------------------------------------------------------
	
	// Constructors
	
	public Card(String text, CardType type) {
		
		this.text = text;
		this.type = type;
	}
	
	//only for advance to location types
	public Card(String text, CardType type, int location) {
		
		this.text = text;
		this.type = type;
		this.location = location;
	}
	
	//------------------------------------------------------------------

	public String getText() {
		
		return text;
	}

	public CardType getType() {
		
		return type;
	}

	public int getLocation() {
		
		return location;
	}
}
