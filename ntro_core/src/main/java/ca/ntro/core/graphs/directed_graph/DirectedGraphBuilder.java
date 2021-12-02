package ca.ntro.core.graphs.directed_graph;

import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.SearchOptionsNtro;
import ca.ntro.core.graphs.generic_graph.GenericGraphBuilder;

public interface DirectedGraphBuilder<N extends Node<N,E,SO>,
                                      E extends Edge<N,E,SO>,
                                      SO extends SearchOptionsNtro>

       extends   GenericGraphBuilder<N,E,SO,DirectedGraph<N,E,SO>> {

	public static <N extends Node<N,E,SO>,
				   E extends Edge<N,E,SO>,
				   SO extends SearchOptionsNtro>
	
	       DirectedGraphBuilder<N,E,SO> 

		   newBuilder(){

		return new DirectedGraphNtro<>();
	}

	public static <N extends Node<N,E,SO>,
				   E extends Edge<N,E,SO>,
				   SO extends SearchOptionsNtro>
	
      	    DirectedGraphBuilder<N,E,SO> newBuilder(String graphName){

		return new DirectedGraphNtro<>(graphName);
	}

}
