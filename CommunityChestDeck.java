import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CommunityChestDeck {
	
	private List<Card> cards;

	public CommunityChestDeck() {
		
		cards = new ArrayList<Card>();
		
		// Add the community chest cards to the deck
		
		cards.add(new Card("Advance to Go (Collect " + ConsoleColors.GREEN_BOLD + "$200" + ConsoleColors.RESET + ")", Card.CardType.ADVANCE_TO_GO));
		
		cards.add(new Card("Bank error in your favor. Collect " + ConsoleColors.GREEN_BOLD + "$200" + ConsoleColors.RESET, Card.CardType.BANK_ERROR));
		
		cards.add(new Card("Doctor's fees. Pay " + ConsoleColors.GREEN_BOLD + "$50" + ConsoleColors.RESET, Card.CardType.DOCTOR_FEES));
		
		cards.add(new Card("From sale of stock you get " + ConsoleColors.GREEN_BOLD + "$50" + ConsoleColors.RESET, Card.CardType.SALE_OF_STOCK));
		
		cards.add(new Card("Get out of Jail Free", Card.CardType.GET_OUT_OF_JAIL_FREE));
		
		cards.add(new Card("Go to Jail. Go directly to jail. Do not pass Go, do not collect $200.", Card.CardType.GO_TO_JAIL));
		
		cards.add(new Card("Grand Opera Night. Collect " + ConsoleColors.GREEN_BOLD + "$50" + ConsoleColors.RESET + " from every player for opening night seats.", Card.CardType.GRAND_OPERA_NIGHT));
		
		cards.add(new Card("Holiday Fund matures. Collect " + ConsoleColors.GREEN_BOLD + "$100" + ConsoleColors.RESET, Card.CardType.HOLIDAY_FUND_MATURES));
		
		cards.add(new Card("Income tax refund. Collect " + ConsoleColors.GREEN_BOLD + "$20" + ConsoleColors.RESET, Card.CardType.INCOME_TAX_REFUND));
		
		cards.add(new Card("It is your birthday. Collect " + ConsoleColors.GREEN_BOLD + "$10" + ConsoleColors.RESET +" from every player.", Card.CardType.BIRTHDAY));
		
		cards.add(new Card("Life insurance matures. Collect " + ConsoleColors.GREEN_BOLD + "$100" + ConsoleColors.RESET, Card.CardType.LIFE_INSURANCE_MATURES));
		
		cards.add(new Card("Pay hospital fees of " + ConsoleColors.GREEN_BOLD + "$100" + ConsoleColors.RESET, Card.CardType.HOSPITAL_FEES));
		
		cards.add(new Card("Pay school fees of " + ConsoleColors.GREEN_BOLD + "$50" + ConsoleColors.RESET, Card.CardType.SCHOOL_FEES));
		
		cards.add(new Card("Receive " + ConsoleColors.GREEN_BOLD + "$25" + ConsoleColors.RESET + " consultancy fee.", Card.CardType.CONSULTANCY_FEE));
		
		cards.add(new Card("You are assessed for street repairs. Pay " + ConsoleColors.GREEN_BOLD + "$40" + ConsoleColors.RESET + " per house.", Card.CardType.STREET_REPAIRS));
		
		cards.add(new Card("You have won second prize in a beauty contest. Collect " + ConsoleColors.GREEN_BOLD + "$10" + ConsoleColors.RESET, Card.CardType.BEAUTY_CONTEST));
		
		cards.add(new Card("You inherit " + ConsoleColors.GREEN_BOLD + "$100" + ConsoleColors.RESET, Card.CardType.INHERITANCE));
		
		shuffle();
	}

	public void shuffle() {
		
		Collections.shuffle(cards);
	}

	public Card drawCard() {
	
		return cards.remove(0);
	}
	
	public void returnCard(Card card) {
		
		cards.add(card);
	}
	
}
