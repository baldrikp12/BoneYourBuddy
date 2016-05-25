import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class DeckOfCards {

	/** comment */
	private static final int NUMBER_OF_CARDS = 52;

	/** comment */
	private transient final List<Card> MY_DECK = new ArrayList<Card>();

	/** comment */
	private int numCardsLeft = 0;

	/** comment */
	public DeckOfCards() {

	}

	/** comment */
	public final void buildNewDeck() {

		for (int rank = 1; rank < 14; rank++) { // Ranks
			for (int suit = 0; suit < 4; suit++) { // Suits
				MY_DECK.add(new Card(rank, suit));
				numCardsLeft++;
			}
		}

	}

	/** comment */
	public Card dealCard() {

		final Card card = MY_DECK.remove(numCardsLeft - 1);
		numCardsLeft--;

		return card;

	}

	/** comment */
	public void addCard(final Card theCard) {

		MY_DECK.add(theCard);
		numCardsLeft++;

	}

	public void clearDeck() {

		MY_DECK.clear();
	}

	/** comment */
	public Card lookAtCard(final int theIndex) {

		final Card theCard = MY_DECK.get(theIndex);

		return theCard;
	}

	/** comment */
	public void shuffleDeck() {

		Collections.shuffle(MY_DECK, new Random());
	}

	/** comment */
	public int size() {

		return numCardsLeft;
	}

}
