package ca.ntro.core.task_graphs.task_graph;

import ca.ntro.core.graphs.NodeId;
import ca.ntro.core.graphs.ReachableNodeReducer;
import ca.ntro.core.graphs.ReachableNodeVisitor;
import ca.ntro.core.graphs.generic_graph.GenericGraph;
import ca.ntro.core.graphs.generic_graph.GenericGraphBuilder;
import ca.ntro.core.graphs.generic_graph.GenericNodeStructure;
import ca.ntro.core.graphs.hierarchical_graph.HierarchicalGraphBuilder;
import ca.ntro.core.graphs.hierarchical_graph.HierarchicalNodeBuilderNtro;
import ca.ntro.core.wrappers.result.Result;

public class TaskGraphNodeBuilderNtro<T  extends Task<T,AT>, 
                                      AT extends AtomicTask<T,AT>>

      extends HierarchicalNodeBuilderNtro<TaskGraphNode<T,AT>,
                                          TaskGraphEdge<T,AT>,
                                          TaskGraphSearchOptionsBuilder,
                                          TaskGraphNodeBuilder<T,AT>>

	  implements TaskGraphNodeBuilder<T,AT> {


	public TaskGraphNodeBuilderNtro(NodeId nodeId,
			                        GenericGraphBuilder<TaskGraphNode<T, AT>, 
			                                            TaskGraphEdge<T, AT>, 
			                                            TaskGraphSearchOptionsBuilder, 
			                                            TaskGraphNodeBuilder<T, AT>, 
			                                            GenericGraph<TaskGraphNode<T, AT>, 
			                                            TaskGraphEdge<T, AT>, 
			                                            TaskGraphSearchOptionsBuilder>> graphBuilder) {
		super(nodeId, graphBuilder);
	}

	private T task;

	public T getTask() {
		return task;
	}

	public void setTask(T task) {
		this.task = task;
	}

	@Override
	public T task() {
		return getTask();
	}

	@Override
	public GenericGraph<TaskGraphNode<T, AT>, TaskGraphEdge<T, AT>, TaskGraphSearchOptionsBuilder> parentGraph() {
		return getGraphBuilder().asGraph();
	}

	@Override
	public boolean hasSubNodes() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasParent() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public TaskGraphNode<T, AT> parent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void forEachSubNode(
			ReachableNodeVisitor<TaskGraphNode<T, AT>, TaskGraphEdge<T, AT>, TaskGraphSearchOptionsBuilder> visitor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void forEachSubNode(TaskGraphSearchOptionsBuilder options,
			ReachableNodeVisitor<TaskGraphNode<T, AT>, TaskGraphEdge<T, AT>, TaskGraphSearchOptionsBuilder> visitor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <R> Result<R> reduceSubNodes(R initialValue,
			ReachableNodeReducer<TaskGraphNode<T, AT>, TaskGraphEdge<T, AT>, TaskGraphSearchOptionsBuilder, R> reducer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <R> Result<R> reduceSubNodes(TaskGraphSearchOptionsBuilder options, R initialValue,
			ReachableNodeReducer<TaskGraphNode<T, AT>, TaskGraphEdge<T, AT>, TaskGraphSearchOptionsBuilder, R> reducer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void forEachParentNode(
			ReachableNodeVisitor<TaskGraphNode<T, AT>, TaskGraphEdge<T, AT>, TaskGraphSearchOptionsBuilder> visitor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void forEachParentNode(TaskGraphSearchOptionsBuilder options,
			ReachableNodeVisitor<TaskGraphNode<T, AT>, TaskGraphEdge<T, AT>, TaskGraphSearchOptionsBuilder> visitor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <R> Result<R> reduceParentNodes(R initialValue,
			ReachableNodeReducer<TaskGraphNode<T, AT>, TaskGraphEdge<T, AT>, TaskGraphSearchOptionsBuilder, R> reducer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <R> Result<R> reduceParentNodes(TaskGraphSearchOptionsBuilder options, R initialValue,
			ReachableNodeReducer<TaskGraphNode<T, AT>, TaskGraphEdge<T, AT>, TaskGraphSearchOptionsBuilder, R> reducer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected GenericNodeStructure<TaskGraphNode<T, AT>, TaskGraphEdge<T, AT>, TaskGraphSearchOptionsBuilder> nodeStructure() {
		// TODO Auto-generated method stub
		return null;
	}


}
