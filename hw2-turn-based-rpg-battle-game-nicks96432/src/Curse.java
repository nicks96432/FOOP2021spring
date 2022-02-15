import java.util.List;

public class Curse extends Skill {

	public static final String name = Curse.class.getSimpleName();

	public Curse() {
		super(TargetType.Enemy, 1, 100, name);
	}

	@Override
	public void specificMove(Unit by, List<Unit> targetUnits) {
		targetUnits.get(0).getCurseList().add(by);
	}

}
