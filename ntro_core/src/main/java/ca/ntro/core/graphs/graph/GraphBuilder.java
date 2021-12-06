package ca.ntro.core.graphs.graph;

import ca.ntro.core.graphs.generics.generic_graph.GenericGraphBuilder;
import ca.ntro.core.graphs.generics.generic_graph.GenericGraphBuilderNtro;

public interface GraphBuilder<N extends GraphNode<N,E>,
                              E extends GraphEdge<N,E>>

       extends   GenericGraphBuilder<N,E, GraphSearchOptionsBuilder, GraphNodeBuilder<N,E>, Graph<N,E>> {

	static <N extends GraphNode<N,E>, E extends GraphEdge<N,E>> GraphBuilder<N,E> newBuilder(Class<N> nodeClass, Class<E> edgeClass) {
		
		GenericGraphBuilderNtro<N,
		                        E,
		                        GraphSearchOptionsBuilder,
		                        GraphNodeBuilder<N,E>,
		                        Graph<N,E>> builder = new GenericGraphBuilderNtro<>();

		builder.setNodeClass(nodeClass);
		builder.setEdgeClass(edgeClass);

		builder.setSearchOptionsClass(GraphSearchOptionsBuilder.class);

		@SuppressWarnings("unchecked")
		GraphBuilder<N,E> graphBuilder = (GraphBuilder<N,E>) builder;
		
		return graphBuilder;
	}

}
