package ca.ntro.core.reflection.mock.linked_list;

public interface LinkedListElement<E extends Object> {

	E value();
	
	boolean hasNext();

	LinkedListElement<E> next();
}
