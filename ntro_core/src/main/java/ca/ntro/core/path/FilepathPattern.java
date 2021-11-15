package ca.ntro.core.path;

public interface FilepathPattern extends GenericPath<FilepathPattern> {

	static FilepathPattern fromRawPattern(String rawPattern) {
		FilepathPatternNtro path = new FilepathPatternNtro();
		
		path.fromRawPath(rawPattern);
		
		return path;
	}

}
