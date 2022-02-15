class CatLink extends DirFunction {

	private static final String name = "cat";
	private static final String type = "Link";

	public CatLink() {
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
		if (!(node instanceof Link))
			throw new Exception(String.format("cat: not a link: %s", node.getName()));
		Link file = (Link) directory.getChildren().get(args[0]);
		directory = (Directory) directory.getChildren().get(file.getLinkPath());
		System.out.println(directory.getPath());
		return null;
	}

}