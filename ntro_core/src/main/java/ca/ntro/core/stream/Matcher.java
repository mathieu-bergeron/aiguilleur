package ca.ntro.core.stream;

public interface Matcher<I extends Object> {
	
	boolean matches(I item) throws Throwable;

}
