package ca.ntro.core.identifyers;

public interface Path {

	public static final String FILENAME_SEPARATOR = "¤";
	public static final String PATH_SEPARATOR = "/";
	public static final String HTML_ID_SEPARATOR = "-";
	public static final String CLASSNAME_SEPARATOR = ".";
	

	void addName(String name);
	int nameCount();
	String name(int index);

	
	

	public static Path fromSingleName(String name) {
		Path path = new PathNtro();

		path.addName(name);

		return path;
	}

	public static Path fromRawPath(String rawPath) {
		Path path = new PathNtro();
		
		path.parsePath(rawPath, PATH_SEPARATOR);

		return path;
	}

	public static Path fromKey(String key) {
		Path path = new PathNtro();
		
		path.parsePath(key, FILENAME_SEPARATOR);

		return path;
	}

	public static Path fromFilename(String filename) {
		Path path = new PathNtro();
		
		path.parsePath(filename, FILENAME_SEPARATOR);

		return path;
	}

	public static Path fromClassname(String classname) {
		Path path = new PathNtro();
		
		path.parsePath(classname, CLASSNAME_SEPARATOR);

		return path;
	}

}
