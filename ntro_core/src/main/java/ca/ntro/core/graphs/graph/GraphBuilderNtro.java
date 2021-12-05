package ca.ntro.core.graphs.graph;

import ca.ntro.core.graphs.Edge;

import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.generic_graph.GenericGraphBuilderNtro;
import ca.ntro.core.graphs.generic_graph.GenericNodeBuilder;

public abstract class GraphBuilderNtro<N extends Node<N,E,SO>,
								       E extends Edge<N,E,SO>,
								       SO extends GraphSearchOptionsBuilder,
								       NB extends GenericNodeBuilder<N,E,SO,NB>>

       extends        GenericGraphBuilderNtro<N,E,SO,NB,Graph<N,E,SO>> 

       implements     GraphBuilder<N,E,SO,NB> {


	public GraphBuilderNtro() {
		super();
	}

	public GraphBuilderNtro(String graphName) {
		super(graphName);
	}
}
