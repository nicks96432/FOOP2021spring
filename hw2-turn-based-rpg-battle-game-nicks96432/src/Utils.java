import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.stream.IntStream;

public class Utils {
	/**
	 * 公差是1的等差數列生成器
	 * 
	 * @param startInclusive 首項
	 * @param endExclusive   末項 + 1
	 * @return 首項是startInclusive，末項是endExclusive - 1，公差是1的等差數列
	 */
	public static final List<Integer> generateIntegers(int startInclusive, int endExclusive) {
		return IntStream.range(startInclusive, endExclusive).boxed().collect(toList());
	}

	/**
	 * 印出所有可選的技能
	 * 
	 * @param prefix  前面先印的一段
	 * @param options 選項
	 */
	public static final void printAvailableSkills(String prefix, List<Skill> options) {
		System.out.printf(prefix);
		for (int i = 0; i < options.size(); ++i)
			System.out.printf(" (%d) %s", i, options.get(i).getName());
		System.out.println();
	}

	/**
	 * 印出所有可選的目標
	 * 
	 * @param prefix  前面先印的一段
	 * @param options 選項
	 */
	public static final void printAvailableTargets(String prefix, List<Unit> options) {
		System.out.printf(prefix);
		for (int i = 0; i < options.size(); ++i)
			System.out.printf(" (%d) [%d]%s", i, options.get(i).getBelongTroop().getID(), options.get(i).getName());
		System.out.println();
	}

	/**
	 * 印出玩家清單
	 * 
	 * @param prefix 前面要先印的東西
	 * @param units  玩家清單
	 */
	public static final void printUnits(String prefix, List<Unit> units) {
		System.out.printf(prefix);
		for (int i = 0; i < units.size(); ++i)
			System.out.printf(" [%d]%s%c", units.get(i).getBelongTroop().getID(), units.get(i).getName(),
					i == units.size() - 1 ? '.' : ',');
		System.out.println();
	}

	/**
	 * 印出誰被誰打
	 * 
	 * @param by     打的人
	 * @param damage 造成的傷害
	 * @param to     被打的人
	 */
	public static final void printDealDamage(Unit by, int damage, Unit to) {
		System.out.println(String.format("[%d]%s causes %d damage to [%d]%s.", by.getBelongTroop().getID(),
				by.getName(), damage, to.getBelongTroop().getID(), to.getName()));
	}

	/**
	 * 印出誰死了
	 * 
	 * @param who 誰死了
	 */
	public static final void printDead(Unit who) {
		System.out.println(String.format("[%d]%s dies.", who.getBelongTroop().getID(), who.getName()));
	}
}
