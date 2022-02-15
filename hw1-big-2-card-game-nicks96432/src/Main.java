import main.Game;

/**
 * The big-2 game will be started from the Main class.
 */

public class Main {
	static final int TOTALCARDS = 52;
	static final int TOTALPLAYERS = 4;

	public static void main(String[] args) {
		Game game = new Game(TOTALCARDS, TOTALPLAYERS);
		game.start();
	}
}