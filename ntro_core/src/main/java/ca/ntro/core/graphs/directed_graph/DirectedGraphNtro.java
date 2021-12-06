package ca.ntro.core.graphs.directed_graph;

import ca.ntro.core.graphs.generics.directed_graph.Edge;
import ca.ntro.core.graphs.generics.directed_graph.GenericDirectedGraphNtro;
import ca.ntro.core.graphs.generics.directed_graph.GenericNode;
import ca.ntro.core.graphs.generics.directed_graph.GenericNodeBuilder;

public abstract class DirectedGraphNtro<N extends GenericNode<N,E,SO>,
                                        E extends Edge<N,E,SO>,
                                        SO extends DirectedGraphSearchOptionsBuilder,
                                        NB extends GenericNodeBuilder<N,E,SO,NB>>

       extends        GenericDirectedGraphNtro<N,E,SO> 

       implements     DirectedGraph<N,E,SO> {

	public DirectedGraphNtro() {
		super();
	}
}
