package main.card;

public class Card implements Comparable<Card> {
	public SuitType suit;
	public RankType rank;

	public Card(SuitType suitType, RankType rankType) {
		this.suit = suitType;
		this.rank = rankType;
	}

	@Override
	public String toString() {
		return String.format("%c[%s]", suit.name().charAt(0), rank.name().replaceFirst("RANK", ""));
	}

	@Override
	public int compareTo(Card card) {
		if (rank.ordinal() > card.rank.ordinal())
			return 1;
		else if (rank == card.rank) {
			if (suit.ordinal() > card.suit.ordinal())
				return 1;
			else if (suit == card.suit)
				return 0;
		}
		return -1;
	}
}
