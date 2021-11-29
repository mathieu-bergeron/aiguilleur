package ca.ntro.core.graphs.hierarchical_graph;

import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.EdgeReducer;
import ca.ntro.core.graphs.EdgeType;
import ca.ntro.core.graphs.NodeId;
import ca.ntro.core.graphs.SearchOptions;
import ca.ntro.core.graphs.generic_graph.EdgeTypeReducer;
import ca.ntro.core.wrappers.result.ResultNtro;

public class HierarchicalNodeBuilderNtro<N extends HierarchicalNode<N,E,SO>,
 									     E extends Edge<N,E,SO>,
 									     SO extends SearchOptions>

       extends    HierarchicalNodeNtro<N,E,SO> 

	   implements HierarchicalNodeBuilder<N,E,SO> {


	public HierarchicalNodeBuilderNtro(NodeId id) {
		super(id);
	}

	@Override
	public void addEdge(String edgeName, N to) {
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public N toNode() {
		return (N) this;
	}

	@Override
	public void addSubNode(N subNode) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected <R> void _reduceEdgeTypes(ResultNtro<R> result, EdgeTypeReducer<R> reducer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected <R> void _reduceEdgesByType(EdgeType edgeType, ResultNtro<R> result, EdgeReducer<N, E, SO, R> reducer) {
		// TODO Auto-generated method stub
		
	}

	@SuppressWarnings("unchecked")
	@Override
	protected SO defaultSearchOptions() {
		return (SO) new HierarchicalGraphSearchOptions();
	}



}
