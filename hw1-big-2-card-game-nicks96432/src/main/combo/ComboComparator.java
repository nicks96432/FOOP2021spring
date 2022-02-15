package main.combo;

public class ComboComparator {
	public static int compare(Combo combo1, Combo combo2) {
		if (combo1.type != combo2.type)
			return Integer.MIN_VALUE;
		switch (combo1.type) {
		case SINGLE:
			return singleCompare(combo1, combo2);
		case PAIR:
			return pairCompare(combo1, combo2);
		case STRAIGHT:
			return straightCompare(combo1, combo2);
		case FULLHOUSE:
			return fullhouseCompare(combo1, combo2);
		case PASS:
			throw new UnsupportedOperationException();
		default:
			throw new UnknownError("this shouldn't happen...");

		}
	}

	public static int singleCompare(Combo combo1, Combo combo2) {
		if (!combo1.validated && !ComboValidator.singleValidate(combo1)
				|| !combo2.validated && !ComboValidator.singleValidate(combo2))
			return Integer.MIN_VALUE;
		return combo1.comboCards.get(0).compareTo(combo2.comboCards.get(0));
	}

	public static int pairCompare(Combo combo1, Combo combo2) {
		if (!combo1.validated && !ComboValidator.pairValidate(combo1)
				|| !combo2.validated && !ComboValidator.pairValidate(combo2))
			return Integer.MIN_VALUE;
		return combo1.comboCards.get(1).compareTo(combo2.comboCards.get(1));
	}

	public static int straightCompare(Combo combo1, Combo combo2) {
		if (!combo1.validated && !ComboValidator.straightValidate(combo1)
				|| !combo2.validated && !ComboValidator.straightValidate(combo2))
			return Integer.MIN_VALUE;
		return combo1.comboCards.get(4).compareTo(combo2.comboCards.get(4));
	}

	public static int fullhouseCompare(Combo combo1, Combo combo2) {
		if (!combo1.validated && !ComboValidator.fullhouseValidate(combo1)
				|| !combo2.validated && !ComboValidator.fullhouseValidate(combo2))
			return Integer.MIN_VALUE;
		int rank1 = combo1.tripleFirst ? combo1.comboCards.get(0).rank.ordinal()
				: combo1.comboCards.get(4).rank.ordinal();
		int rank2 = combo2.tripleFirst ? combo2.comboCards.get(0).rank.ordinal()
				: combo2.comboCards.get(4).rank.ordinal();
		if (rank1 > rank2)
			return 1;
		else if (rank1 < rank2)
			return -1;
		boolean[] tripleSuit1 = new boolean[4];
		boolean[] tripleSuit2 = new boolean[4];
		if (combo1.tripleFirst)
			for (int i = 0; i < 3; ++i)
				tripleSuit1[combo1.comboCards.get(i).suit.ordinal()] = true;
		else
			for (int i = 2; i < 5; ++i)
				tripleSuit1[combo1.comboCards.get(i).suit.ordinal()] = true;
		if (combo2.tripleFirst)
			for (int i = 0; i < 3; ++i)
				tripleSuit2[combo2.comboCards.get(i).suit.ordinal()] = true;
		else
			for (int i = 2; i < 5; ++i)
				tripleSuit2[combo2.comboCards.get(i).suit.ordinal()] = true;
		for (int i = 3; i >= 0; --i) {
			if (tripleSuit1[i] == true && tripleSuit2[i] == false)
				return 1;
			else if (tripleSuit1[i] == false && tripleSuit2[i] == true)
				return -1;
		}
		return 0;
	}

}
