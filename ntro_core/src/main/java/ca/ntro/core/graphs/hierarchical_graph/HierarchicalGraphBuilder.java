package ca.ntro.core.graphs.hierarchical_graph;

import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.SearchOptions;
import ca.ntro.core.graphs.generic_graph.GenericGraphBuilder;

public interface HierarchicalGraphBuilder<N extends HierarchicalNode<N,E,SO>,
										  E extends Edge<N,E,SO>,
										  SO extends SearchOptions>

       extends   GenericGraphBuilder<N,E,SO,HierarchicalGraph<N,E,SO>> {


	public static <N extends HierarchicalNode<N,E,SO>,
	               E extends Edge<N,E,SO>,
	               SO extends SearchOptions>
	
  	       HierarchicalGraphBuilder<N,E,SO> newBuilder(){

		return new HierarchicalGraphBuilderNtro<>();
	}
	public static <N extends HierarchicalNode<N,E,SO>,
	               E extends Edge<N,E,SO>,
	               SO extends SearchOptions>
	
  	       HierarchicalGraphBuilder<N,E,SO> newBuilder(String graphName){

		return new HierarchicalGraphBuilderNtro<>(graphName);
	}

}
