package ca.ntro.core.graphs.generic_graph.graph_strcuture;

import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.SearchOptions;

public interface EdgesByDirection<N extends Node<N,E,SO>, 
                                  E extends Edge<N,E,SO>,
                                  SO extends SearchOptions> 

       extends   EdgesMap<N,E,SO>  {
}
