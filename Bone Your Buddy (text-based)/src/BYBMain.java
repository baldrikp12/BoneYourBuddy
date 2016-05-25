
public class BYBMain {

	public static void main(String[] args) {

		try {
			Game game = new Game();

			do {
				game.start();
			} while (Game.playAgain());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
