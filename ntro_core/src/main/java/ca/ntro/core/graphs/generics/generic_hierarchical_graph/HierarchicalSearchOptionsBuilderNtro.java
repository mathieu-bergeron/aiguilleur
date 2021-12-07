package ca.ntro.core.graphs.generics.generic_hierarchical_graph;

import ca.ntro.core.graphs.generics.generic_graph.Direction;
import ca.ntro.core.graphs.graph.GraphSearchOptionsBuilderNtro;

public class HierarchicalSearchOptionsBuilderNtro 

       extends GraphSearchOptionsBuilderNtro 
       
       implements HierarchicalSearchOptionsBuilder {
	
	public HierarchicalSearchOptionsBuilderNtro() {
		internal().setDirections(new Direction[] {Direction.FORWARD, Direction.DOWN});
	}

}
