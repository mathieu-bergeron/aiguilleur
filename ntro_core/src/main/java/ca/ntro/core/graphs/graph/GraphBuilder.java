package ca.ntro.core.graphs.graph;

import ca.ntro.core.graphs.generics.generic_graph.GenericGraphBuilder;

public interface GraphBuilder<N extends GraphNode<N,E>,
                              E extends GraphEdge<N,E>>

       extends   GenericGraphBuilder<N,E, GraphSearchOptionsBuilder, GraphNodeBuilder<N,E>, Graph<N,E>> {

	static <N extends GraphNodeNtro<N,E>, E extends GraphEdgeNtro<N,E>> 

	      GraphBuilder<N,E> newBuilder(Class<N> nodeClass, Class<E> edgeClass) {
		
		GraphBuilderNtro<N,E> builder = new GraphBuilderNtro<N,E>();
		
		builder.setNodeClass(nodeClass);
		builder.setEdgeClass(edgeClass);
		
		builder.initialize();

		return builder;
	}

}
