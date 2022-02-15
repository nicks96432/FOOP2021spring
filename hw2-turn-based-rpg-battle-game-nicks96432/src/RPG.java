import static java.lang.Integer.parseInt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import utils.Inputs;

public class RPG {
	private final List<Troop> troops;
	private final HashMap<String, Skill> allSkillMap;
	private Unit player;

	public RPG(HashMap<String, Skill> allSkillMap, int countTroop) {
		this.allSkillMap = allSkillMap;
		troops = new ArrayList<>();
		for (int i = 1; i <= countTroop; ++i)
			troops.add(new Troop(i));
	}

	public void load() {
		for (int i = 1; i <= troops.size() && Inputs.in.hasNextLine(); ++i) {
			String nextline = Inputs.in.nextLine();
			if (nextline.equals(String.format("#START-TROOP-%d", i))) {
				while (Inputs.in.hasNextLine()) {
					nextline = Inputs.in.nextLine();
					if (nextline.equals(String.format("#END-TROOP-%d", i)))
						break;
					String[] split = nextline.split("\\s+");
					List<Skill> skillList = new ArrayList<>();
					Skill skill = allSkillMap.get(BasicAttack.name);
					if (skill == null)
						continue;
					skillList.add(skill);
					boolean check = true;
					for (int j = 4; j < split.length; ++j) {
						skill = allSkillMap.get(split[j]);
						if (skill == null) {
							check = false;
							break;
						}
						skillList.add(skill);
					}
					if (!check)
						continue;
					Unit newUnit = new Unit(parseInt(split[1]), parseInt(split[2]), Integer.parseInt(split[3]),
							troops.get(i - 1), split[0], skillList, troops.get(0).getUnits().isEmpty());
					troops.get(i - 1).getUnits().add(newUnit);
				}
			}
		}
		player = troops.get(0).getUnits().get(0);
	}

	public void debug() {
		for (Troop troop : troops) {
			for (Unit unit : troop.getUnits())
				System.out.printf("[%d]%s %d %d %d %s%n", unit.getBelongTroop().getID(), unit.getName(), unit.getHP(),
						unit.getMP(), unit.getSTR(), unit.getState().name());
			System.out.println();
		}
	}

	public void start() {
		boolean gameover = false;
		while (!gameover) {
			for (Troop troop : troops) {
				for (int i = 0; i < troop.getUnits().size(); ++i) {
					Unit unit = troop.getUnits().get(i);
					// Skip dead units
					if (unit.getHP() <= 0)
						continue;
					System.out.printf("[%d]%s's turn (HP: %d, MP: %d, STR: %d, State: %s).%n",
							unit.getBelongTroop().getID(), unit.getName(), unit.getHP(), unit.getMP(), unit.getSTR(),
							unit.getState().name());
					// System.out.println("Debug:");
					// debug();
					StateType state = unit.getState();
					if (state == StateType.Poisoned) {
						int nowHP = unit.getHP();
						unit.setHP(nowHP - Poison.damage);
						if (unit.getHP() <= 0) {
							Utils.printDead(unit);
							continue;
						}
					}
					if (unit.getState() != StateType.Petrochemical) {
						Action action = new Action(unit, troops);
						action.perform();
					}
					// Check game over condition
					if (player.getHP() <= 0) {
						gameover = true;
						break;
					}
					int annihilated = 0;
					for (Troop t : troops)
						if (t.getDeadCount() == t.getUnits().size())
							++annihilated;
					if (annihilated >= troops.size() - 1) {
						gameover = true;
						break;
					}
				}
				if (gameover)
					break;
			}
			for (Troop troop : troops)
				for (Unit unit : troop.getUnits()) {
					int remainTime = unit.getStateRemainTime();
					unit.setStateRemainTime(remainTime - 1);
					if (unit.getStateRemainTime() == 0)
						unit.setState(StateType.Normal);
				}
		}
		// Determine whether the player wins
		if (player.getHP() > 0)
			System.out.println("You win.");
		else
			System.out.println("You lose.");
	}

}
