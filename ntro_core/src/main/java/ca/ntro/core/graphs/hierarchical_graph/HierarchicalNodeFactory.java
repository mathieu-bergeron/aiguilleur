package ca.ntro.core.graphs.hierarchical_graph;

import ca.ntro.core.graphs.generics.hierarchical_graph.GenericHierarchicalNodeFactory;

public interface HierarchicalNodeFactory<N extends HierarchicalGraphNode<N,E>,
                                         E extends HierarchicalGraphEdge<N,E>>

       extends   GenericHierarchicalNodeFactory<N,E,HierarchicalGraphSearchOptions>

 {

}
