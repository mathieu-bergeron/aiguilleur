package ca.ntro.core.graphs.graph;

import ca.ntro.core.graphs.generics.generic_graph.GenericGraphBuilderNtro;
import ca.ntro.core.graphs.generics.generic_graph.GenericGraphNtro;
import ca.ntro.core.graphs.generics.generic_graph.GenericNodeBuilderNtro;

public class      GraphBuilderNtro<N extends GraphNode<N,E>,
                                   E extends GraphEdge<N,E>>

       extends    GenericGraphBuilderNtro<N,E, GraphSearchOptionsBuilder, GraphNodeBuilder<N,E>, Graph<N,E>> 

       implements GraphBuilder<N,E> {

	@Override
	protected GenericGraphNtro<N, E, GraphSearchOptionsBuilder> newGraphInstance() {
		return new GraphNtro<>();
	}

	@Override
	protected GenericNodeBuilderNtro<N, E, GraphSearchOptionsBuilder, GraphNodeBuilder<N, E>> newNodeBuilderInstance() {
		return new GraphNodeBuilderNtro<>();
	}

}
