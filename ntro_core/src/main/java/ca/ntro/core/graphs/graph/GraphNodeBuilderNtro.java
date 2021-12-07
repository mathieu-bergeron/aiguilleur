package ca.ntro.core.graphs.graph;

import ca.ntro.core.graphs.generics.generic_graph.GenericNodeBuilderNtro;

public class      GraphNodeBuilderNtro<N extends GraphNode<N,E>,
                                       E extends GraphEdge<N,E>>

       extends    GenericNodeBuilderNtro<N,E,GraphSearchOptionsBuilder,GraphNodeBuilder<N,E>> 

       implements GraphNodeBuilder<N,E> {


}
