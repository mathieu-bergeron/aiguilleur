package ca.ntro.core.graphs.generics.hierarchical_graph;

import ca.ntro.core.graphs.generics.graph.GenericEdge;
import ca.ntro.core.graphs.generics.graph.GenericGraph;

public interface GenericHierarchicalGraph<N extends GenericHierarchicalNode<N,E,SO>,
								   E extends GenericEdge<N,E,SO>,
								   SO extends HierarchicalSearchOptions>
       
       extends   GenericGraph<N,E,SO> {


}
