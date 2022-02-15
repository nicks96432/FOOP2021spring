public class File extends Node {

	private String content;
	private static final String type = "File";

	public File(String name, Node parent) {
		super(name, type, parent);
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
