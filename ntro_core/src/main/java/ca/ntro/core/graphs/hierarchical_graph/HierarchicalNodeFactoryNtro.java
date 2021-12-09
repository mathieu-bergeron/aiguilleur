package ca.ntro.core.graphs.hierarchical_graph;

import ca.ntro.core.graphs.generics.hierarchical_graph.GenericHierarchicalNodeFactoryNtro;

public class    HierarchicalNodeFactoryNtro<N extends HierarchicalGraphNode<N,E>,
                                            E extends HierarchicalGraphEdge<N,E>>

       extends  GenericHierarchicalNodeFactoryNtro<N,E,HierarchicalGraphSearchOptions>

       implements HierarchicalNodeFactory<N,E> {

}
