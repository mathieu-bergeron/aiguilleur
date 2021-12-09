package ca.ntro.core.graphs.hierarchical_graph;

import ca.ntro.core.graphs.generics.graph.GenericEdge;

public interface HierarchicalGraphEdge<N extends HierarchicalGraphNode<N,E>,
                                       E extends HierarchicalGraphEdge<N,E>>

       extends GenericEdge<N,E,HierarchicalGraphSearchOptions> {

}
