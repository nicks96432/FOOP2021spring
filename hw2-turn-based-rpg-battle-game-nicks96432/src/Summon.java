import java.util.Arrays;
import java.util.List;

public class Summon extends Skill {

	public static final String name = Summon.class.getSimpleName();
	private static final int summonedUnitHP = 100;
	private static final int summonedUnitMP = 0;
	private static final int summonedUnitSTR = 50;
	private static final String summonedUnitName = "Slime";
	private static final List<Skill> summonedUnitSkillList = Arrays.asList(new BasicAttack());

	public Summon() {
		super(TargetType.None, 0, 150, name);
	}

	@Override
	public void specificMove(Unit by, List<Unit> targetUnits) {
		Unit summonedUnit = new Unit(summonedUnitHP, summonedUnitMP, summonedUnitSTR, by.getBelongTroop(),
				summonedUnitName, summonedUnitSkillList, false);
		by.getBelongTroop().addUnit(summonedUnit);
	}

}
