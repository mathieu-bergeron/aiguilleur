package ca.ntro.core.graphs.hierarchical_graph;

import ca.ntro.core.graphs.generics.hierarchical_graph.GenericHierarchicalNodeBuilderNtro;

public class      HierarchicalGraphNodeBuilderNtro<N extends HierarchicalGraphNode<N,E>,
                                                   E extends HierarchicalGraphEdge<N,E>>

       extends    GenericHierarchicalNodeBuilderNtro<N,E,HierarchicalGraphSearchOptions,HierarchicalGraphNodeBuilder<N,E>> 

	   implements HierarchicalGraphNodeBuilder<N,E> {

}
