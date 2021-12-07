package ca.ntro.core.graphs.hierarchical_graph;

import ca.ntro.core.graphs.generics.generic_hierarchical_graph.GenericHierarchicalNodeBuilder;

public interface HierarchicalGraphNodeBuilder<N extends HierarchicalGraphNode<N,E>,
                                              E extends HierarchicalGraphEdge<N,E>>

       extends GenericHierarchicalNodeBuilder<N,E,HierarchicalGraphSearchOptionsBuilder,HierarchicalGraphNodeBuilder<N,E>> {

}
