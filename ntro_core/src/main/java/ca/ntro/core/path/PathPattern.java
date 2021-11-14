package ca.ntro.core.path;

public interface PathPattern extends Path {

	public static final String NAME_WILDCARD = "*";
	public static final String SUBPATH_WILDCARD = "**";
	
	PathPattern clone();
	PathPattern subPath(int beginIndex);
	PathPattern subPath(int beginIndex, int endIndexExclusive);

	public static PathPattern fromSingleName(String name) {
		PathPatternNtro path = new PathPatternNtro();

		path.addName(name);

		return path;
	}

	public static PathPattern fromRawPath(String rawPath) {
		PathPatternNtro path = new PathPatternNtro();
		
		path.parsePath(rawPath, Path.PATH_SEPARATOR);

		return path;
	}

	public static PathPattern fromKey(String key) {
		PathPatternNtro path = new PathPatternNtro();
		
		path.parsePath(key, Path.FILENAME_SEPARATOR);

		return path;
	}

	public static PathPattern fromFilename(String filename) {
		PathPatternNtro path = new PathPatternNtro();
		
		path.parsePath(filename, Path.FILENAME_SEPARATOR);

		return path;
	}

	public static PathPattern fromClassname(String classname) {
		PathPatternNtro path = new PathPatternNtro();
		
		path.parsePath(classname, Path.CLASSNAME_SEPARATOR);

		return path;
		
	}

}
