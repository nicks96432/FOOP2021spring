public class Touch extends DirFunction {

	private static final String name = "touch";
	private static final String type = "Directory";

	public Touch() {
		super(name, type);
	}

	@Override
	public String getPerformType(Directory directory, String... args) throws Exception {
		return directory.getType();
	}

	@Override
	public Node perform(Directory directory, String... args) throws Exception {
		if (directory.getChildren().containsKey(args[0]))
			throw new Exception("touch: file/directory already exists");
		File file = new File(args[0], directory);
		file.setContent(args[1]);
		directory.addChildren(file);
		return null;
	}

}
