package ca.ntro.core.graphs.graph;

import ca.ntro.core.graphs.generics.graph.GenericGraphBuilderNtro;
import ca.ntro.core.graphs.generics.graph.GraphSearchOptionsBuilder;

public abstract class GraphBuilderNtro <N extends GraphNode<N,E>,
                                        E extends GraphEdge<N,E>,
                                        NB extends GraphNodeBuilder<N,E,NB>> 

       extends GenericGraphBuilderNtro<N,E,GraphSearchOptionsBuilder,NB>

       implements GraphBuilder<N,E,NB> {

	public GraphBuilderNtro(String graphName) {
		super(graphName);
	}
}
