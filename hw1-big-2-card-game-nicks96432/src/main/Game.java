package main;

import main.combo.Combo;

public class Game {
	Data data;
	private final int TOTALCARDS;
	private final int TOTALPLAYERS;

	public Game(int countCards, int countPlayers) {
		TOTALCARDS = countCards;
		TOTALPLAYERS = countPlayers;
		data = new Data(TOTALCARDS, TOTALPLAYERS);
	}

	public final void start() {
		data.loadData();
		int nowPlayer = data.dealCards();
		boolean firstRound = false;
		for (;;) {
			int countPass = 0;
			Combo topCombo = null;
			System.out.println("New round begins.");
			boolean firstPlayer = true, prevPass = false;
			for (;; nowPlayer = (nowPlayer + 1) % TOTALPLAYERS) {
				String nowPlayerName = data.players.get(nowPlayer).name;
				System.out.printf("Next turn: %s\n", nowPlayerName);
				Try t = new Try(data.players.get(nowPlayer), firstPlayer, topCombo, firstRound);
				firstPlayer = false;
				Combo result = t.tryAndError();
				if (result.equals(Combo.pass)) {
					System.out.printf("Player %s passes.\n", nowPlayerName);
					if (prevPass) {
						++countPass;
						if (countPass == 3) {
							nowPlayer = (nowPlayer + 1) % TOTALPLAYERS;
							break;
						}
					} else
						countPass = 1;
					prevPass = true;
					continue;
				} else {
					topCombo = result;
					prevPass = false;
				}
				System.out.printf("Player %s plays a %s %s.\n", data.players.get(nowPlayer).name,
						result.type.toString(), result.toString());
				data.players.get(nowPlayer).removeCardsIndex(result.cardsIndex);
				if (data.players.get(nowPlayer).handCards.isEmpty()) {
					System.out.printf("Game over, the winner is %s.\n", data.players.get(nowPlayer).name);
					return;
				}
				if (countPass == TOTALPLAYERS - 1)
					break;
			}
			firstRound = false;
		}
	}
}
