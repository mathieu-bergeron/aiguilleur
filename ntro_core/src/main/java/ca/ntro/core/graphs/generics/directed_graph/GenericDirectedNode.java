package ca.ntro.core.graphs.generics.directed_graph;

import ca.ntro.core.graphs.generics.graph.GenericNode;

public interface GenericDirectedNode<N extends GenericDirectedNode<N,E,SO>, 
                                     E extends GenericDirectedEdge<N,E,SO>,
                                     SO extends DirectedGraphSearchOptions> 

       extends   GenericNode<N,E,SO> {

}
