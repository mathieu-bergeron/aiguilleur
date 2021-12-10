package ca.ntro.core.reflection;

public interface LinkedListElement<E extends Object> {

	E value();
	
	boolean hasNext();

	LinkedListElement<E> next();
}
