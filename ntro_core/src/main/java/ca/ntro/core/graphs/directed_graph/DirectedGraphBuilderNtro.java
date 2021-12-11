package ca.ntro.core.graphs.directed_graph;

import ca.ntro.core.graphs.generics.directed_graph.DirectedGraphWriterOptions;
import ca.ntro.core.graphs.generics.directed_graph.DirectedSearchOptions;
import ca.ntro.core.graphs.generics.graph.GenericGraphBuilderNtro;

public class      DirectedGraphBuilderNtro<N extends  DirectedNode<N,E>, 
                                           E extends  DirectedEdge<N,E>>

       extends    GenericGraphBuilderNtro<N,
                                          E,
                                          DirectedSearchOptions,
                                          DirectedNodeBuilder<N,E>,
                                          DirectedGraphWriterOptions, 
                                          DirectedGraph<N,E>> 

       implements DirectedGraphBuilder<N,E> {

	@Override
	protected DirectedGraph<N, E> newGraphInstance() {
		return new DirectedGraphNtro<N,E>();
	}

	@Override
	protected DirectedNodeBuilder<N, E> newNodeBuilderInstance() {
		return new DirectedNodeBuilderNtro<N,E>();
	}

}
