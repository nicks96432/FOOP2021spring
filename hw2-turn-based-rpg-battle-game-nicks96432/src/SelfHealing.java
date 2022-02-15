import java.util.List;

public class SelfHealing extends Skill {

	public static final String name = SelfHealing.class.getSimpleName();
	private static final int amount = 150;

	public SelfHealing() {
		super(TargetType.Self, 1, 50, name);
	}

	public static int getAmount() {
		return amount;
	}

	@Override
	public void specificMove(Unit by, List<Unit> targetUnits) {
		int nowHP = by.getHP();
		by.setHP(nowHP + amount);
	}

}
