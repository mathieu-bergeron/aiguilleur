package ca.ntro.core.graphs.hierarchical_graph;

import ca.ntro.core.graphs.common.Direction;
import ca.ntro.core.graphs.generics.graph.SearchOptionsNtro;

public class      HierarchicalGraphSearchOptionsNtro 

       extends    SearchOptionsNtro

       implements HierarchicalGraphSearchOptions {
	
	public HierarchicalGraphSearchOptionsNtro() {
		super();
		
		internal().setDirections(new Direction[] {Direction.FORWARD, Direction.DOWN});
	}

}
