package ca.ntro.core.graphs.directed_graph;

import ca.ntro.core.graphs.generics.graph.GenericEdge;
import ca.ntro.core.graphs.generics.graph.GenericGraphNtro;
import ca.ntro.core.graphs.generics.graph.GenericNodeBuilder;
import ca.ntro.core.graphs.generics.graph.GenericNode;

public abstract class DirectedGraphNtro<N extends GenericNode<N,E,SO>,
                                        E extends GenericEdge<N,E,SO>,
                                        SO extends DirectedGraphSearchOptionsBuilder,
                                        NB extends GenericNodeBuilder<N,E,SO,NB>>

       extends        GenericGraphNtro<N,E,SO> 

       implements     DirectedGraph<N,E,SO> {

	public DirectedGraphNtro() {
		super();
	}
}
