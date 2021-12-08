package ca.ntro.core.graphs.directed_graph;

import ca.ntro.core.graphs.generics.graph.GenericEdgeNtro;

public class DirectedEdgeNtro<N extends  DirectedNode<N,E>, 
                              E extends  DirectedEdge<N,E>>

       extends GenericEdgeNtro<N,E,DirectedSearchOptions> 
  
       implements DirectedEdge<N,E> {

}
