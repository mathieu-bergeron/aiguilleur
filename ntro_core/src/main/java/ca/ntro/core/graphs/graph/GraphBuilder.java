package ca.ntro.core.graphs.graph;

import ca.ntro.core.graphs.generics.graph.GenericGraphBuilder;
import ca.ntro.core.graphs.generics.graph.GraphSearchOptionsBuilder;

public interface GraphBuilder<N extends GraphNode<N,E>,
                              E extends GraphEdge<N,E>,
                              NB extends GraphNodeBuilder<N,E,NB>>

       extends   GenericGraphBuilder<N,E, GraphSearchOptionsBuilder, NB> {

}
