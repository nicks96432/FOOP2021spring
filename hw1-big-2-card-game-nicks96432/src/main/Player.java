package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import main.card.Card;

public class Player {
	public int id;
	public String name;
	public List<Card> handCards;

	public Player(String n, int id) {
		this.id = id;
		name = n;
		handCards = new ArrayList<>();
	}

	public void addCard(Card card) {
		handCards.add(card);
	}

	public void removeCardsIndex(List<Integer> index) {
		index.sort(Collections.reverseOrder());
		for (Integer i : index)
			handCards.remove((int) i);
	}

	public void printCards() {
		StringBuilder numbers = new StringBuilder();
		StringBuilder cards = new StringBuilder();
		for (int i = 0; i < handCards.size(); ++i) {
			String cardString = handCards.get(i).toString();
			numbers.append(String.format("%" + (-cardString.length()) + "s", i)).append(" ");
			cards.append(cardString).append(" ");
		}
		System.out.println(numbers.toString().stripTrailing());
		System.out.println(cards.toString().stripTrailing());

	}
}