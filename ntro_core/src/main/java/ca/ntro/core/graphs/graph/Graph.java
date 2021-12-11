package ca.ntro.core.graphs.graph;

import ca.ntro.core.graphs.generics.graph.GenericGraph;
import ca.ntro.core.graphs.generics.graph.GraphWriterOptions;

public interface Graph <N extends Node<N,E>,
                        E extends Edge<N,E>>

       extends GenericGraph<N,E, GraphSearchOptions,GraphWriterOptions> {

}
