package ca.ntro.core.path;

import ca.ntro.core.json.JsonSerializable;
import ca.ntro.core.validation.Validator;

public interface Path extends PathGeneric<Path>, JsonSerializable {

	public static final String FILENAME_SEPARATOR = "¤";
	public static final String PATH_SEPARATOR = "/";
	public static final String HTML_ID_SEPARATOR = "-";
	public static final String CLASSNAME_SEPARATOR = ".";
	
	public static Path emptyPath() {
		return new PathNtro(Validator.validIdCharacters);
	}

	public static Path fromSingleName(String name) {
		PathNtro path = new PathNtro(Validator.validIdCharacters);

		path.addName(name);

		return path;
	}

	public static Path fromRawPath(String rawPath) {
		return PathGeneric.fromRawPath(PathNtro.class, Validator.validIdCharacters, rawPath);
	}

	public static Path fromKey(String key) {
		PathNtro path = new PathNtro(Validator.validIdCharacters);
		
		path.parsePath(key, FILENAME_SEPARATOR);

		return path;
	}

	public static Path fromFilename(String filename) {
		PathNtro path = new PathNtro(Validator.validIdCharacters);
		
		path.parsePath(filename, FILENAME_SEPARATOR);

		return path;
	}

	public static Path fromClassname(String classname) {
		PathNtro path = new PathNtro(Validator.validIdCharacters);
		
		path.parsePath(classname, CLASSNAME_SEPARATOR);

		return path;
	}
}
