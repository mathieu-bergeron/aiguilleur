package ca.ntro.core.graphs.graph;

import ca.ntro.core.graphs.generics.graph.GenericGraphBuilderNtro;
import ca.ntro.core.graphs.generics.graph.GraphSearchOptionsBuilder;

public abstract class GraphBuilderNtro <N extends GraphNode<N,E>,
                                        E extends GraphEdge<N,E>> 

       extends GenericGraphBuilderNtro<N,E,GraphSearchOptionsBuilder,GraphNodeBuilder<N,E>>

       implements GraphBuilder<N,E> {

	public GraphBuilderNtro() {
		super();
	}

	public GraphBuilderNtro(String graphName) {
		super(graphName);
	}

}
