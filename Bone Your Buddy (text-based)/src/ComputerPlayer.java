import java.util.Random;

public class ComputerPlayer extends Player {

	public ComputerPlayer(String thePlayerName) {
		super(thePlayerName);
	}

	public boolean isHuman() {

		return false;
	}

	@Override
	public void yourTurn() throws InterruptedException {

		String theChoice = "";

		if (giveCard().getRank() > getOldCardRank() || giveCard().getRank() > 9) {
			theChoice = "k";
		} else if (giveCard().getRank() < 5) {
			theChoice = "t";
		} else {
			TextDisplay.thinkingText();
			Random ran = new Random();
			int x = ran.nextInt(2) + 1;

			theChoice = (x == 1) ? "t" : "k";
		}

		setChanged();
		notifyObservers(theChoice);

	}

	@Override
	public void testPrint() {

		System.out.println("Computer " + getMyPlayerName());
		System.out.println(">> Their card - " + getCard());
		System.out.println(">> Number of Quarter: " + getNumOfQuarters());
	}

}
