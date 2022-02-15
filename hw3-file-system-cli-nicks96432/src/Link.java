public class Link extends Node {

	private static final String type = "Link";
	private final String linkPath;

	public Link(String name, String path, Node parent) {
		super(name, type, parent);
		this.linkPath = path;
	}

	public String getLinkPath() {
		return linkPath;
	}

}
