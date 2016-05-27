import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

public class Game implements Observer {

	private static boolean playAgain = true;

	private int num_Of_Players = 0;

	private LinkedList<Player> playersList_In = new LinkedList<Player>();

	private LinkedList<Player> playersList_Out = new LinkedList<Player>();

	private List<Player> losersList = new ArrayList<Player>();

	private DeckOfCards deck = new DeckOfCards();

	private DeckOfCards discardDeck = new DeckOfCards();

	private int currentPlayer = 0;

	public Game() throws InterruptedException {

	}

	private void moveDealer() {

		playersList_In.addLast(playersList_In.getFirst());
		playersList_In.removeFirst();
	}

	private void startRound(int theRound)
	        throws InterruptedException, IOException {

		System.out.println();
		TextDisplay.showText(">> Starting round " + theRound);

		if (deck.size() - num_Of_Players < 1) {

			TextDisplay.showText(">> Building new deck.");
			deck.clearDeck();
			discardDeck.clearDeck();
			deck.buildNewDeck();
			deck.shuffleDeck();

		}

		System.out.println();
		TextDisplay.showText(">> Dealing cards ");
		System.out.println();

		for (Player p : playersList_In) {
			p.takeCard(deck.dealCard());
			p.rememberCard();
			if (p.isHuman()) {
				p.testPrint();
			}
		}

		/* Test Only */
		// System.out.print(">>Test<< (Cards Dealt) [");
		// for (int i = 0; i < playersList_In.size(); i++) {
		// if (i > 0) {
		// System.out.print(", ");
		// }
		// System.out.print(playersList_In.get(i).getCard());
		// }
		// System.out.println("]\n");
		// System.out.print("");
		/* Test Only */

		for (Player p : playersList_In) {
			p.yourTurn();
			currentPlayer++;
		}

		TextDisplay.showText(">> Everyone show your cards <<\n");

		calculateLosers();
		displayResults();
		updatePlayers();

		currentPlayer = 0;
	}

	private void displayResults() {

		for (Player p : playersList_In) {

			if (p.getLoserStatus() == true) {
				System.out.println(p.getName() + "(" + p.getNumOfQuarters()
				        + ")    " + p.getCard() + " (Loser)");
			} else {
				System.out.println(p.getName() + "(" + p.getNumOfQuarters()
				        + ")    " + p.getCard());
			}
		}

	}

	private void calculateLosers() {

		ArrayList<Integer> cardRanks = new ArrayList<Integer>();

		for (Player p : playersList_In) {
			cardRanks.add(p.giveCard().getRank());
		}

		int min = Collections.min(cardRanks);

		for (int index = 0; index < cardRanks.size(); index++) {
			if (min == playersList_In.get(index).giveCard().getRank()) {
				losersList.add(playersList_In.get(index));
				playersList_In.get(index).setLoserStatus();
			}
		}

	}

	private void updatePlayers() throws InterruptedException {

		System.out.println();

		if ((playersList_In.size() == 2) & (losersList.size() == 2)) {
			TextDisplay.showText(">> Equal cards! Next round.");

		} else {

			Collections.reverse(losersList);

			for (Player p : losersList) {
				p.loseQuarter();
				TextDisplay.showText(p.getName() + " Loses a quarter!");

				if (p.getNumOfQuarters() == 0) {
					System.out.println();
					TextDisplay.showText(p.getName() + " Is out of the game!");
					playersList_Out.add(p);
					playersList_In.remove(p);

				}
				p.setLoserStatus();
			}
		}

	}

	private void buildPlayers() {

		Player player = new HumanPlayer("MAN");
		player.addObserver(this);
		playersList_In.add(player);

		for (int i = 1; i < num_Of_Players; i++) {
			player = new ComputerPlayer((String) "AI" + i);
			player.addObserver(this);
			playersList_In.add(player);
		}
	}

	public void start() throws InterruptedException {

		Scanner in = new Scanner(System.in);
		TextDisplay.showText(">> How many total players 2 - 12?");
		do {

			num_Of_Players = in.nextInt();

			if (num_Of_Players < 2 || num_Of_Players > 12) {
				TextDisplay.showText(">> Please enter a number between 2 and 12");
			}

		} while (num_Of_Players < 2 || num_Of_Players > 12);

		buildPlayers();
		deck.buildNewDeck();
		deck.shuffleDeck();
		int round = 1;
		while (playersList_In.size() > 1) {

			try {
				startRound(round++);
				losersList.clear();
				moveDealer();

			} catch (IOException e) {
				e.printStackTrace();
			}
			pauseProg();
		}

		in.nextLine();

		String newGame = "";
		do {
			TextDisplay.showText(">> Would you like to play another game (y/n)?");

			newGame = in.nextLine();

		} while (!"y".equalsIgnoreCase(newGame) && !"n".equalsIgnoreCase(newGame));

		if (!"y".equalsIgnoreCase(newGame)) {
			playAgain = false;
			in.close();
		} else {
			playersList_In.clear();
			playersList_Out.clear();
			losersList.clear();
			in.reset();
		}

	}

	public static boolean playAgain() {

		return playAgain;

	}

	@Override
	public void update(Observable arg0, Object arg1) {

		String action = "";

		if (arg1 != null) {
			action = arg1.toString();
		}

		if ("t".equalsIgnoreCase(action)) {
			try {
				makeTrade();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if ("k".equalsIgnoreCase(action)) {
			keepCard();
		}

	}

	private void makeTrade() throws InterruptedException {

		if (currentPlayer < playersList_In.size() - 1) {

			TextDisplay
			        .showText(">> " + playersList_In.get(currentPlayer).getName()
			                + " is trading cards with " + playersList_In
			                        .get(currentPlayer + 1).getName());

			if (playersList_In.get(currentPlayer + 1).getCard().contains("King")) {
				TextDisplay.showText(
				        ">> " + playersList_In.get(currentPlayer + 1).getName()
				                + " shows a King to stop the trade!");
				keepCard();

			} else {
				Card tempCardNew = playersList_In.get(currentPlayer + 1)
				        .giveCard();
				Card tempCardOld = playersList_In.get(currentPlayer).giveCard();
				playersList_In.get(currentPlayer + 1).takeCard(tempCardOld);
				playersList_In.get(currentPlayer).takeCard(tempCardNew);

				if (playersList_In.get(currentPlayer).isHuman()
				        || playersList_In.get(currentPlayer).isHuman()) {
					TextDisplay.showText(">> Your new card: "
					        + playersList_In.get(currentPlayer).getCard());

				} else if (playersList_In.get(currentPlayer + 1).isHuman()) {

					TextDisplay.showText(">> Your new card: "
					        + playersList_In.get(currentPlayer + 1).getCard());
				}

			}
		} else {

			TextDisplay.showText(">> "
			        + playersList_In.get(currentPlayer).getName()
			        + " is trading in their card for a card off the deck.");
			TextDisplay
			        .showText(">> " + playersList_In.get(currentPlayer).getName()
			                + " flips their card to show a "
			                + playersList_In.get(currentPlayer).getCard());
			TextDisplay.showText(">> Dealer takes top card off deck.");

			playersList_In.get(currentPlayer).takeCard(deck.dealCard());
			TextDisplay.showText(">> Top card is a  "
			        + playersList_In.get(currentPlayer).getCard());

		}

	}

	private void keepCard() {

		try {
			TextDisplay.showText(">> "
			        + playersList_In.get(currentPlayer).getName() + ": I'm good!");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public static void pauseProg() {

		System.out.println("Press enter to continue...");
		Scanner keyboard = new Scanner(System.in);
		keyboard.nextLine();
	}

}
