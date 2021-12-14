package ca.ntro.core.graphs.directed_graph;

import ca.ntro.core.graphs.generics.directed_graph.DirectedGraphSearchOptions;
import ca.ntro.core.graphs.generics.graph.GenericNodeFactoryNtro;

public class      DirectedNodeFactoryNtro<N extends  DirectedNode<N,E>, 
                                          E extends  DirectedEdge<N,E>>

       extends    GenericNodeFactoryNtro<N,E,DirectedGraphSearchOptions> 

       implements DirectedNodeFactory<N,E> {

}
