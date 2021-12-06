package ca.ntro.core.graphs.graph;

import ca.ntro.core.graphs.generics.directed_graph.GenericDirectedGraphBuilderNtro;
import ca.ntro.core.graphs.generics.graph.GenericGraphBuilder;
import ca.ntro.core.graphs.generics.graph.GenericGraphBuilderNtro;
import ca.ntro.core.graphs.generics.graph.GraphSearchOptionsBuilder;

public interface GraphBuilder<N extends GraphNode<N,E>,
                              E extends GraphEdge<N,E>>

       extends   GenericGraphBuilder<N,E, GraphSearchOptionsBuilder, GraphNodeBuilder<N,E>> {

	static <N extends GraphNode<N,E>, E extends GraphEdge<N,E>> GraphBuilder<N,E> newBuilder(Class<N> nodeClass, Class<E> edgeClass) {
		
		GenericDirectedGraphBuilderNtro<N,
		                                E,
		                                GraphSearchOptionsBuilder,
		                                GraphNodeBuilder<N,E>,
		                                Graph<N,E>> graphBuilder = new GenericDirectedGraphBuilderNtro<N,
		                                                                                               E,
		                                                                                               GraphSearchOptionsBuilder,
		                                                                                               GraphNodeBuilder<N,E>,
		                                                                                               Graph<N,E>>();

		graphBuilder.setNodeClass(nodeClass);
		graphBuilder.setEdgeClass(edgeClass);

		graphBuilder.setSearchOptionsClass(GraphSearchOptionsBuilderNtro.class);

		graphBuilder.setGraphClass(GraphNtro.class);

		return graphBuilder;
	}
}
