package ca.ntro.core.graphs.directed_graph;

import ca.ntro.core.graphs.generics.directed_graph.DirectedGraphWriterOptions;
import ca.ntro.core.graphs.generics.directed_graph.DirectedGraphSearchOptions;
import ca.ntro.core.graphs.generics.directed_graph.GenericDirectedGraphBuilderNtro;

public class      DirectedGraphBuilderNtro<N extends  DirectedNode<N,E>, 
                                           E extends  DirectedEdge<N,E>>

       extends    GenericDirectedGraphBuilderNtro<N,
                                                  E,
                                                  DirectedGraphSearchOptions,
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
