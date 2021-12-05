package ca.ntro.core.graphs.hierarchical_graph;

import ca.ntro.core.graphs.Direction;
import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.EdgeTypeNtro;
import ca.ntro.core.graphs.NodeId;
import ca.ntro.core.graphs.generic_graph.EdgeNtro;
import ca.ntro.core.graphs.generic_graph.GenericGraph;
import ca.ntro.core.graphs.generic_graph.GenericGraphBuilder;
import ca.ntro.core.graphs.generic_graph.GenericNodeBuilderNtro;

public abstract class HierarchicalNodeBuilderNtro<N extends HierarchicalNode<N,E,SO>,
 									              E extends Edge<N,E,SO>,
 									              SO extends HierarchicalGraphSearchOptionsBuilder,
 									              NB extends HierarchicalNodeBuilder<N,E,SO,NB>>

       extends        GenericNodeBuilderNtro<N,E,SO,NB> 

	   implements     HierarchicalNode<N,E,SO>,
	                  HierarchicalNodeBuilder<N,E,SO,NB> {



	public HierarchicalNodeBuilderNtro(NodeId nodeId, 
			                           GenericGraphBuilder<N, E, SO, NB, GenericGraph<N, E, SO>> graphBuilder) {
		super(nodeId, graphBuilder);
	}

	@SuppressWarnings("unchecked")
	@Override
	public N toNode() {
		return (N) this;
	}

	@Override
	public E addEdge(String edgeName, N toNode) {
		return getGraphBuilder().addEdge(this.toNode(), edgeName, toNode);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void addSubNode(N subNode) {
		EdgeTypeNtro edgeType = new EdgeTypeNtro(Direction.DOWN, "");

		E edge = (E) new EdgeNtro<N,E,SO>(this.toNode(), edgeType, subNode);

		getEdgesByDirection().addEdge(edge);

		((HierarchicalNodeBuilderNtro<N,E,SO,NB>) subNode).addParentNode(this.toNode());
	}

	@SuppressWarnings("unchecked")
	protected void addParentNode(N parentNode) {
		EdgeTypeNtro edgeType = new EdgeTypeNtro(Direction.UP, "");

		E edge = (E) new EdgeNtro<N,E,SO>(this.toNode(), edgeType, parentNode);

		getEdgesByDirection().addEdge(edge);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected SO defaultSearchOptions() {
		return (SO) new HierarchicalGraphSearchOptionsBuilderNtro();
	}
}
