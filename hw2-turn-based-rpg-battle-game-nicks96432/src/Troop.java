import java.util.ArrayList;
import java.util.List;

public class Troop {
	private List<Unit> units;
	private int deadCount;
	private final int ID;

	/**
	 * 空的{@code Troop}的建構子
	 * 
	 * @param ID 第幾個{@code Troop}
	 */
	public Troop(int ID) {
		this.units = new ArrayList<>();
		this.ID = ID;
		deadCount = 0;
	}

	/**
	 * 用單位清單建構出一個{@code Troop}
	 * 
	 * @param units 單位清單
	 * @param ID    第幾個{@code Troop}
	 */
	public Troop(List<Unit> units, int ID) {
		this.units = units;
		this.ID = ID;
		deadCount = 0;
	}

	/**
	 * 這個{@code Troop}的單位清單的getter
	 * 
	 * @return 一個單位清單
	 */
	public List<Unit> getUnits() {
		return units;
	}

	/**
	 * 新增一個單位到這個{@code Troop}
	 * 
	 * @param unit 一個單位
	 */
	public void addUnit(Unit unit) {
		this.units.add(unit);
	}

	/**
	 * 這個{@code Troop}的死亡人數的getter
	 * 
	 * @return 死亡人數
	 */
	public int getDeadCount() {
		return deadCount;
	}

	/**
	 * 增加一個死亡人數
	 */
	public void addDeadCount() {
		++deadCount;
	}

	/**
	 * 這個{@code Troop}是第幾個的Getter
	 * 
	 * @return 這個{@code Troop}是第幾個
	 */
	public int getID() {
		return ID;
	}

}
