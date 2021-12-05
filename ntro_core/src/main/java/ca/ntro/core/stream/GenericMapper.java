package ca.ntro.core.stream;

public interface GenericMapper<I extends Object, R extends Object> {
	
	R map(I item) throws Throwable;

}
