public class Mkdir extends DirFunction {

	private static final String name = "mkdir";
	private static final String type = "Directory";

	public Mkdir() {
		super(name, type);
	}

	@Override
	public String getPerformType(Directory directory, String... args) throws Exception {
		return directory.getType();
	}

	@Override
	public Node perform(Directory directory, String... args) throws Exception {
		if (directory.getChildren().containsKey(args[0]))
			throw new Exception("mkdir: file/directory already exists");
		Directory newDirectory = DirectoryFactory.newDirectory(args[0], directory);
		directory.addChildren(newDirectory);
		return null;
	}

}
