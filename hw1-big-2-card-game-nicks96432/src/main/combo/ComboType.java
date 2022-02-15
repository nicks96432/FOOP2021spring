package main.combo;

public enum ComboType {
	SINGLE, PAIR, STRAIGHT, FULLHOUSE, PASS;

	@Override
	public String toString() {
		switch (this) {
		case FULLHOUSE:
			return "full house";
		case PAIR:
		case PASS:
		case SINGLE:
		case STRAIGHT:
			return this.name().toLowerCase();
		default:
			throw new UnknownError("this shouldn't happen...");

		}
	}
}
