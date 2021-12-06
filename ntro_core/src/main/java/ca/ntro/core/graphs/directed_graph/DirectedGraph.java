package ca.ntro.core.graphs.directed_graph;

import ca.ntro.core.graphs.generics.directed_graph.Edge;
import ca.ntro.core.graphs.generics.directed_graph.GenericDirectedGraph;
import ca.ntro.core.graphs.generics.directed_graph.GenericNode;

public interface DirectedGraph<N extends GenericNode<N,E,SO>,
                               E extends Edge<N,E,SO>,
                               SO extends DirectedGraphSearchOptionsBuilder>

       extends   GenericDirectedGraph<N,E,SO> {

}
