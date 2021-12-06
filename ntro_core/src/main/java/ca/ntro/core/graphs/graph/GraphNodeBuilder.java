package ca.ntro.core.graphs.graph;

import ca.ntro.core.graphs.generics.directed_graph.GenericNodeBuilder;
import ca.ntro.core.graphs.generics.graph.GraphSearchOptionsBuilder;

public interface GraphNodeBuilder<N extends GraphNode<N,E>,
                                  E extends GraphEdge<N,E>>

       extends   GenericNodeBuilder<N,E,GraphSearchOptionsBuilder, GraphNodeBuilder<N,E>> {

}
