package ca.ntro.core.graphs.graph;

import ca.ntro.core.graphs.generics.directed_graph.GenericDirectedGraph;
import ca.ntro.core.graphs.generics.directed_graph.GenericDirectedGraphBuilder;
import ca.ntro.core.graphs.generics.directed_graph.GenericNodeBuilderNtro;
import ca.ntro.core.graphs.generics.directed_graph.NodeId;
import ca.ntro.core.graphs.generics.graph.GraphSearchOptionsBuilder;

public class      GraphNodeBuilderNtro <N extends GraphNode<N,E>,
                                        E extends GraphEdge<N,E>> 

       extends    GenericNodeBuilderNtro<N,E,GraphSearchOptionsBuilder,GraphNodeBuilder<N,E>>

       implements GraphNodeBuilder<N,E> {

	public GraphNodeBuilderNtro(NodeId nodeId,
			GenericDirectedGraphBuilder<N, E, GraphSearchOptionsBuilder, GraphNodeBuilder<N, E>, GenericDirectedGraph<N, E, GraphSearchOptionsBuilder>> graphBuilder) {
		super(nodeId, graphBuilder);
	}

}
