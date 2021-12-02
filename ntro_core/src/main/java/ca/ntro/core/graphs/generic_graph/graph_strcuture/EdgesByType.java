package ca.ntro.core.graphs.generic_graph.graph_strcuture;

import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.SearchOptionsBuilder;
import ca.ntro.core.graphs.SearchOptionsNtro;

public interface EdgesByType<N extends Node<N,E,SO>, 
                                 E extends Edge<N,E,SO>,
                                 SO extends SearchOptionsBuilder> 

       extends EdgesMap<N,E,SO> {


}
