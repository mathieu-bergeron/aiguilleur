package ca.ntro.core.graphs.hierarchical_graph;

import ca.ntro.core.graphs.generics.hierarchical_graph.GenericHierarchicalEdgeFactory;

public interface HierarchicalEdgeFactory<N extends HierarchicalGraphNode<N,E>,
                                         E extends HierarchicalGraphEdge<N,E>>

       extends   GenericHierarchicalEdgeFactory<N,E,HierarchicalGraphSearchOptions> {

}
