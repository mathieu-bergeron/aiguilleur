package ca.ntro.core.task_graphs.task_graph;

import ca.ntro.core.graphs.EdgeType;
import ca.ntro.core.graphs.NodeId;
import ca.ntro.core.graphs.generic_graph.GenericGraph;
import ca.ntro.core.graphs.generic_graph.GenericGraphBuilder;
import ca.ntro.core.graphs.hierarchical_dag.HierarchicalDag;
import ca.ntro.core.graphs.hierarchical_dag.HierarchicalDagBuilderNtro;

public class InternalHierarchicalDagBuilderNtro<T  extends Task<T,AT>, 
                                                AT extends AtomicTask<T,AT>>

       extends HierarchicalDagBuilderNtro<TaskGraphNode<T,AT>,
	                                      TaskGraphEdge<T,AT>,
	                                      TaskGraphSearchOptionsBuilder,
	                                      HierarchicalDag<TaskGraphNode<T,AT>,
	                                                      TaskGraphEdge<T,AT>,
	                                                      TaskGraphSearchOptionsBuilder>>

	   implements InternalHierarchicalDagBuilder<T,AT> {

	public InternalHierarchicalDagBuilderNtro() {
		super();
	}

	public InternalHierarchicalDagBuilderNtro(String graphName) {
		super(graphName);
	}

	@Override
	public TaskGraphEdge<T, AT> addEdge(TaskGraphNode<T, AT> fromNode, String edgeName, TaskGraphNode<T, AT> toNode) {

		return null;
	}

	@Override
	public TaskGraphEdge<T, AT> addEdge(TaskGraphNode<T, AT> fromNode, EdgeType edgeType, TaskGraphNode<T, AT> toNode) {

		return null;
	}


	@Override
	protected TaskGraphEdge<T, AT> createEdge(TaskGraphNode<T, AT> fromNode, EdgeType edgeType,
			TaskGraphNode<T, AT> toNode) {

		return null;
	}

	@Override
	protected TaskGraphNode<T, AT> createNodeBuilder(NodeId nodeId,
			GenericGraphBuilder<TaskGraphNode<T, AT>, TaskGraphEdge<T, AT>, TaskGraphSearchOptionsBuilder, GenericGraph<TaskGraphNode<T, AT>, TaskGraphEdge<T, AT>, TaskGraphSearchOptionsBuilder>> graphBuilder) {
		// TODO Auto-generated method stub
		return null;
	}
}
