package ca.ntro.core.graphs.generics.directed_graph;

import ca.ntro.core.graphs.generics.graph.GenericGraphBuilderNtro;

public class GenericDirectedGraphBuilderNtro<N extends  GenericDirectedNode<N,E,SO>, 
                                             E extends  GenericDirectedEdge<N,E,SO>,
                                             SO extends DirectedSearchOptions,
                                             NB extends GenericDirectedNodeBuilder<N,E,SO,NB>,
                                             G extends GenericDirectedGraph<N,E,SO>> 

       extends    GenericGraphBuilderNtro<N,E,SO,NB,G> 

       implements GenericDirectedGraphBuilder<N,E,SO,NB,G> {


	@Override
	protected G newGraphInstance() {
		return null;
	}

	@Override
	protected NB newNodeBuilderInstance() {
		return null;
	}

}
