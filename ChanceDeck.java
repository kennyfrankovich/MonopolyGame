import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ChanceDeck {
	
	private List<Card> cards;

	public ChanceDeck() {
		
		cards = new ArrayList<Card>();
		
		// Add the chance cards to the deck
		
		cards.add(new Card("Advance to Go (Collect " + ConsoleColors.GREEN_BOLD + "$200" + ConsoleColors.RESET + ")", Card.CardType.ADVANCE_TO_GO));
		
		cards.add(new Card("Advance to Illinois Ave. If you pass Go, collect " + ConsoleColors.GREEN_BOLD + "$200" + ConsoleColors.RESET, Card.CardType.ADVANCE_TO_LOCATION, 24));
		
		cards.add(new Card("Advance to St. Charles Place. If you pass Go, collect " + ConsoleColors.GREEN_BOLD + "$200" + ConsoleColors.RESET, Card.CardType.ADVANCE_TO_LOCATION, 11));
		
		cards.add(new Card("Advance to the nearest Utility. If unowned, you may buy it from the Bank. If owned, pay the rent.", Card.CardType.ADVANCE_TO_NEAREST_UTILITY));
		
		cards.add(new Card("Advance to the nearest Railroad and pay the rent if owned. If Railroad is unowned, you may buy it from the Bank.", Card.CardType.ADVANCE_TO_NEAREST_RAILROAD));
		
		cards.add(new Card("Bank pays you dividend of " + ConsoleColors.GREEN_BOLD + "$50" + ConsoleColors.RESET, Card.CardType.BANK_PAYS_DIVIDEND));
		
		cards.add(new Card("Get out of Jail Free", Card.CardType.GET_OUT_OF_JAIL_FREE));
		
		cards.add(new Card("Go back 3 spaces", Card.CardType.GO_BACK_3_SPACES));
		
		cards.add(new Card("Go to Jail. Go directly to jail. Do not pass Go, do not collect " + ConsoleColors.GREEN_BOLD + "$200" + ConsoleColors.RESET, Card.CardType.GO_TO_JAIL));
		
		cards.add(new Card("Make general repairs on all your property. For each house pay " + ConsoleColors.GREEN_BOLD + "$25" + ConsoleColors.RESET, Card.CardType.MAKE_REPAIRS));
		
		cards.add(new Card("Pay poor tax of " + ConsoleColors.GREEN_BOLD + "$15" + ConsoleColors.RESET, Card.CardType.PAY_POOR_TAX));
		
		cards.add(new Card("Take a trip to Reading Railroad. If you pass Go, collect " + ConsoleColors.GREEN_BOLD + "$200" + ConsoleColors.RESET, Card.CardType.TAKE_TRIP));
		
		cards.add(new Card("Take a walk on the Broadway. Advance token to Broadway.", Card.CardType.TAKE_WALK));
		
		cards.add(new Card("You have been elected Chairman of the Board. Pay each player " + ConsoleColors.GREEN_BOLD + "$50" + ConsoleColors.RESET, Card.CardType.ELECTED_CHAIRMAN));
		
		cards.add(new Card("Your building and loan matures. Collect " + ConsoleColors.GREEN_BOLD + "$150" + ConsoleColors.RESET, Card.CardType.BUILDING_AND_LOAN_MATURES));
		
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
