import java.io.IOException;
import java.util.Observable;

public abstract class Player extends Observable {

	private Card myCard;

	private Card myOldCard;

	private int num_Of_Quarter = 4;

	private String myPlayerName;

	private boolean isLoser = false;

	public Player(String thePlayerName) {
		setMyPlayerNumber(thePlayerName);
	}

	public void takeCard(Card theNewCard) {

		myCard = theNewCard;

	}

	public String getCard() {

		return myCard.toString();
	}

	public void rememberCard() {

		myOldCard = myCard;
	}

	public int getOldCardRank() {

		return myOldCard.getRank();
	}

	public Card giveCard() {

		return myCard;
	}

	public void loseQuarter() {

		num_Of_Quarter--;
	}

	public int getNumOfQuarters() {

		return num_Of_Quarter;
	}

	public String getMyPlayerName() {

		return myPlayerName;
	}

	public void setMyPlayerNumber(String thePlayerName) {

		this.myPlayerName = thePlayerName;
	}

	public String getName() {

		return "Player " + getMyPlayerName();

	}

	public void setLoserStatus() {

		isLoser = !isLoser;
	}

	public boolean getLoserStatus() {

		return isLoser;
	}

	public abstract boolean isHuman();

	public abstract void yourTurn() throws InterruptedException, IOException;

	public abstract void testPrint();

}
