/**
 */
public class Card {

	/** Suits */
	private final static int SPADES = 0, HEARTS = 1, DIAMONDS = 2, CLUBS = 3;

	/** comment */
	private transient final int myRank;

	/** comment */
	private transient final int mySuit;

	/** comment */
	public Card(final int theRank, final int theSuit) {

		if (theSuit != SPADES && theSuit != HEARTS && theSuit != DIAMONDS
		        && theSuit != CLUBS) {
			throw new IllegalArgumentException("Illegal playing card suit");
		}
		if (theRank < 1 || theRank > 13) {
			throw new IllegalArgumentException("Illegal playing card value");
		}
		myRank = theRank;
		mySuit = theSuit;
	}

	/** comment */
	public int getRank() {

		return myRank;
	}

	/** comment */
	public int getSuit() {

		return mySuit;
	}

	/** comment */
	public String getRankAsString() {

		String rank = "";
		switch (myRank) {
			case 1:
				rank = "Ace";
				break;
			case 11:
				rank = "Jack";
				break;
			case 12:
				rank = "Queen";
				break;
			case 13:
				rank = "King";
				break;
			default:
				rank = Integer.toString(myRank);
				break;
		}

		return rank;

	}

	/** comment */
	public String getSuitAsString() {

		String suit = "";

		switch (mySuit) {
			case 0:
				suit = "Clubs";
				break;
			case 1:
				suit = "Diamonds";
				break;
			case 2:
				suit = "Hearts";
				break;
			case 3:
				suit = "Spades";
				break;
			default:
				break;
		}

		return suit;
	}

	/** comment */
	public String toString() {

		return getRankAsString() + " of " + getSuitAsString();
	}

}
