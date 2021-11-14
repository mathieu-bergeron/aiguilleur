package ca.ntro.core.path;

public interface Path extends PathGeneric<Path> {
	
    public static final String FILENAME_SEPARATOR = "¤";
    public static final String PATH_SEPARATOR = "/";
    public static final String HTML_ID_SEPARATOR = "-";
    public static final String CLASSNAME_SEPARATOR = ".";
    
    public static Path fromRawPath(String rawPath) {
    	PathNtro path = new PathNtro();
    	
    	path.fromRawPath(rawPath);
    	
    	return path;
    }

}
