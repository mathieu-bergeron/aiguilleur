package ca.ntro.core.stream;

public interface GenericVisitor<I extends Object> {
	
	void visit(I item) throws Throwable;

}
