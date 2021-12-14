package ca.ntro.core.reflection.mock.three_pointer_list;

public interface ThreePointerListElement<E extends Object> {
	
	boolean hasNext();
	
	ThreePointerListElement<E> next();

	E value();

}
