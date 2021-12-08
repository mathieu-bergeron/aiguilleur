package ca.ntro.core.graphs.directed_graph;

import ca.ntro.core.graphs.common.Direction;
import ca.ntro.core.graphs.generics.graph.SearchOptionsNtro;

public class DirectedSearchOptionsNtro 
 
       extends SearchOptionsNtro 
       
       implements DirectedSearchOptions {
	
	public DirectedSearchOptionsNtro() {
		super();

		internal().setDirections(new Direction[] {Direction.FORWARD});
	}

}
