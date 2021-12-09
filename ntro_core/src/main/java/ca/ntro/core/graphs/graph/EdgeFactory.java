package ca.ntro.core.graphs.graph;

import ca.ntro.core.graphs.generics.graph.GenericEdgeFactory;

public interface EdgeFactory<N extends Node<N,E>,
                             E extends Edge<N,E>> 
	
	   extends   GenericEdgeFactory<N,E,GraphSearchOptions> {

}
