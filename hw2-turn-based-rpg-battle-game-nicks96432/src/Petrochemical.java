import java.util.List;

public class Petrochemical extends Skill {

	public static final String name = Petrochemical.class.getSimpleName();

	public Petrochemical() {
		super(TargetType.Enemy, 1, 100, name);
	}

	@Override
	public void specificMove(Unit by, List<Unit> targetUnits) {
		targetUnits.get(0).setState(StateType.Petrochemical);
		targetUnits.get(0).setStateRemainTime(3);
	}

}
