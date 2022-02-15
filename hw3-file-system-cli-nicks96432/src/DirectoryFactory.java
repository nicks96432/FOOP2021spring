import java.util.List;

public class DirectoryFactory {

	private static List<DirFunction> supportFunctions;

	static void setSupportFunctions(List<DirFunction> supportFunctions) {
		DirectoryFactory.supportFunctions = supportFunctions;
	}

	static Directory newDirectory(String name, Node parent) {
		return new Directory(name, parent, supportFunctions);
	}

}
