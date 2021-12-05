package ca.ntro.core.stream;

public interface GenericMatcher<I extends Object> {
	
	boolean matches(I item) throws Throwable;

}
