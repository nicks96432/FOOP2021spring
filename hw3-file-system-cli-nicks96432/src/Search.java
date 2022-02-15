public class Search extends DirFunction {

	private static final String name = "search";
	private static final String type = "Directory";

	public Search() {
		super(name, type);
	}

	@Override
	public String getPerformType(Directory directory, String... args) throws Exception {
		return directory.getType();
	}

	@Override
	public Node perform(Directory directory, String... args) throws Exception {
		if (directory.getName().contains(args[0]))
			System.out.println(String.format("%s", directory.getName()));
		for (Node n : directory.getChildren().values()) {
			if (n instanceof Directory)
				((Directory) n).callFunction("search", args);
			else if (n.getName().contains(args[0]))
				System.out.println(String.format("%s", n.getName()));
		}
		return null;
	}

}
