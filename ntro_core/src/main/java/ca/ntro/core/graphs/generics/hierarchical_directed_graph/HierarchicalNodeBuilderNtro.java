package ca.ntro.core.graphs.generics.hierarchical_directed_graph;

import ca.ntro.core.graphs.generics.directed_graph.Direction;
import ca.ntro.core.graphs.generics.directed_graph.Edge;
import ca.ntro.core.graphs.generics.directed_graph.EdgeNtro;
import ca.ntro.core.graphs.generics.directed_graph.EdgeTypeNtro;
import ca.ntro.core.graphs.generics.directed_graph.GenericDirectedGraph;
import ca.ntro.core.graphs.generics.directed_graph.GenericDirectedGraphBuilder;
import ca.ntro.core.graphs.generics.directed_graph.GenericNodeBuilderNtro;
import ca.ntro.core.graphs.generics.directed_graph.NodeId;

public abstract class HierarchicalNodeBuilderNtro<N extends HierarchicalNode<N,E,SO>,
 									              E extends Edge<N,E,SO>,
 									              SO extends HierarchicalGraphSearchOptionsBuilder,
 									              NB extends HierarchicalNodeBuilder<N,E,SO,NB>>

       extends        GenericNodeBuilderNtro<N,E,SO,NB> 

	   implements     HierarchicalNodeBuilder<N,E,SO,NB> {



	public HierarchicalNodeBuilderNtro(NodeId nodeId, 
			                           GenericDirectedGraphBuilder<N, E, SO, NB, GenericDirectedGraph<N, E, SO>> graphBuilder) {
		super(nodeId, graphBuilder);
	}

	@SuppressWarnings("unchecked")
	@Override
	public N node() {
		return (N) this;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void addSubNode(NB subNode) {
		EdgeTypeNtro edgeType = new EdgeTypeNtro(Direction.DOWN, "");

		E edge = (E) new EdgeNtro<N,E,SO>(this.node(), edgeType, subNode.node());

		getEdgesByDirection().addEdge(edge);

		((HierarchicalNodeBuilderNtro<N,E,SO,NB>) subNode).addParentNode(this.node());
	}

	@SuppressWarnings("unchecked")
	protected void addParentNode(N parentNode) {
		EdgeTypeNtro edgeType = new EdgeTypeNtro(Direction.UP, "");

		E edge = (E) new EdgeNtro<N,E,SO>(this.node(), edgeType, parentNode);

		getEdgesByDirection().addEdge(edge);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected SO defaultSearchOptions() {
		return (SO) new HierarchicalGraphSearchOptionsBuilderNtro();
	}
}
