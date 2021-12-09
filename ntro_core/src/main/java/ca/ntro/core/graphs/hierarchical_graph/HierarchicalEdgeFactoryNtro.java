package ca.ntro.core.graphs.hierarchical_graph;

import ca.ntro.core.graphs.generics.hierarchical_graph.GenericHierarchicalEdgeFactoryNtro;

public class HierarchicalEdgeFactoryNtro<N extends HierarchicalGraphNode<N,E>,
                                         E extends HierarchicalGraphEdge<N,E>>

       extends   GenericHierarchicalEdgeFactoryNtro<N,E,HierarchicalGraphSearchOptions> 

       implements HierarchicalEdgeFactory<N,E> {

}
