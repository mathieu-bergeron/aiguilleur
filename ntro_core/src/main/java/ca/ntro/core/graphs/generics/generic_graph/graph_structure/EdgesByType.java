package ca.ntro.core.graphs.generics.generic_graph.graph_structure;

import ca.ntro.core.graphs.generics.generic_graph.Edge;
import ca.ntro.core.graphs.generics.generic_graph.Node;
import ca.ntro.core.graphs.generics.generic_graph.SearchOptionsBuilder;
import ca.ntro.core.graphs.generics.generic_graph.SearchOptionsNtro;

public interface EdgesByType<N extends Node<N,E,SO>, 
                                 E extends Edge<N,E,SO>,
                                 SO extends SearchOptionsBuilder> 

       extends EdgesMap<N,E,SO> {


}
