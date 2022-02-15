import java.util.List;

public class BasicAttack extends Skill {

	public static final String name = "Basic Attack";

	public BasicAttack() {
		super(TargetType.Enemy, 1, 0, name);
	}

	@Override
	public void specificMove(Unit by, List<Unit> targetUnits) {
		Unit target = targetUnits.get(0);
		int damage = by.getSTR();
		if (by.getState() == StateType.Cheerup)
			damage += Cheerup.getAddDamage();
		// System.out.println(by.getState().name());
		// System.out.println(damage);
		Utils.printDealDamage(by, damage, target);
		target.takeDamage(damage);
	}

}
