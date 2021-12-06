package ca.ntro.core.graphs.generics.directed_graph.graph_structure;

import ca.ntro.core.graphs.generics.directed_graph.Edge;
import ca.ntro.core.graphs.generics.directed_graph.GenericNode;
import ca.ntro.core.graphs.generics.directed_graph.SearchOptionsBuilder;

public interface NodeById<N extends GenericNode<N,E,SO>, 
                          E extends Edge<N,E,SO>,
                          SO extends SearchOptionsBuilder> {
	
	

}
