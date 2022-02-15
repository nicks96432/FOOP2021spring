/**
 * 代表整個檔案系統任意一個東西的Object
 * <p>
 * 收到指令->先找出這個指令的執行對象->找到可以被作用的東西->對找到的東西執行
 */
public class Node {

	private String name;
	private Node parent;
	private final String type;

	public Node(String name, String type, Node parent) {
		this.name = name;
		this.type = type;
		this.parent = parent;
	}

	/**
	 * 得到這個檔案或目錄的絕對路徑，不包含它的名字
	 * 
	 * @return 這個檔案或目錄的絕對路徑的String
	 */
	public String getPath() {
		String path = this.getName();
		Node node = this;
		Node prev = this;
		while ((node = node.getParent()) != prev) {
			path = node.getName().concat("/").concat(path);
			prev = node;
		}
		if (path.isEmpty())
			path = "/";
		return path;
	}

	/**
	 * 得到這個東西的名字
	 * 
	 * @return 這個東西的名字的String
	 */
	public String getName() {
		return name;
	}

	/**
	 * 設定這個東西的名字
	 * 
	 * @param name 新名字
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 得到這個東西所在的地方
	 * 
	 * @return 在哪個東西裡面
	 */
	public Node getParent() {
		return this.parent;
	}

	public void setParent(Directory parent) {
		this.parent = parent;
	}

	public String getType() {
		return type;
	}

}
