package ca.ntro.core.graphs.dag;

public interface NodeMatcher<N extends Node> {
	
	boolean matches(N node);
	

}
