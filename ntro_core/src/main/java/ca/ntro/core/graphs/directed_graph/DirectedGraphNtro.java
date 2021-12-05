package ca.ntro.core.graphs.directed_graph;

import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.generic_graph.GenericGraphNtro;
import ca.ntro.core.graphs.generic_graph.GenericNodeBuilder;

public abstract class DirectedGraphNtro<N extends Node<N,E,SO>,
                                        E extends Edge<N,E,SO>,
                                        SO extends DirectedGraphSearchOptionsBuilder,
                                        NB extends GenericNodeBuilder<N,E,SO,NB>>

       extends        GenericGraphNtro<N,E,SO> 

       implements     DirectedGraph<N,E,SO> {

	public DirectedGraphNtro() {
		super();
	}
}
