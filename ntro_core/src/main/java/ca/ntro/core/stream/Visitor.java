package ca.ntro.core.stream;

public interface Visitor<I extends Object> {
	
	void visit(I item) throws Throwable;

}
