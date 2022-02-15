import java.util.List;

public class Fireball extends Skill {

	public static final String name = Fireball.class.getSimpleName();
	public static final int basicDamage = 50;

	public Fireball() {
		super(TargetType.Enemy, -1, 50, name);
	}

	public static int getBasicdamage() {
		return basicDamage;
	}

	@Override
	public void specificMove(Unit by, List<Unit> targetUnits) {
		for (Unit unit : targetUnits) {
			int totalDamage = getBasicdamage();
			if (by.getState() == StateType.Cheerup)
				totalDamage += Cheerup.getAddDamage();
			Utils.printDealDamage(by, totalDamage, unit);
			unit.takeDamage(totalDamage);
		}
	}
}
