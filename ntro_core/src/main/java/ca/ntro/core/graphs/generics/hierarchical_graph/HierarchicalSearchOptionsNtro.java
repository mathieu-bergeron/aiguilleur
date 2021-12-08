package ca.ntro.core.graphs.generics.hierarchical_graph;

import ca.ntro.core.graphs.common.Direction;
import ca.ntro.core.graphs.graph.GraphSearchOptionsBuilderNtro;

public class HierarchicalSearchOptionsNtro 

       extends GraphSearchOptionsBuilderNtro 
       
       implements HierarchicalSearchOptions {
	
	public HierarchicalSearchOptionsNtro() {
		internal().setDirections(new Direction[] {Direction.FORWARD, Direction.DOWN});
	}

}
