package ca.ntro.core.graphs.generics.generic_hierarchical_graph;

import ca.ntro.core.graphs.generics.generic_graph.Edge;
import ca.ntro.core.graphs.generics.generic_graph.GenericGraph;

public interface GenericHierarchicalGraph<N extends GenericHierarchicalNode<N,E,SO>,
								   E extends Edge<N,E,SO>,
								   SO extends HierarchicalSearchOptionsBuilder>
       
       extends   GenericGraph<N,E,SO> {


}
