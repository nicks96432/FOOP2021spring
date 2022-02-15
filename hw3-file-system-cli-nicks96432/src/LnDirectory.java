public class LnDirectory extends DirFunction {

	private static final String name = "ln";
	private static final String type = "Directory";

	public LnDirectory() {
		super(name, type);
	}

	@Override
	public String getPerformType(Directory directory, String... args) throws Exception {
		return directory.getType();
	}

	@Override
	public Node perform(Directory directory, String... args) throws Exception {
		if (!directory.getChildren().containsKey(args[0]))
			throw new Exception(String.format("ln: directory not exist: %s", args[0]));
		Node node = directory.getChildren().get(args[0]);
		if (!(node instanceof Directory))
			throw new Exception(String.format("ln: link to non-directory: %s", args[0]));
		if (directory.getChildren().containsKey(args[1]))
			throw new Exception(String.format("ln: file/directory already exists: %s", args[1]));
		directory.addChildren(new Link(args[1], args[0], directory));
		return null;
	}

}
