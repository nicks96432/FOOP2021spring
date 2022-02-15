import java.util.List;

public class OnePunch extends Skill {

	public static final String name = OnePunch.class.getSimpleName();

	public OnePunch() {
		super(TargetType.Enemy, 1, 180, name);
	}

	@Override
	public int getMPCost() {
		tw.waterball.foop.hw2.provided.OnePunch onePunch = new tw.waterball.foop.hw2.provided.OnePunch();
		return onePunch.getMpCost();
	}

	@Override
	public void specificMove(Unit by, List<Unit> targetUnits) {
		// 先揍一下看傷害再真的扣
		Unit target = targetUnits.get(0);
		int nowHP = target.getHP();
		tw.waterball.foop.hw2.provided.OnePunch onePunch = new tw.waterball.foop.hw2.provided.OnePunch();
		onePunch.perform(target);
		int damage = nowHP - target.getHP();
		target.setHP(nowHP);
		if (by.getState() == StateType.Cheerup)
			damage += Cheerup.getAddDamage();
		Utils.printDealDamage(by, damage, target);
		target.takeDamage(damage);
	}
}
