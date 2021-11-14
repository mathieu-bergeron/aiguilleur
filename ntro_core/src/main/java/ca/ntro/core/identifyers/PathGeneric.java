package ca.ntro.core.identifyers;

import ca.ntro.core.initialization.Factory;
import ca.ntro.core.json.JsonSerializable;

public interface PathGeneric<I extends PathGeneric<I, IMPL>, IMPL extends PathGenericNtro<I,IMPL>> extends JsonSerializable {

	void addName(String name);
	int nameCount();
	String name(int index);

	void append(I otherPath);
	boolean isPrefixOf(I otherPath);
	boolean startsWith(I path);
	boolean startsWith(String rawPath);
	boolean isRootPath();
	String lastName();

	I clone();
	I subPath(int beginIndex);
	I subPath(int beginIndex, int endIndexExclusive);
	I parent();

	String toRawPath();
	String toHtmlId();
	String toFilename();
	String toKey();
	I removePrefix(String rawPrefix);
	I removePrefix(I prefix);
	String toClassname();

	public static <I extends PathGeneric<I,IMPL>, IMPL extends PathGenericNtro<I,IMPL>>
               	  IMPL newInstance(Class<IMPL> implClass, String[] validCharacters) {
		
		IMPL path = Factory.newInstance(implClass);
		
		path.setImplClass(implClass);
		path.setValidCharacters(validCharacters);
		
		return path;
	}

	public static <I extends PathGeneric<I,IMPL>, IMPL extends PathGenericNtro<I,IMPL>>
               	  I fromRawPath(Class<IMPL> implClass, 
               			           String[] validCharacters, 
               			           String rawPath) {
		
		IMPL path = newInstance(implClass, validCharacters);
		
		path.parsePath(rawPath, Path.PATH_SEPARATOR);
		
		return (I) path;
	}

}
