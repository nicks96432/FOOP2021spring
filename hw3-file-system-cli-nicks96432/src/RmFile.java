public class RmFile extends DirFunction {

	private static final String name = "rm";
	private static final String type = "File";

	public RmFile() {
		super(name, type);
	}

	@Override
	public String getPerformType(Directory directory, String... args) throws Exception {
		Node node = directory.getChildren().get(args[0]);
		return node.getType();
	}

	@Override
	public Node perform(Directory directory, String... args) throws Exception {
		if (!directory.getChildren().containsKey(args[0]))
			throw new Exception(String.format("rm: directory does not contain file: %s", args[0]));
		directory.getChildren().remove(args[0]);
		return null;
	}

}
