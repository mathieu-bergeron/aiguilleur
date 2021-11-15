package ca.ntro.core.path;

import ca.ntro.core.json.JsonSerializable;

public interface GenericPath<I extends GenericPath<I>> extends JsonSerializable {

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

}
