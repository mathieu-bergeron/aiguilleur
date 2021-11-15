package ca.ntro.core.path;

public interface PathPattern extends GenericPath<PathPattern> {

	public static final String NAME_WILDCARD = "*";
	public static final String SUBPATH_WILDCARD = "**";

	public static PathPattern fromPath(GenericPath<?> otherPath) {
		PathPatternNtro pathPattern = new PathPatternNtro();

		pathPattern.fromGenericPath(otherPath);

		return pathPattern;
	}

	public static PathPattern fromFilename(String rawFilename) {
		PathPatternNtro pathPattern = new PathPatternNtro();
		
		pathPattern.fromFilename(rawFilename);

		return pathPattern;
	}

	public static PathPattern fromRawPattern(String rawPattern) {
		PathPatternNtro pathPattern = new PathPatternNtro();
		
		pathPattern.fromRawPath(rawPattern);

		return pathPattern;
	}

}
