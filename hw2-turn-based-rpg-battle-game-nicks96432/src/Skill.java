import java.util.List;

public abstract class Skill {
	private final TargetType targetType;
	private final int costMP;
	private final int maxTargetCount;
	private final String name;

	/**
	 * 建構子
	 * 
	 * @param targetType     作用在哪一方
	 * @param maxTargetCount 最多可以選幾個單位，-1代表全部的某一方
	 * @param costMP         消耗的MP
	 */
	public Skill(TargetType targetType, int maxTargetCount, int costMP, String name) {
		this.targetType = targetType;
		this.costMP = costMP;
		this.maxTargetCount = maxTargetCount;
		this.name = name;
	}

	/**
	 * 取得這個技能的名字
	 * 
	 * @return 這個技能的名字
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * 檢查輸入是否滿足條件
	 * 
	 * @param by          誰開技能
	 * @param targetUnits 目標清單
	 * @throws UnknownError 不符合技能使用規則(MP不足、目標數量太多等)
	 */
	protected void check(Unit by, List<Unit> targetUnits) throws UnknownError {
		if (this.getMaxTargetCount() != -1 && targetUnits.size() > this.getMaxTargetCount())
			throw new UnknownError(String.format("Unexpected targetUnits size: %d", targetUnits.size()));
		int nowMP = by.getMP();
		if (nowMP < this.getMPCost())
			throw new UnknownError(String.format("Not enough MP: need %d, but only %d", nowMP, this.getMPCost()));
	}

	/**
	 * 開技能都會做的動作，檢查是否滿足條件、消耗MP、印出使用的技能
	 * 
	 * @param by          誰開技能
	 * @param targetUnits 目標清單
	 * @throws UnknownError 不符合技能使用規則
	 */
	protected void commonMove(Unit by, List<Unit> targetUnits) throws UnknownError {
		this.check(by, targetUnits);
		int nowMP = by.getMP();
		by.setMP(nowMP - this.getMPCost());
		if (this.getName() == BasicAttack.name)
			Utils.printUnits(String.format("[%d]%s attacks", by.getBelongTroop().getID(), by.getName()), targetUnits);
		else if (this.getTargetType() == TargetType.None || this.getTargetType() == TargetType.Self)
			System.out.println(
					String.format("[%d]%s uses %s.", by.getBelongTroop().getID(), by.getName(), this.getName()));
		else
			Utils.printUnits(
					String.format("[%d]%s uses %s on", by.getBelongTroop().getID(), by.getName(), this.getName()),
					targetUnits);
	}

	/**
	 * 每個技能會做出的動作，必須override這個函數，這裡不用管MP的消耗
	 * <p>
	 * 如果有造成傷害要在這裡結算
	 * 
	 * @param by          誰開技能
	 * @param troops      所有單位，召喚東西會用到
	 * @param targetUnits 目標清單
	 */
	protected abstract void specificMove(Unit by, List<Unit> targetUnits);

	/**
	 * 以{@code by}的身分對{@code targetUnits}釋放技能
	 * <p>
	 * 如果會召喚東西的話會在{@code troops}中新增召喚物
	 * 
	 * @param by          誰開技能
	 * @param troops      所有單位，召喚東西會用到
	 * @param targetUnits 目標清單
	 * @throws UnknownError 不符合技能使用規則(MP不足、目標數量太多等)
	 */
	public void perform(Unit by, List<Unit> targetUnits) throws UnknownError {
		commonMove(by, targetUnits);
		specificMove(by, targetUnits);
	}

	/**
	 * 消耗多少MP的getter
	 * 
	 * @return 這個{@code Skill}消耗多少MP
	 */
	public int getMPCost() {
		return costMP;
	}

	/**
	 * 最大釋放對象數的getter
	 * 
	 * @return 這個{@code Skill}最多可以作用在幾個單位上，-1代表全部
	 */
	public int getMaxTargetCount() {
		return maxTargetCount;
	}

	/**
	 * 對誰施放的getter
	 * 
	 * @return 這個{@code Skill}是作用在哪一方(友方、敵方、自身、無)
	 */
	public TargetType getTargetType() {
		return targetType;
	}
}
