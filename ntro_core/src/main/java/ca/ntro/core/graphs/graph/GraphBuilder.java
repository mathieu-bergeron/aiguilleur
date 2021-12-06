package ca.ntro.core.graphs.graph;

import ca.ntro.core.graphs.generics.directed_graph.GenericDirectedGraphBuilder;
import ca.ntro.core.graphs.generics.directed_graph.GenericDirectedGraphBuilderNtro;

public interface GraphBuilder<N extends GraphNode<N,E>,
                              E extends GraphEdge<N,E>>

       extends   GenericDirectedGraphBuilder<N,E, GraphSearchOptionsBuilder, GraphNodeBuilder<N,E>, Graph<N,E>> {

	static <N extends GraphNode<N,E>, E extends GraphEdge<N,E>> GraphBuilder<N,E> newBuilder(Class<N> nodeClass, Class<E> edgeClass) {
		
		GraphBuilderNtro<N,E> builder = new GraphBuilderNtro<>();

		graphBuilder.setNodeClass(nodeClass);
		graphBuilder.setEdgeClass(edgeClass);

		graphBuilder.setSearchOptionsClass(GraphSearchOptionsBuilderNtro.class);

		graphBuilder.setGraphClass(GraphNtro.class);

		return graphBuilder;
	}

}
