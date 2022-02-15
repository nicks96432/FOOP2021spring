public class CdLink extends DirFunction {

	private static final String name = "cd";
	private static final String type = "Link";

	public CdLink() {
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
		Node node = directory.getChildren().get(args[0]);
		if (!(node instanceof Link))
			throw new Exception(String.format("cd: not a link: %s", node.getName()));
		Link link = (Link) node;
		if (!directory.getChildren().containsKey(link.getLinkPath()))
			throw new Exception(String.format("cd: linked directory not found: %s", link.getLinkPath()));
		node = directory.getChildren().get(link.getLinkPath());
		if (!(node instanceof Directory))
			throw new Exception(String.format("cd: linked not a directory: %s", node.getName()));
		return directory.getChildren().get(link.getLinkPath());
	}

}
