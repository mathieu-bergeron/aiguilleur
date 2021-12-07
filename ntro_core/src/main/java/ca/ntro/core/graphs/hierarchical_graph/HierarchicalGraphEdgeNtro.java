package ca.ntro.core.graphs.hierarchical_graph;

import ca.ntro.core.graphs.generics.generic_graph.EdgeNtro;

public class HierarchicalGraphEdgeNtro<N extends HierarchicalGraphNode<N,E>,
                                       E extends HierarchicalGraphEdge<N,E>> 

       extends    EdgeNtro<N,E,HierarchicalGraphSearchOptionsBuilder>

       implements HierarchicalGraphEdge<N,E> {

}
