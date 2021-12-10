package ca.ntro.core.reflection;

public interface List<I extends Object> {
	
	void add(I item);

	void insert(int index, I item);

	int size();
	
	

}
