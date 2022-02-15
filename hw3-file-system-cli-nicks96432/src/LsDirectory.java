public class LsDirectory extends DirFunction {

	private static final String name = "ls";
	private static final String type = "Directory";

	public LsDirectory() {
		super(name, type);
	}

	@Override
	public String getPerformType(Directory directory, String... args) throws Exception {
		return directory.getType();
	}

	@Override
	public Node perform(Directory directory, String... args) throws Exception {
		for (Node n : directory.getChildren().values())
			System.out.println(n.getName());
		return null;
	}

}
