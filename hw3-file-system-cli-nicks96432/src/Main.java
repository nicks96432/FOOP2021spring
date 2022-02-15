import java.util.ArrayList;
import java.util.List;

/**
 * The File System Commandline will be started from the Main class.
 */
public class Main {
	public static void main(String[] args) {
		List<DirFunction> suppNodeFunctions = new ArrayList<>();
		suppNodeFunctions.add(new CatFile());
		suppNodeFunctions.add(new CatLink());
		suppNodeFunctions.add(new CdDirectory());
		suppNodeFunctions.add(new CdLink());
		suppNodeFunctions.add(new LnDirectory());
		suppNodeFunctions.add(new LsDirectory());
		suppNodeFunctions.add(new Mkdir());
		suppNodeFunctions.add(new RmFile());
		suppNodeFunctions.add(new RmDirectory());
		suppNodeFunctions.add(new RmLink());
		suppNodeFunctions.add(new Search());
		suppNodeFunctions.add(new Touch());
		FileSystemCLI cli = new FileSystemCLI(suppNodeFunctions);
		cli.start();
	}
}
