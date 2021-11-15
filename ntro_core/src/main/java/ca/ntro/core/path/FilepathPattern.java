package ca.ntro.core.path;

public interface FilepathPattern extends GenericPath<FilepathPattern> {

	FilepathPattern directoryPattern();
	String  filenamePattern();

	static FilepathPattern fromRawPattern(String rawPattern) {
		FilepathPatternNtro path = new FilepathPatternNtro();
		
		path.fromRawPath(rawPattern);
		
		return path;
	}

}
