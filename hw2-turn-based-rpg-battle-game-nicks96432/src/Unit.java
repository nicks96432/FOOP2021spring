import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import tw.waterball.foop.hw2.provided.AI;
import tw.waterball.foop.hw2.provided.Target;

public class Unit extends AI implements Target {

	private int HP, MP, STR, stateRemainTime;
	private final int defaultSTR;
	private final String name;
	private final List<Skill> skills;
	private final boolean isPlayer;
	private Set<Unit> curseList;
	private StateType state;
	private Troop belongTroop;

	public Unit(int HP, int MP, int STR, Troop belongTroop, String name, List<Skill> skills, boolean isPlayer) {
		super();
		this.HP = HP;
		this.MP = MP;
		this.STR = STR;
		this.defaultSTR = STR;
		this.belongTroop = belongTroop;
		this.name = name;
		this.skills = new ArrayList<>();
		this.skills.addAll(skills);
		this.isPlayer = isPlayer;
		this.curseList = new HashSet<>();
		this.state = StateType.Normal;
		this.stateRemainTime = 0;
	}

	/**
	 * HP的getter
	 * 
	 * @return 這個{@code Unit}的HP
	 */
	public int getHP() {
		return HP;
	}

	/**
	 * HP的setter
	 * 
	 * @param HP 要設定的HP
	 */
	public void setHP(int HP) {
		this.HP = HP;
	}

	/**
	 * MP的getter
	 * 
	 * @return 這個{@code Unit}的MP
	 */
	public int getMP() {
		return MP;
	}

	/**
	 * MP的setter
	 * 
	 * @param MP 要設定的MP
	 */
	public void setMP(int MP) {
		this.MP = MP;
	}

	/**
	 * STR的getter
	 * 
	 * @return 這個{@code Unit}的STR
	 */
	public int getSTR() {
		return STR;
	}

	/**
	 * STR的setter
	 * 
	 * @param STR 要設定的STR
	 */
	public void setSTR(int STR) {
		this.STR = STR;
	}

	/**
	 * 初始STR的getter
	 * 
	 * @return 這個{@code Unit}的初始STR
	 */
	public int getDefaultSTR() {
		return defaultSTR;
	}

	/**
	 * 屬於哪個{@code Troop}的getter
	 * 
	 * @return 這個{@code Unit}屬於哪個{@code Troop}
	 */
	public Troop getBelongTroop() {
		return belongTroop;
	}

	/**
	 * 屬於哪個{@code Troop}的setter
	 * 
	 * @param belongTroop 要設定這個{@code Unit}屬於哪個{@code Troop}
	 */
	public void setBelongTroop(Troop belongTroop) {
		this.belongTroop = belongTroop;
	}

	/**
	 * 異常狀態的getter
	 * 
	 * @return 這個{@code Unit}的異常狀態
	 */
	public StateType getState() {
		return state;
	}

	/**
	 * 異常狀態的setter
	 * 
	 * @param state 要設定的異常狀態
	 */
	public void setState(StateType state) {
		this.state = state;
	}

	/**
	 * 異常狀態持續時間的getter
	 * 
	 * @return 這個{@code Unit}的異常狀態持續時間
	 */
	public int getStateRemainTime() {
		return stateRemainTime;
	}

	/**
	 * 異常狀態持續時間的setter
	 * 
	 * @param stateRemainTime 要設定的持續時間
	 */
	public void setStateRemainTime(int stateRemainTime) {
		this.stateRemainTime = stateRemainTime;
	}

	/**
	 * 有誰詛咒這個{@code Unit}的getter
	 * 
	 * @return 這個{@code Unit}被誰詛咒的Set
	 */
	public Set<Unit> getCurseList() {
		return curseList;
	}

	/**
	 * 名字的getter
	 * 
	 * @return 這個{@code Unit}的名字
	 */
	public String getName() {
		return name;
	}

	/**
	 * 技能清單的getter
	 * 
	 * @return 這個{@code Unit}的技能清單
	 */
	public List<Skill> getSkills() {
		return skills;
	}

	/**
	 * 判斷這個{@code Unit}是不是玩家
	 * 
	 * @return 這個{@code Unit}是不是玩家
	 */
	public boolean isPlayer() {
		return isPlayer;
	}

	/**
	 * 被{@code OnePunch}打到的時候會呼叫這個，會對這個{@code Unit}造成{@code damage}的傷害
	 * 
	 * @param damage 被打的傷害
	 */
	@Override
	public void takeOnePunchDamage(int damage) {
		int nowHP = this.getHP();
		this.setHP(nowHP - damage);
	}

	/**
	 * 對這個{@code Unit}造成{@code damage}的傷害，會判定死亡和詛咒是否生效
	 * 
	 * @param damage
	 */
	public void takeDamage(int damage) {
		int nowHP = this.getHP();
		this.setHP(nowHP - damage);
		if (this.getHP() <= 0) {
			Utils.printDead(this);
			this.getBelongTroop().addDeadCount();
			for (Unit unit : this.getCurseList()) {
				nowHP = unit.getHP();
				unit.setHP(nowHP + this.getMP());
			}
		}
	}
}