package ca.ntro.core.graphs.graph;

import ca.ntro.core.graphs.generics.graph.GenericEdgeFactoryNtro;

public class EdgeFactoryNtro<N extends Node<N,E>,
                             E extends Edge<N,E>> 
	
	   extends   GenericEdgeFactoryNtro<N,E,GraphSearchOptions> 

       implements EdgeFactory<N,E> {

}
