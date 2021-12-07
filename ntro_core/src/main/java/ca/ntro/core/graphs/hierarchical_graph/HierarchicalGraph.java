package ca.ntro.core.graphs.hierarchical_graph;

import ca.ntro.core.graphs.generics.generic_hierarchical_graph.GenericHierarchicalGraph;

public interface HierarchicalGraph<N extends HierarchicalGraphNode<N,E>,
                                   E extends HierarchicalGraphEdge<N,E>>

       extends GenericHierarchicalGraph<N,E,HierarchicalGraphSearchOptionsBuilder> {

}
