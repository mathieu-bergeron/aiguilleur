package ca.ntro.core.graphs.generics.hierarchical_graph;

import ca.ntro.core.graphs.generics.graph.GenericEdge;
import ca.ntro.core.graphs.generics.graph.GenericEdgeFactory;

public interface GenericHierarchicalEdgeFactory<N extends GenericHierarchicalNode<N,E,SO>,
								                E extends GenericEdge<N,E,SO>,
								                SO extends HierarchicalSearchOptions> 

       extends   GenericEdgeFactory<N,E,SO> {

}
