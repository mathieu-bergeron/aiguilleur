package ca.ntro.core.graphs.graph;

import ca.ntro.core.graphs.generics.directed_graph.EdgeType;
import ca.ntro.core.graphs.generics.directed_graph.GenericDirectedGraph;
import ca.ntro.core.graphs.generics.directed_graph.GenericDirectedGraphBuilder;
import ca.ntro.core.graphs.generics.directed_graph.GenericGraphStructure;
import ca.ntro.core.graphs.generics.directed_graph.GraphId;
import ca.ntro.core.graphs.generics.directed_graph.NodeId;
import ca.ntro.core.graphs.generics.graph.GenericGraph;
import ca.ntro.core.graphs.generics.graph.GenericGraphBuilderNtro;
import ca.ntro.core.graphs.generics.graph.GraphSearchOptionsBuilder;

public class GraphBuilderNtro <N extends GraphNode<N,E>,
                               E extends GraphEdge<N,E>,
                               NB extends GraphNodeBuilder<N,E,NB>> 

       extends GenericGraphBuilderNtro<N,E,GraphSearchOptionsBuilder,NB>

       implements GraphBuilder<N,E,NB> {

	public GraphBuilderNtro(String graphName) {
		super(graphName);
	}

	@Override
	protected GenericGraph<N, E, GraphSearchOptionsBuilder> createGraph(GraphId id,
			GenericGraphStructure<N, E, GraphSearchOptionsBuilder> graphStructure) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected NB createNodeBuilder(NodeId nodeId,
			GenericDirectedGraphBuilder<N, E, GraphSearchOptionsBuilder, NB, GenericDirectedGraph<N, E, GraphSearchOptionsBuilder>> graphBuilder) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected E createEdge(NB fromNode, EdgeType edgeType, NB toNode) {
		// TODO Auto-generated method stub
		return null;
	}

}
