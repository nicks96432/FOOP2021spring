import static utils.Inputs.in;

import java.util.List;

public class FileSystemCLI {
	private Directory root;
	private Directory currentDir;

	public FileSystemCLI(List<DirFunction> supportFunctions) {
		DirectoryFactory.setSupportFunctions(supportFunctions);
		root = DirectoryFactory.newDirectory("", null);
		root.setParent(root);
		currentDir = root;
	}

	public void start() {
		while (true) {
			printCurrentPath();
			String command = in.nextLine();
			if (command.equals("exit")) {
				break;
			}
			executeCommand(command);
		}
	}

	private void printCurrentPath() {
		System.out.println("Current path: " + currentDir.getPath());
	}

	public void executeCommand(String command) {
		String[] segments = command.split("\\s");

		try {
			String commandName = segments[0];
			performCommand(commandName, segments);
		} catch (Exception err) {
			System.err.println("(Debug) Error: " + err.getMessage());
			System.out.println("Illegal command.");
		}
	}

	private void performCommand(String commandName, String[] segments) throws Exception {
		switch (commandName) {
			case "cd":
				changeDirectory(segments[1]);
				break;
			case "mkdir":
				makeDirectory(segments[1]);
				break;
			case "touch":
				touch(segments[1], segments[2]);
				break;
			case "rm":
				remove(segments[1]);
				break;
			case "cat":
				concatenate(segments[1]);
				break;
			case "ls":
				list();
				break;
			case "search":
				search(segments[1]);
				break;
			case "ln":
				link(segments[1], segments[2]);
				break;
		}
	}

	private void changeDirectory(String directoryName) throws Exception {
		currentDir = (Directory) currentDir.callFunction("cd", directoryName);
	}

	private void makeDirectory(String directoryName) throws Exception {
		currentDir.callFunction("mkdir", directoryName);
	}

	private void touch(String fileName, String content) throws Exception {
		currentDir.callFunction("touch", fileName, content);
	}

	private void remove(String childName) throws Exception {
		currentDir.callFunction("rm", childName);
	}

	private void concatenate(String fileName) throws Exception {
		currentDir.callFunction("cat", fileName);
	}

	private void list() throws Exception {
		currentDir.callFunction("ls");
	}

	private void link(String targetName, String linkName) throws Exception {
		currentDir.callFunction("ln", targetName, linkName);
	}

	private void search(String keyword) throws Exception {
		currentDir.callFunction("search", keyword);
	}

}
