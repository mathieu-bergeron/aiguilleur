package ca.ntro.core.identifyers.matchers;

public interface Matcher<V extends Object> {
	
	boolean matches(V value);

}
