package main;

import java.util.ArrayList;
import java.util.List;

import main.card.Card;
import main.combo.*;

public class Try {
	boolean firstPlayer;
	boolean firstRound;
	Player player;
	Combo topCombo;

	public Try(Player pl, boolean firstplayer, Combo combo, boolean first) {
		player = pl;
		firstRound = first;
		firstPlayer = firstplayer;
		topCombo = combo;
	}

	public Combo tryAndError() {
		List<Integer> actionArray;
		List<Card> comboCards = new ArrayList<>();
		while (!(actionArray = Data.readAction()).isEmpty()) {
			player.printCards();
			if (actionArray.get(0).intValue() == -1 && actionArray.size() == 1) {
				if (firstPlayer) {
					System.out.println("You can't pass in the new round.");
					continue;
				}
				return Combo.pass;
			}
			boolean rangeCheck = true;
			for (int i : actionArray) {
				if (i < 0 || i >= player.handCards.size()) {
					rangeCheck = false;
					break;
				}
				comboCards.add(player.handCards.get(i));
			}
			if (!rangeCheck) {
				System.out.println("Invalid play, please try again.");
				continue;

			}
			Combo combo = new Combo(player, actionArray);

			for (ComboType type : ComboType.values())
				if (type != ComboType.PASS && ComboValidator.validate(type, combo))
					break;
			if (!combo.validated || firstRound && !combo.containsC3()) {
				System.out.println("Invalid play, please try again.");
				continue;
			}
			if (topCombo != null && topCombo != Combo.pass
					&& (combo.type != topCombo.type || ComboComparator.compare(combo, topCombo) != 1)) {
				System.out.println("Invalid play, please try again.");
				continue;
			}
			return combo;
		}
		throw new UnknownError();
	}
}
