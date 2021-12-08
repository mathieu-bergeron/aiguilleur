package ca.ntro.core.graphs.directed_graph;

import ca.ntro.core.graphs.generics.graph.GenericGraphStructure;

public interface DirectedGraphStructure<N extends  DirectedNode<N,E>, 
                                        E extends  DirectedEdge<N,E>>

       extends GenericGraphStructure<N,E,DirectedSearchOptions> {

}
