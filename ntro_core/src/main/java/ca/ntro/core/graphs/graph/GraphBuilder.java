package ca.ntro.core.graphs.graph;

import ca.ntro.core.graphs.generics.graph.GenericGraphBuilder;

public interface GraphBuilder<N extends Node<N,E>,
                              E extends Edge<N,E>>

       extends   GenericGraphBuilder<N,E, GraphSearchOptionsBuilder, NodeBuilder<N,E>, Graph<N,E>> {

	static <N extends NodeNtro<N,E>, E extends EdgeNtro<N,E>> 

	      GraphBuilder<N,E> newBuilder(Class<N> nodeClass, Class<E> edgeClass) {
		
		GraphBuilderNtro<N,E> builder = new GraphBuilderNtro<N,E>();
		
		builder.setNodeClass(nodeClass);
		builder.setEdgeClass(edgeClass);
		
		builder.initialize();

		return builder;
	}

}
