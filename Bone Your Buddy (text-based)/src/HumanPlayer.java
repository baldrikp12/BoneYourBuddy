import java.util.Scanner;

public class HumanPlayer extends Player {

	public HumanPlayer(String thePlayerNumber) {
		super(thePlayerNumber);
	}

	public boolean isHuman() {

		return true;
	}

	public void yourTurn() throws InterruptedException {

		TextDisplay.showText(">> It's your turn");

		Scanner in = new Scanner(System.in);

		String theChoice = "";
		do {
			TextDisplay.showText(">> Trade card or keep (t/k)? ");
			theChoice = in.next();

			if (!("t").equalsIgnoreCase(theChoice)
			        && !("k").equalsIgnoreCase(theChoice)) {
				continue;
			}
			break;

		} while (true);

		System.out.println();

		setChanged();
		notifyObservers(theChoice.toLowerCase());

	}

	@Override
	public void testPrint() {

		System.out.println();
		System.out.println(">> Human(You) ");
		System.out.println(">> Your card - " + getCard());
		System.out.println(">> Number of Quarter: " + getNumOfQuarters());
		System.out.println();
	}

}
