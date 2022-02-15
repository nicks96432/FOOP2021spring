public class CdDirectory extends DirFunction {

	private static final String name = "cd";
	private static final String type = "Directory";

	public CdDirectory() {
		super(name, type);
	}

	@Override
	public String getPerformType(Directory directory, String... args) throws Exception {
		if (args[0].equals(".."))
			return type;
		if (!directory.getChildren().containsKey(args[0]))
			throw new Exception(String.format("cd: directory doesn't contain: %s", args[0]));
		Node node = directory.getChildren().get(args[0]);
		return node.getType();
	}

	@Override
	public Node perform(Directory directory, String... args) throws Exception {
		if (args[0].equals(".."))
			return directory.getParent();
		Node node = directory.getChildren().get(args[0]);
		if (!(node instanceof Directory))
			throw new Exception(String.format("cd: not a directory: %s", node.getName()));
		return node;
	}

}
