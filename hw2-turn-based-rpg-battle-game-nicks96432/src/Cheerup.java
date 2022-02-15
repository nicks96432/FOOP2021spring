import java.util.List;

public class Cheerup extends Skill {

	public static final String name = Cheerup.class.getSimpleName();
	private static final int addDamage = 50;

	public Cheerup() {
		super(TargetType.Ally, 3, 100, name);
	}

	/**
	 * 加多少傷害的getter
	 * 
	 * @return 加多少傷害
	 */
	public static int getAddDamage() {
		return addDamage;
	}

	@Override
	public void specificMove(Unit by, List<Unit> targetUnits) {
		for (Unit unit : targetUnits) {
			unit.setState(StateType.Cheerup);
			unit.setStateRemainTime(3);
		}
	}
}
