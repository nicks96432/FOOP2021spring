public abstract class DirFunction {

	private final String name;
	private final String type;

	public DirFunction(String name, String type) {
		this.name = name;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	abstract public String getPerformType(Directory directory, String... args) throws Exception;

	abstract public Node perform(Directory directory, String... args) throws Exception;

}
