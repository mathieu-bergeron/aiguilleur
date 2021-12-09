package ca.ntro.core.graphs.graph;

import ca.ntro.core.graphs.common.EdgeType;
import ca.ntro.core.graphs.generics.graph.GenericEdgeNtro;

public class      EdgeNtro<N extends Node<N,E>,
                                E extends Edge<N,E>>

       extends    GenericEdgeNtro<N,E, GraphSearchOptions> 

       implements Edge<N,E> {

	public EdgeNtro() {
		super();
	}

	public EdgeNtro(N from, EdgeType edgeType, N to) {
		super(from, edgeType, to);
	}


}
