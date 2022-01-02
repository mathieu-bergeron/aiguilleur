package ca.ntro.core.graphs.common;

import ca.ntro.core.stream.Stream;
import ca.ntro.core.stream.StreamNtro;
import ca.ntro.core.stream.Visitor;

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
	
	public static Stream<Direction> asStream(){
		return new StreamNtro<Direction>() {
			@Override
			public void forEach_(Visitor<Direction> visitor) throws Throwable {
				for(Direction direction : Direction.values()) {
					visitor.visit(direction);
				}
			}
		};
		
	}
}
