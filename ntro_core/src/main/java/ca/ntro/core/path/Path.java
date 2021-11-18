package ca.ntro.core.path;

public interface Path extends GenericPath<Path> {
	
    public static final String FILENAME_SEPARATOR = "¤";
    public static final String PATH_SEPARATOR = "/";
    public static final String HTML_ID_SEPARATOR = "-";
    public static final String CLASSNAME_SEPARATOR = ".";

	public static Path emptyPath() {
    	return new PathNtro();
	}

	public static Path rootPath() {
    	return new PathNtro();
	}

    public static Path fromRawPath(String rawPath) {
    	PathNtro path = new PathNtro();
    	
    	path.fromRawPath(rawPath);
    	
    	return path;
    }

	public static Path fromClassname(String rawClassname) {
    	PathNtro path = new PathNtro();
    	
    	path.fromClassname(rawClassname);
    	
    	return path;
	}

	public static Path fromKey(String rawKey) {
    	PathNtro path = new PathNtro();
    	
    	path.fromKey(rawKey);
    	
    	return path;
	}

	public static Path fromFilename(String rawFilename) {
    	PathNtro path = new PathNtro();
    	
    	path.fromFilename(rawFilename);
    	
    	return path;
	}

	public static Path fromSingleName(String id) {
    	PathNtro path = new PathNtro();
    	
    	path.fromSingleName(id);
    	
    	return path;
	}

	public static Path fromPath(GenericPath<?> otherPath) {
		PathNtro path = new PathNtro();
		
		path.fromGenericPath(otherPath);
		
		return path;
	}

}
