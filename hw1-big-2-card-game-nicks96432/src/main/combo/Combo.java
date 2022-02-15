package main.combo;

import java.util.List;
import java.util.ArrayList;

import main.Player;
import main.card.Card;
import main.card.RankType;
import main.card.SuitType;

public class Combo {
	public boolean validated, tripleFirst;
	public ComboType type;
	public List<Card> comboCards;
	public List<Integer> cardsIndex;

	public Combo() {
		comboCards = new ArrayList<>();
		cardsIndex = new ArrayList<>();
	}

	public Combo(List<Card> comboCards) {
		this.comboCards = comboCards;
		cardsIndex = new ArrayList<>();
	}

	public Combo(ComboType type) {
		this.type = type;
		comboCards = new ArrayList<>();
		cardsIndex = new ArrayList<>();
	}

	public Combo(Player player, List<Integer> cardsIndex) {
		if (cardsIndex.get(0).intValue() == -1) {
			type = ComboType.PASS;
			return;
		}
		this.cardsIndex = cardsIndex;
		comboCards = new ArrayList<>();
		for (int i : cardsIndex)
			comboCards.add(player.handCards.get(i));
	}

	public static final Combo pass = new Combo(ComboType.PASS);

	public final boolean containsC3() {
		Card c3 = new Card(SuitType.CLUBS, RankType.RANK3);
		for (Card card : comboCards)
			if (card.compareTo(c3) == 0)
				return true;
		return false;
	}

	@Override
	public final String toString() {
		StringBuilder cards = new StringBuilder();
		for (Card card : comboCards) {
			String cardString = card.toString();
			cards.append(cardString).append(" ");
		}
		return cards.toString().stripTrailing();
	}
}
