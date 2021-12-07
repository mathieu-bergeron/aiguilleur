package ca.ntro.core.graphs.graph;

import ca.ntro.core.graphs.generics.generic_graph.GenericGraphBuilderNtro;

public class      GraphBuilderNtro<N extends GraphNode<N,E>,
                                   E extends GraphEdge<N,E>>

       extends    GenericGraphBuilderNtro<N,E, GraphSearchOptionsBuilder, GraphNodeBuilder<N,E>, Graph<N,E>> 

       implements GraphBuilder<N,E> {

	@Override
	protected Graph<N,E> newGraphInstance() {
		return new GraphNtro<>();
	}

	@Override
	protected GraphNodeBuilder<N,E> newNodeBuilderInstance() {
		return new GraphNodeBuilderNtro<>();
	}

}
