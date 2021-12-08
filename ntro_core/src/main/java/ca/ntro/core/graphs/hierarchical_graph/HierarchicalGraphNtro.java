package ca.ntro.core.graphs.hierarchical_graph;

import ca.ntro.core.graphs.generics.hierarchical_graph.GenericHierarchicalGraphNtro;

public class HierarchicalGraphNtro<N extends HierarchicalGraphNode<N,E>,
                                   E extends HierarchicalGraphEdge<N,E>>

       extends GenericHierarchicalGraphNtro<N,E,HierarchicalGraphSearchOptionsBuilder> 

       implements HierarchicalGraph<N,E> { 
}
