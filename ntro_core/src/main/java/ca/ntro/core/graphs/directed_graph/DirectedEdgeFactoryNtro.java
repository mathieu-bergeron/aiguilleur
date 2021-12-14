package ca.ntro.core.graphs.directed_graph;

import ca.ntro.core.graphs.generics.directed_graph.DirectedGraphSearchOptions;
import ca.ntro.core.graphs.generics.graph.GenericEdgeFactoryNtro;

public class       DirectedEdgeFactoryNtro<N extends  DirectedNode<N,E>, 
                                           E extends  DirectedEdge<N,E>>

       extends     GenericEdgeFactoryNtro<N,E,DirectedGraphSearchOptions> 

       implements  DirectedEdgeFactory<N,E> {

}
