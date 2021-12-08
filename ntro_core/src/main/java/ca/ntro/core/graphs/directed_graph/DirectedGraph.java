package ca.ntro.core.graphs.directed_graph;

import ca.ntro.core.graphs.generics.graph.GenericGraph;

public interface DirectedGraph<N extends  DirectedNode<N,E>, 
                               E extends  DirectedEdge<N,E>>

       extends   GenericGraph<N,E,DirectedSearchOptions> {

}
