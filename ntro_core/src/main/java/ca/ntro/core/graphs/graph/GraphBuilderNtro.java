package ca.ntro.core.graphs.graph;

import ca.ntro.core.graphs.generics.graph.GenericGraphBuilderNtro;
import ca.ntro.core.graphs.generics.graph.GraphWriterOptions;

public class      GraphBuilderNtro<N extends Node<N,E>,
                                   E extends Edge<N,E>>

       extends    GenericGraphBuilderNtro<N,E, GraphSearchOptions, NodeBuilder<N,E>, GraphWriterOptions, Graph<N,E>> 

       implements GraphBuilder<N,E> {

	@Override
	protected Graph<N,E> newGraphInstance() {
		return new GraphNtro<>();
	}

	@Override
	protected NodeBuilder<N,E> newNodeBuilderInstance() {
		return new NodeBuilderNtro<>();
	}

}
