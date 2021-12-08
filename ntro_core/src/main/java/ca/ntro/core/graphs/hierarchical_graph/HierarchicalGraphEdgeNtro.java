package ca.ntro.core.graphs.hierarchical_graph;

import ca.ntro.core.graphs.generics.graph.GenericEdgeNtro;

public class HierarchicalGraphEdgeNtro<N extends HierarchicalGraphNode<N,E>,
                                       E extends HierarchicalGraphEdge<N,E>> 

       extends    GenericEdgeNtro<N,E,HierarchicalGraphSearchOptionsBuilder>

       implements HierarchicalGraphEdge<N,E> {

}
