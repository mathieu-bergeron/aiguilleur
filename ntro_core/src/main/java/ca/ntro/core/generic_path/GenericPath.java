package ca.ntro.core.generic_path;

import ca.ntro.core.json.JsonSerializable;

public interface GenericPath<N extends KeySerializable, I extends GenericPath<N,I>> extends KeySerializable, JsonSerializable {

	void addName(String rawName);
	void addName(N name);
	int nameCount();
	N name(int index);

	void append(I otherPath);
	boolean isPrefixOf(I otherPath);
	boolean startsWith(I path);
	boolean startsWith(String rawPath);
	boolean isRootPath();
	N lastName();

	I clone();
	I subPath(int beginIndex);
	I subPath(int beginIndex, int endIndexExclusive);
	I parent();
	I removePrefix(String rawPrefix);
	I removePrefix(I prefix);

}
