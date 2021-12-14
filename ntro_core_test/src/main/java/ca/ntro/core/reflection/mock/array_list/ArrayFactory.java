package ca.ntro.core.reflection.mock.array_list;

public interface ArrayFactory<I extends Object> {
	
	I[] newInstance(int size);

}
