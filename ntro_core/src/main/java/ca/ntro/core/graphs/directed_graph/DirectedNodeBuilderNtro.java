package ca.ntro.core.graphs.directed_graph;

import ca.ntro.core.graphs.generics.directed_graph.DirectedGraphSearchOptions;
import ca.ntro.core.graphs.generics.directed_graph.GenericDirectedNodeBuilderNtro;

public class      DirectedNodeBuilderNtro<N extends  DirectedNode<N,E>, 
                                          E extends  DirectedEdge<N,E>>

       extends    GenericDirectedNodeBuilderNtro<N,
                                                 E,
                                                 DirectedGraphSearchOptions,
                                                 DirectedNodeBuilder<N,E>>

       implements DirectedNodeBuilder<N,E> {

}
