package ca.ntro.core.task_graphs.task_graph;

import ca.ntro.core.graphs.EdgeType;
import ca.ntro.core.graphs.NodeId;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TaskGraphEdge<T, AT> addEdge(TaskGraphNode<T, AT> fromNode, EdgeType edgeType, TaskGraphNode<T, AT> toNode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected TaskGraphNode<T, AT> createNode(NodeId nodeId) {
		// TODO Auto-generated method stub
		return null;
	}
}
