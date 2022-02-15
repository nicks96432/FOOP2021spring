import java.util.List;

public class Waterball extends Skill {

	public static final String name = Waterball.class.getSimpleName();
	private static final int basicDamge = 120;

	public Waterball() {
		super(TargetType.Enemy, 1, 50, name);
	}

	public static int getBasicdamge() {
		return basicDamge;
	}

	@Override
	public void specificMove(Unit by, List<Unit> targetUnits) {
		Unit targetUnit = targetUnits.get(0);
		int damage = getBasicdamge();
		if (by.getState() == StateType.Cheerup)
			damage += Cheerup.getAddDamage();
		Utils.printDealDamage(by, damage, targetUnit);
		targetUnit.takeDamage(damage);
	}

}
