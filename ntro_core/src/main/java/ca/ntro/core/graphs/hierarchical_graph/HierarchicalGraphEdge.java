package ca.ntro.core.graphs.hierarchical_graph;

import ca.ntro.core.graphs.generics.generic_graph.Edge;

public interface HierarchicalGraphEdge<N extends HierarchicalGraphNode<N,E>,
                                       E extends HierarchicalGraphEdge<N,E>>

       extends Edge<N,E,HierarchicalGraphSearchOptionsBuilder> {

}
