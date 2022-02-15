import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Directory extends Node {

	private Map<String, Node> children;
	private static final String type = "Directory";
	private final Map<String, DirFunction> typeGetters;
	private final Map<String, Map<String, DirFunction>> functions;

	public Directory(String name, Node parent, List<DirFunction> functions) {
		super(name, type, parent);
		children = new TreeMap<>();
		this.typeGetters = new HashMap<>();
		this.functions = new HashMap<>();
		for (DirFunction nodeFunction : functions) {
			if (!this.functions.containsKey(nodeFunction.getName())) {
				typeGetters.put(nodeFunction.getName(), nodeFunction);
				this.functions.put(nodeFunction.getName(), new HashMap<>());
			}
			this.functions.get(nodeFunction.getName()).put(nodeFunction.getType(), nodeFunction);
		}
	}

	public Map<String, Node> getChildren() {
		return this.children;
	}

	public void addChildren(Node child) {
		this.children.put(child.getName(), child);
	}

	public Node callFunction(String name, String... args) throws Exception {
		String type = this.typeGetters.get(name).getPerformType(this, args);
		return this.functions.get(name).get(type).perform(this, args);
	}

}
