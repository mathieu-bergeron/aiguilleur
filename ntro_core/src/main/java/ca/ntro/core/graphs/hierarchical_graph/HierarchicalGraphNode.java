package ca.ntro.core.graphs.hierarchical_graph;

import ca.ntro.core.graphs.generics.hierarchical_graph.GenericHierarchicalNode;

public interface HierarchicalGraphNode<N extends HierarchicalGraphNode<N,E>,
                                       E extends HierarchicalGraphEdge<N,E>> 

       extends GenericHierarchicalNode<N,E,HierarchicalGraphSearchOptions> {

}
