public class CatFile extends DirFunction {

	private static final String name = "cat";
	private static final String type = "File";

	public CatFile() {
		super(name, type);
	}

	@Override
	public String getPerformType(Directory directory, String... args) throws Exception {
		if (!directory.getChildren().containsKey(args[0]))
			throw new Exception(String.format("cat: directory doesn't contain file: %s", args[0]));
		Node node = directory.getChildren().get(args[0]);
		return node.getType();
	}

	@Override
	public Node perform(Directory directory, String... args) throws Exception {
		Node node = directory.getChildren().get(args[0]);
		if (!(node instanceof File))
			throw new Exception(String.format("cat: not a file: %s", node.getName()));
		File file = (File) directory.getChildren().get(args[0]);
		System.out.println(file.getContent());
		return null;
	}

}