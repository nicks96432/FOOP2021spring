import java.util.List;

public class SelfExplosion extends Skill {

	public static final String name = SelfExplosion.class.getSimpleName();
	private static final int basicDamage = 150;

	public SelfExplosion() {
		super(TargetType.All, -1, 200, name);
	}

	public static int getBasicdamage() {
		return basicDamage;
	}

	@Override
	public void specificMove(Unit by, List<Unit> targetUnits) {
		for (Unit unit : targetUnits) {
			if (unit.equals(by))
				continue;
			int damage = getBasicdamage();
			if (by.getState() == StateType.Cheerup)
				damage += 50;
			Utils.printDealDamage(by, damage, unit);
			unit.takeDamage(damage);
		}
		by.takeDamage(by.getHP());
	}
}
