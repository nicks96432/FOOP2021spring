import java.util.ArrayList;
import java.util.List;

import utils.Inputs;

public class Action {
	private final Unit unit;
	private final List<Unit> allyList;
	private final List<Unit> enemyList;
	private final List<Unit> unitList;

	/**
	 * 建立{@code unit}的回合
	 * 
	 * @param unit   輪到誰的回合
	 * @param troops 所有的troop
	 */
	public Action(Unit unit, List<Troop> troops) {
		allyList = new ArrayList<>();
		enemyList = new ArrayList<>();
		unitList = new ArrayList<>();
		this.unit = unit;
		for (Troop troop : troops)
			for (Unit u : troop.getUnits()) {
				if (u.getHP() <= 0 || u.equals(unit))
					continue;
				if (unit.getBelongTroop().getID() == u.getBelongTroop().getID())
					allyList.add(u);
				else
					enemyList.add(u);
				unitList.add(u);

			}
	}

	/**
	 * 輪到{@code unit}的回合
	 * 
	 * @throws UnknownError 技能的目標類型不明或是無法選擇目標
	 */
	public void perform() {
		// 讀技能 (S1)
		Skill action;
		action = selectAction();
		// 讀目標 (S2)
		List<Unit> chosenUnits = findAvailableTargets(action);
		// 開技能 (S3)
		action.perform(unit, chosenUnits);
	}

	/**
	 * 選擇技能
	 * 
	 * @param allSkillMap 所有技能
	 * @return 決定的結果
	 */
	public Skill selectAction() {
		int action;
		Utils.printAvailableSkills("Select an action:", unit.getSkills());
		if (unit.isPlayer())
			while (true) {
				action = Inputs.nextIntRange(0, unit.getSkills().size());
				if (unit.getSkills().get(action).getMPCost() > unit.getMP()) {
					System.out.println("You can't perform the action: insufficient MP.");
					Utils.printAvailableSkills("Select an action:", unit.getSkills());
					continue;
				}
				break;
			}
		else {
			List<Integer> availableChoice = new ArrayList<>();
			for (int i = 0; i < unit.getSkills().size(); ++i)
				if (unit.getSkills().get(i).getMPCost() <= unit.getMP())
					availableChoice.add(i);
			action = unit.selectAction(availableChoice);
		}
		return unit.getSkills().get(action);
	}

	/**
	 * 尋找目標
	 * 
	 * @param action 開的技能
	 * @return 所有可選的目標
	 */
	public List<Unit> findAvailableTargets(Skill action) {
		List<Unit> chosenUnits;
		switch (action.getTargetType()) {
			case All:
				chosenUnits = unitList;
				break;
			case Ally:
				chosenUnits = allyList;
				break;
			case Enemy:
				chosenUnits = enemyList;
				break;
			default:
				chosenUnits = new ArrayList<>();
				break;
		}
		if (action.getTargetType() == TargetType.Self)
			chosenUnits.add(unit);
		if (action.getMaxTargetCount() > 0 && action.getTargetType() != TargetType.Self
				&& action.getMaxTargetCount() < chosenUnits.size()) {
			List<Integer> integers;
			if (unit.isPlayer())
				integers = selectTarget(chosenUnits, action.getMaxTargetCount());
			else
				integers = unit.selectTarget(chosenUnits.size(), action.getMaxTargetCount());
			List<Unit> tmpList = new ArrayList<>();
			for (int i : integers)
				tmpList.add(chosenUnits.get(i));
			chosenUnits = tmpList;
		}
		return chosenUnits;
	}

	/**
	 * 從{@code availableTargets}中選{@code targetCount}個目標
	 * 
	 * @param targetCount      要選幾個目標
	 * @param availableTargets 所有可選的目標
	 */
	public List<Integer> selectTarget(List<Unit> availableTargets, int targetCount) {
		// Perform the action on the target
		List<Integer> integers = new ArrayList<>();
		Utils.printAvailableTargets(String.format("Select %d targets:", targetCount), availableTargets);
		while (true) {
			String str = Inputs.in.nextLine();
			String[] split = str.split(",\\s");
			boolean check = true;
			for (String s : split) {
				try {
					integers.add(Integer.parseInt(s));
				} catch (NumberFormatException | IndexOutOfBoundsException e) {
					check = false;
					break;
				}
			}
			if (!check || integers.size() != targetCount) {
				integers.clear();
				continue;
			}
			break;
		}
		return integers;
	}
}