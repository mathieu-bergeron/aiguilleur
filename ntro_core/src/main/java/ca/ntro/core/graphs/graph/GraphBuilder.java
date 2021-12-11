package ca.ntro.core.graphs.graph;

import ca.ntro.core.graphs.generics.graph.GenericGraphBuilder;
import ca.ntro.core.graphs.generics.graph.GraphWriterOptions;
import ca.ntro.core.initialization.Factory;

public interface GraphBuilder<N extends Node<N,E>,
                              E extends Edge<N,E>>

       extends   GenericGraphBuilder<N,E, GraphSearchOptions, NodeBuilder<N,E>, GraphWriterOptions, Graph<N,E>> {

	static <N extends NodeNtro<N,E>, E extends EdgeNtro<N,E>> 

	      GraphBuilder<N,E> newBuilder(NodeFactory<N,E> nodeFactory, EdgeFactory<N,E> edgeFactory) {
		
		GraphBuilderNtro<N,E> builder = new GraphBuilderNtro<N,E>();
		
		builder.setNodeFactory(nodeFactory);
		builder.setEdgeFactory(edgeFactory);

		builder.initialize();

		return builder;
	}

	static <N extends NodeNtro<N,E>, E extends EdgeNtro<N,E>> 

	      GraphBuilder<N,E> newBuilder(Class<N> nodeClass, Class<E> edgeClass) {

		// JSweet: class instead of lambda to avoid typing errors
		NodeFactoryNtro<N,E> nodeFactory = new NodeFactoryNtro<>();
		nodeFactory.setNodeClass(nodeClass);
		
		EdgeFactoryNtro<N,E> edgeFactory = new EdgeFactoryNtro<N,E>();
		edgeFactory.setEdgeClass(edgeClass);
		
		return newBuilder(nodeFactory, edgeFactory);
	}


}
