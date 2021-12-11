package ca.ntro.core.graphs.generics.directed_graph;

import ca.ntro.core.graphs.common.Direction;
import ca.ntro.core.graphs.generics.graph.SearchOptionsNtro;

public class DirectedGraphSearchOptionsNtro 
 
       extends SearchOptionsNtro 
       
       implements DirectedGraphSearchOptions {
	
	public DirectedGraphSearchOptionsNtro() {
		super();

		internal().setDirections(new Direction[] {Direction.FORWARD});
	}

}
