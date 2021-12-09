package ca.ntro.core.graphs.generics.hierarchical_graph;

import ca.ntro.core.graphs.common.Direction;
import ca.ntro.core.graphs.common.EdgeTypeNtro;
import ca.ntro.core.graphs.generics.graph.GenericEdge;
import ca.ntro.core.graphs.generics.graph.GenericEdgeNtro;
import ca.ntro.core.graphs.generics.graph.GenericNodeBuilderNtro;

public abstract class GenericHierarchicalNodeBuilderNtro<N extends GenericHierarchicalNode<N,E,SO>,
 									              E extends GenericEdge<N,E,SO>,
 									              SO extends HierarchicalSearchOptions,
 									              NB extends GenericHierarchicalNodeBuilder<N,E,SO,NB>>

       extends        GenericNodeBuilderNtro<N,E,SO,NB> 

	   implements     GenericHierarchicalNodeBuilder<N,E,SO,NB> {


	@Override
	public N node() {
		return getNode();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void addSubNode(NB subNode) {
		EdgeTypeNtro edgeType = new EdgeTypeNtro(Direction.DOWN, "");

		E edge = (E) new GenericEdgeNtro<N,E,SO>(this.node(), edgeType, subNode.node());

		getEdgesByDirection().addEdge(edge);

		((GenericHierarchicalNodeBuilderNtro<N,E,SO,NB>) subNode).addParentNode(this.node());
	}

	@SuppressWarnings("unchecked")
	protected void addParentNode(N parentNode) {
		EdgeTypeNtro edgeType = new EdgeTypeNtro(Direction.UP, "");

		E edge = (E) new GenericEdgeNtro<N,E,SO>(this.node(), edgeType, parentNode);

		((GenericHierarchicalNodeBuilderNtro<N,E,SO,NB>) this).setIsStartNode(false);
		
		getGraphBuilder().removeStartNode(this.node());
		
		getEdgesByDirection().addEdge(edge);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected SO defaultSearchOptions() {
		return (SO) new HierarchicalSearchOptionsNtro();
	}
}