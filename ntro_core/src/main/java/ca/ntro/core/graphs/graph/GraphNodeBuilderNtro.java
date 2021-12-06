package ca.ntro.core.graphs.graph;

import ca.ntro.core.graphs.generics.directed_graph.GenericDirectedGraph;
import ca.ntro.core.graphs.generics.directed_graph.GenericDirectedGraphBuilder;
import ca.ntro.core.graphs.generics.directed_graph.GenericNodeBuilderNtro;
import ca.ntro.core.graphs.generics.directed_graph.GenericNodeStructure;
import ca.ntro.core.graphs.generics.directed_graph.NodeId;
import ca.ntro.core.graphs.generics.graph.GraphSearchOptionsBuilder;

public class      GraphNodeBuilderNtro <N extends GraphNode<N,E>,
                                        E extends GraphEdge<N,E>,
                                        NB extends GraphNodeBuilder<N,E,NB>> 

       extends    GenericNodeBuilderNtro<N,E,GraphSearchOptionsBuilder, NB>

       implements GraphNode<N,E>,
                  GraphNodeBuilder<N,E,NB>

{


	public GraphNodeBuilderNtro(NodeId nodeId,
			GenericDirectedGraphBuilder<N, E, GraphSearchOptionsBuilder, NB, GenericDirectedGraph<N, E, GraphSearchOptionsBuilder>> graphBuilder) {
		super(nodeId, graphBuilder);
	}

	@Override
	protected GenericNodeStructure<N, E, GraphSearchOptionsBuilder> nodeStructure() {
		// TODO Auto-generated method stub
		return null;
	}

}
