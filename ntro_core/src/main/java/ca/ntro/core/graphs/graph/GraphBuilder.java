package ca.ntro.core.graphs.graph;

import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.SearchOptionsNtro;
import ca.ntro.core.graphs.generic_graph.GenericGraphBuilder;

public interface GraphBuilder<N extends Node<N,E,SO>,
							  E extends Edge<N,E,SO>,
							  SO extends GraphSearchOptionsBuilder>

       extends GenericGraphBuilder<N,E,SO, Graph<N,E,SO>> {
	
	public static <N extends Node<N,E,SO>,
				   E extends Edge<N,E,SO>,
				   SO extends GraphSearchOptionsBuilder> 
	
		GraphBuilder<N,E,SO> newBuilder(){

		return new GraphBuilderNtro<>();
	}

	public static <N extends Node<N,E,SO>,
				   E extends Edge<N,E,SO>,
				   SO extends GraphSearchOptionsBuilder> 
	
		GraphBuilder<N,E,SO> newBuilder(String graphName){

		return new GraphBuilderNtro<>(graphName);
	}

}
