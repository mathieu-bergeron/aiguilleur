package ca.ntro.core.graphs.graph;

import ca.ntro.core.graphs.generics.graph.GenericGraph;
import ca.ntro.core.graphs.generics.graph.GraphSearchOptionsBuilder;

public interface Graph <N extends GraphNode<N,E>,
                        E extends GraphEdge<N,E>>

       extends GenericGraph<N,E, GraphSearchOptionsBuilder> {

}
