package ca.ntro.core.graphs.generics.graph;

import ca.ntro.core.graphs.generics.directed_graph.Edge;
import ca.ntro.core.graphs.generics.directed_graph.GenericDirectedGraph;
import ca.ntro.core.graphs.generics.directed_graph.GenericNode;

public interface GenericGraph<N extends GenericNode<N,E,SO>,
                              E extends Edge<N,E,SO>,
                              SO extends GraphSearchOptionsBuilder>

       extends   GenericDirectedGraph<N,E,SO> {
	

}
