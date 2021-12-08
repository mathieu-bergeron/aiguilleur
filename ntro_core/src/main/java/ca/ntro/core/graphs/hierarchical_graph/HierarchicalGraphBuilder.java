package ca.ntro.core.graphs.hierarchical_graph;

import ca.ntro.core.graphs.generics.hierarchical_graph.GenericHierarchicalGraphBuilder;

public interface HierarchicalGraphBuilder<N extends HierarchicalGraphNode<N,E>,
                                          E extends HierarchicalGraphEdge<N,E>>

       extends GenericHierarchicalGraphBuilder<N,
                                               E,
                                               HierarchicalGraphSearchOptionsBuilder,
                                               HierarchicalGraphNodeBuilder<N,E>,
                                               HierarchicalGraph<N,E>> {

	static <N extends HierarchicalGraphNodeNtro<N,E>, E extends HierarchicalGraphEdgeNtro<N,E>> 

	      HierarchicalGraphBuilder<N,E> newBuilder(Class<N> nodeClass, Class<E> edgeClass) {
		
		HierarchicalGraphBuilderNtro<N,E> builder = new HierarchicalGraphBuilderNtro<N,E>();
		
		builder.setNodeClass(nodeClass);
		builder.setEdgeClass(edgeClass);
		
		builder.initialize();

		return builder;
	}
}
