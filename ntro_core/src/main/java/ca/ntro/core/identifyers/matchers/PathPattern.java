package ca.ntro.core.identifyers.matchers;

import java.util.List;

import ca.ntro.core.identifyers.Path;
import ca.ntro.core.util.ArrayUtils;
import ca.ntro.core.util.ListUtils;
import ca.ntro.core.validation.Validator;

public class PathPattern extends Path {


	public PathPattern() {
		super();
	}

	protected PathPattern(List<String> names) {
		super(names);
	}

	protected PathPattern(PathPattern otherPattern) {
		super(otherPattern);
	}

	@Override
	protected String[] validNameCharacters() {
		return ArrayUtils.addString(Validator.validIdCharacters, "*");
	}

	public static PathPattern fromSingleName(String name) {
		PathPattern path = new PathPattern();

		path.addName(name);

		return path;
	}

	public static PathPattern fromRawPath(String rawPath) {
		PathPattern path = new PathPattern();
		
		path.parsePath(rawPath, PATH_SEPARATOR);

		return path;
	}

	public static PathPattern fromKey(String key) {
		PathPattern path = new PathPattern();
		
		path.parsePath(key, FILENAME_SEPARATOR);

		return path;
	}

	public static PathPattern fromFilename(String filename) {
		PathPattern path = new PathPattern();
		
		path.parsePath(filename, FILENAME_SEPARATOR);

		return path;
	}

	public static PathPattern fromClassname(String classname) {
		PathPattern path = new PathPattern();
		
		path.parsePath(classname, CLASSNAME_SEPARATOR);

		return path;
		
		
	}

	@Override
	public PathPattern clone() {
		return subPath(0, nameCount());
	}

	@Override
	public PathPattern subPath(int beginIndex) {
		return subPath(beginIndex, nameCount());
	}

	@Override
	public PathPattern subPath(int beginIndex, int endIndexExclusive) {
		PathPattern path = null;
		
		if(ifValidIndices(beginIndex, endIndexExclusive-1)) {

			path = new PathPattern(ListUtils.subList(getNames(), beginIndex, endIndexExclusive));

		}else {

			path = new PathPattern();

		}
		
		return path;
	}
}
