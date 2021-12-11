package ca.ntro.core.graphs.hierarchical_graph;

import ca.ntro.core.graphs.generics.hierarchical_graph.GenericHierarchicalGraphBuilderNtro;
import ca.ntro.core.graphs.generics.hierarchical_graph.HierarchicalGraphWriterOptions;

public class HierarchicalGraphBuilderNtro<N extends HierarchicalGraphNode<N,E>,
                                          E extends HierarchicalGraphEdge<N,E>>

       extends GenericHierarchicalGraphBuilderNtro<N,
                                                   E,
                                                   HierarchicalGraphSearchOptions,
                                                   HierarchicalGraphNodeBuilder<N,E>,
                                                   HierarchicalGraphWriterOptions,
                                                   HierarchicalGraph<N,E>>

       implements HierarchicalGraphBuilder<N,E> {

	@Override
	protected HierarchicalGraph<N,E> newGraphInstance() {
		return new HierarchicalGraphNtro<>();
	}

	@Override
	protected HierarchicalGraphNodeBuilder<N,E> newNodeBuilderInstance() {
		return new HierarchicalGraphNodeBuilderNtro<>();
	}

}
