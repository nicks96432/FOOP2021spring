package main.combo;

import java.util.Collections;

public class ComboValidator {
	public static boolean validate(ComboType type, Combo combo) {
		switch (type) {
		case SINGLE:
			return singleValidate(combo);
		case PAIR:
			return pairValidate(combo);
		case STRAIGHT:
			return straightValidate(combo);
		case FULLHOUSE:
			return fullhouseValidate(combo);
		case PASS:
			throw new UnsupportedOperationException();
		default:
			throw new UnknownError("this shouldn't happen...");
		}
	}

	protected static boolean singleValidate(Combo combo) {
		if (combo.comboCards.size() == 1) {
			combo.validated = true;
			combo.type = ComboType.SINGLE;
			return true;
		}
		combo.validated = false;
		return false;
	}

	protected static boolean pairValidate(Combo combo) {
		if (combo.comboCards.size() != 2) {
			combo.validated = false;
			return false;
		}
		Collections.sort(combo.comboCards);
		if (combo.comboCards.get(0).rank == combo.comboCards.get(1).rank) {
			if (combo.comboCards.get(0).suit == combo.comboCards.get(1).suit) {
				combo.validated = false;
				return false;
			}
			combo.type = ComboType.PAIR;
			combo.validated = true;
			return true;
		}
		combo.validated = false;
		return false;
	}

	protected static boolean straightValidate(Combo combo) {
		if (combo.comboCards.size() != 5) {
			combo.validated = false;
			return false;
		}
		Collections.sort(combo.comboCards);
		for (int i = 0; i < 4; ++i) {
			int diff = combo.comboCards.get(i + 1).rank.ordinal() - combo.comboCards.get(i).rank.ordinal();
			if (diff != 1 && diff != 9) {
				combo.validated = false;
				return false;
			}
		}
		combo.type = ComboType.STRAIGHT;
		combo.validated = true;
		return true;
	}

	protected static boolean fullhouseValidate(Combo combo) {
		combo.tripleFirst = false;
		if (combo.comboCards.size() != 5) {
			combo.validated = false;
			return false;
		}
		Collections.sort(combo.comboCards);
		for (int i = 0; i < 4; ++i)
			if (combo.comboCards.get(i).compareTo(combo.comboCards.get(i + 1)) == 0) {
				combo.validated = false;
				return false;
			}
		if (combo.comboCards.get(0).rank == combo.comboCards.get(1).rank
				&& combo.comboCards.get(1).rank == combo.comboCards.get(2).rank) {
			combo.validated = combo.comboCards.get(2).rank != combo.comboCards.get(3).rank
					&& combo.comboCards.get(3).rank == combo.comboCards.get(4).rank;
			if (combo.validated) {
				combo.tripleFirst = true;
				combo.type = ComboType.FULLHOUSE;
			}
			return combo.validated;
		} else if (combo.comboCards.get(0).rank == combo.comboCards.get(1).rank
				&& combo.comboCards.get(1).rank != combo.comboCards.get(2).rank) {
			combo.validated = combo.comboCards.get(2).rank == combo.comboCards.get(3).rank
					&& combo.comboCards.get(3).rank == combo.comboCards.get(4).rank;
			if (combo.validated)
				combo.type = ComboType.FULLHOUSE;
			return combo.validated;
		}
		combo.validated = false;
		return false;
	}

}
