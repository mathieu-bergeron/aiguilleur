package ca.ntro.core.graphs.graph;

import ca.ntro.core.graphs.generics.graph.GenericGraphBuilder;
import ca.ntro.core.graphs.generics.graph.GraphSearchOptionsBuilder;

public interface GraphBuilder<N extends GraphNode<N,E>,
                              E extends GraphEdge<N,E>>

       extends GenericGraphBuilder<N,E, GraphSearchOptionsBuilder, GraphNodeBuilder<N,E>> {

}
