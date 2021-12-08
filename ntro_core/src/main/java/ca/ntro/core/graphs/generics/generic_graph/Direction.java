package ca.ntro.core.graphs.generics.generic_graph;


public enum Direction {

	FORWARD, BACKWARD, UP, DOWN;

	public boolean equalsUndirected(Direction other) {
		if(this == other) {
			return true;
		}
		
		if(this == FORWARD && other == BACKWARD) {
			return true;
		}

		if(this == BACKWARD && other == FORWARD) {
			return true;
		}
		
		return false;
		
	}
}
