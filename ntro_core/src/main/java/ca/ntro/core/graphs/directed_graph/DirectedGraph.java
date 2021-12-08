package ca.ntro.core.graphs.directed_graph;

import ca.ntro.core.graphs.generics.graph.GenericEdge;
import ca.ntro.core.graphs.generics.graph.GenericGraph;
import ca.ntro.core.graphs.generics.graph.GenericNode;

public interface DirectedGraph<N extends GenericNode<N,E,SO>,
                               E extends GenericEdge<N,E,SO>,
                               SO extends DirectedGraphSearchOptionsBuilder>

       extends   GenericGraph<N,E,SO> {

}
