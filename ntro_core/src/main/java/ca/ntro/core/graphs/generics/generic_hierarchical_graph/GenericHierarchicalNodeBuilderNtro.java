package ca.ntro.core.graphs.generics.generic_hierarchical_graph;

import ca.ntro.core.graphs.generics.generic_graph.Direction;
import ca.ntro.core.graphs.generics.generic_graph.Edge;
import ca.ntro.core.graphs.generics.generic_graph.EdgeNtro;
import ca.ntro.core.graphs.generics.generic_graph.EdgeTypeNtro;
import ca.ntro.core.graphs.generics.generic_graph.GenericGraph;
import ca.ntro.core.graphs.generics.generic_graph.GenericGraphBuilder;
import ca.ntro.core.graphs.generics.generic_graph.GenericNodeBuilderNtro;
import ca.ntro.core.graphs.generics.generic_graph.NodeId;

public abstract class GenericHierarchicalNodeBuilderNtro<N extends GenericHierarchicalNode<N,E,SO>,
 									              E extends Edge<N,E,SO>,
 									              SO extends HierarchicalGraphSearchOptionsBuilder,
 									              NB extends GenericHierarchicalNodeBuilder<N,E,SO,NB>>

       extends        GenericNodeBuilderNtro<N,E,SO,NB> 

	   implements     GenericHierarchicalNodeBuilder<N,E,SO,NB> {



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

		((GenericHierarchicalNodeBuilderNtro<N,E,SO,NB>) subNode).addParentNode(this.node());
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