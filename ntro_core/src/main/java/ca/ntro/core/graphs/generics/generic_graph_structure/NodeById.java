package ca.ntro.core.graphs.generics.generic_graph_structure;

import ca.ntro.core.graphs.generics.generic_graph.Edge;
import ca.ntro.core.graphs.generics.generic_graph.Node;
import ca.ntro.core.graphs.generics.generic_graph.SearchOptionsBuilder;

public interface NodeById<N extends Node<N,E,SO>, 
                          E extends Edge<N,E,SO>,
                          SO extends SearchOptionsBuilder> {
	
	

}
