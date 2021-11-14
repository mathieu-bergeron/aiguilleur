package ca.ntro.core.identifyers;

import ca.ntro.core.json.JsonSerializable;

public interface Path extends JsonSerializable {

	public static final String FILENAME_SEPARATOR = "¤";
	public static final String PATH_SEPARATOR = "/";
	public static final String HTML_ID_SEPARATOR = "-";
	public static final String CLASSNAME_SEPARATOR = ".";
	

	void addName(String name);
	int nameCount();
	String name(int index);

	void append(Path otherPath);
	boolean isPrefixOf(Path otherPath);
	boolean startsWith(Path path);
	boolean startsWith(String rawPath);
	boolean isRootPath();
	String lastName();

	Path clone();
	Path subPath(int beginIndex);
	Path subPath(int beginIndex, int endIndexExclusive);

	String toRawPath();
	String toHtmlId();
	String toFilename();
	String toKey();
	Path removePrefix(String rawPrefix);
	Path removePrefix(Path prefix);
	String toClassname();
	
	public static Path emptyPath() {
		return new PathNtro();
	}

	public static Path fromSingleName(String name) {
		PathNtro path = new PathNtro();

		path.addName(name);

		return path;
	}

	public static Path fromRawPath(String rawPath) {
		PathNtro path = new PathNtro();
		
		path.parsePath(rawPath, PATH_SEPARATOR);

		return path;
	}

	public static Path fromKey(String key) {
		PathNtro path = new PathNtro();
		
		path.parsePath(key, FILENAME_SEPARATOR);

		return path;
	}

	public static Path fromFilename(String filename) {
		PathNtro path = new PathNtro();
		
		path.parsePath(filename, FILENAME_SEPARATOR);

		return path;
	}

	public static Path fromClassname(String classname) {
		PathNtro path = new PathNtro();
		
		path.parsePath(classname, CLASSNAME_SEPARATOR);

		return path;
	}


}
