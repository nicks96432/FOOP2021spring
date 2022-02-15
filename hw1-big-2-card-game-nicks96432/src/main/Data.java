package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import main.card.Card;
import main.card.RankType;
import main.card.SuitType;

public class Data {
	private final int TOTALCARDS, TOTALPLAYERS;
	private List<Card> deck;
	public List<Player> players;
	static private Scanner scanner = new Scanner(System.in);

	public Data(int totalCard, int totalPlayer) {
		TOTALCARDS = totalCard;
		TOTALPLAYERS = totalPlayer;
		deck = new ArrayList<>();
		players = new ArrayList<>();
	}

	public void loadData() {
		for (int i = 0; i < TOTALCARDS; ++i) {
			final String cardString = scanner.next();
			if (!cardString.matches("^[SDHC]\\[([1-9JQKA]|10)\\]$")) {
				scanner.close();
				throw new NumberFormatException("Invalid Deck format.");
			}
			String suitString = "";
			switch (cardString.substring(0, 1)) {
			case "S":
				suitString = "SPADES";
				break;
			case "D":
				suitString = "DIAMONDS";
				break;
			case "H":
				suitString = "HEARTS";
				break;
			case "C":
				suitString = "CLUBS";
				break;
			}
			Card card = new Card(SuitType.valueOf(suitString), RankType
					.valueOf(cardString.matches("^.\\[10\\]$") ? "RANK10" : "RANK" + cardString.substring(2, 3)));
			deck.add(card);
		}
		scanner.nextLine();
		for (int i = 0; i < TOTALPLAYERS; ++i) {
			String name = scanner.nextLine();
			if (!name.matches("^[0-9a-zA-Z]+$")) {
				scanner.close();
				throw new IllegalArgumentException("Invalid player name.");
			}
			players.add(new Player(name, i));
		}
	}


	public int dealCards() {
		int startPlayer = 0;
		for (int i = deck.size() - 1, count = 0; i >= 0; --i, count = (count + 1) % TOTALPLAYERS) {
			Card card = deck.get(i);
			players.get(count).addCard(card);
			if (card.suit == SuitType.CLUBS && card.rank == RankType.RANK3)
				startPlayer = count;
		}
		for (int i = 0; i < TOTALPLAYERS; ++i)
			Collections.sort(players.get(i).handCards);
		return startPlayer;
	}

	static public List<Integer> readAction() {
		List<Integer> actionArray = new ArrayList<>();
		String nextLine;
		if (scanner.hasNextLine() && !(nextLine = scanner.nextLine()).isEmpty())
			actionArray = Arrays.stream(nextLine.split(" ")).mapToInt(Integer::parseInt).boxed()
					.collect(Collectors.toList());
		return actionArray;
	}

}