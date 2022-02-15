import java.util.List;

public class Poison extends Skill {

	public static final String name = Poison.class.getSimpleName();
	public static final int damage = 30;

	public Poison() {
		super(TargetType.Enemy, 1, 80, name);
	}

	@Override
	public void specificMove(Unit by, List<Unit> targetUnits) {
		targetUnits.get(0).setState(StateType.Poisoned);
		targetUnits.get(0).setStateRemainTime(3);
	}
}
