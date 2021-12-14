package ca.ntro.core.reflection.mock.three_pointer_list;

public interface LeftRightListElement<E extends Object> {
	
	boolean hasNext();
	
	LeftRightListElement<E> next();

	E value();

}
