package ca.ntro.core.graphs.generics.graph;

import ca.ntro.core.graphs.generics.directed_graph.Edge;
import ca.ntro.core.graphs.generics.directed_graph.GenericDirectedGraphBuilderNtro;
import ca.ntro.core.graphs.generics.directed_graph.GenericNode;
import ca.ntro.core.graphs.generics.directed_graph.GenericNodeBuilder;

public abstract class GenericGraphBuilderNtro<N extends GenericNode<N,E,SO>,
								       E extends Edge<N,E,SO>,
								       SO extends GraphSearchOptionsBuilder,
								       NB extends GenericNodeBuilder<N,E,SO,NB>>

       extends        GenericDirectedGraphBuilderNtro<N,E,SO,NB,GenericGraph<N,E,SO>> 

       implements     GenericGraphBuilder<N,E,SO,NB> {


	public GenericGraphBuilderNtro() {
		super();
	}

	public GenericGraphBuilderNtro(String graphName) {
		super(graphName);
	}
}
