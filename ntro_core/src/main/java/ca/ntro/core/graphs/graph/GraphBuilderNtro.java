package ca.ntro.core.graphs.graph;

import ca.ntro.core.graphs.generics.directed_graph.EdgeType;
import ca.ntro.core.graphs.generics.directed_graph.GenericDirectedGraph;
import ca.ntro.core.graphs.generics.directed_graph.GenericDirectedGraphBuilder;
import ca.ntro.core.graphs.generics.directed_graph.GenericDirectedGraphBuilderNtro;
import ca.ntro.core.graphs.generics.directed_graph.GenericGraphStructure;
import ca.ntro.core.graphs.generics.directed_graph.GraphId;
import ca.ntro.core.graphs.generics.directed_graph.NodeId;

public class       GraphBuilderNtro<N extends GraphNode<N,E>,
                                    E extends GraphEdge<N,E>>

       extends     GenericDirectedGraphBuilderNtro<N,E, GraphSearchOptionsBuilder, GraphNodeBuilder<N,E>, Graph<N,E>> 

	   implements  GraphBuilder<N,E> {

	@Override
	public void setGraphName(String string) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected Graph<N, E> createGraph(GraphId id,
			GenericGraphStructure<N, E, GraphSearchOptionsBuilder> graphStructure) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected GraphNodeBuilder<N, E> createNodeBuilder(NodeId nodeId,
			GenericDirectedGraphBuilder<N, E, GraphSearchOptionsBuilder, GraphNodeBuilder<N, E>, GenericDirectedGraph<N, E, GraphSearchOptionsBuilder>> graphBuilder) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected E createEdge(GraphNodeBuilder<N, E> fromNode, EdgeType edgeType, GraphNodeBuilder<N, E> toNode) {
		// TODO Auto-generated method stub
		return null;
	}

}
