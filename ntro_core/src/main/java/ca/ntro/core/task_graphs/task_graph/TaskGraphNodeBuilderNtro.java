package ca.ntro.core.task_graphs.task_graph;

import ca.ntro.core.graphs.generics.generic_graph.EdgeReducer;
import ca.ntro.core.graphs.generics.generic_graph.EdgeVisitor;
import ca.ntro.core.graphs.generics.generic_graph.GenericGraph;
import ca.ntro.core.graphs.generics.generic_graph.GenericGraphBuilder;
import ca.ntro.core.graphs.generics.generic_graph.GenericNodeStructure;
import ca.ntro.core.graphs.generics.generic_graph.NodeId;
import ca.ntro.core.graphs.generics.generic_graph.ReachableEdgeReducer;
import ca.ntro.core.graphs.generics.generic_graph.ReachableEdgeVisitor;
import ca.ntro.core.graphs.generics.generic_graph.ReachableNodeReducer;
import ca.ntro.core.graphs.generics.generic_graph.ReachableNodeVisitor;
import ca.ntro.core.graphs.generics.generic_graph.VisitedEdge;
import ca.ntro.core.graphs.generics.generic_graph.VisitedNode;
import ca.ntro.core.graphs.generics.generic_graph.WalkId;
import ca.ntro.core.graphs.generics.generic_graph.WalkInProgress;
import ca.ntro.core.graphs.generics.generic_graph.WalkReducer;
import ca.ntro.core.graphs.generics.generic_graph.WalkVisitor;
import ca.ntro.core.graphs.generics.generic_hierarchical_graph.GenericHierarchicalNodeBuilderNtro;
import ca.ntro.core.stream.Stream;
import ca.ntro.core.wrappers.result.Result;

public class TaskGraphNodeBuilderNtro<T  extends Task<T,AT>, 
                                      AT extends AtomicTask<T,AT>>

      extends GenericHierarchicalNodeBuilderNtro<TaskGraphNode<T,AT>,
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
		return getGraphBuilder().graph();
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

	protected GenericNodeStructure<TaskGraphNode<T, AT>, TaskGraphEdge<T, AT>, TaskGraphSearchOptionsBuilder> nodeStructure() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NodeId id() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isPartOfCycle() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Stream<TaskGraphEdge<T, AT>> edges() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void forEachEdge(
			EdgeVisitor<TaskGraphNode<T, AT>, TaskGraphEdge<T, AT>, TaskGraphSearchOptionsBuilder> visitor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <R> Result<R> reduceEdges(R initialValue,
			EdgeReducer<TaskGraphNode<T, AT>, TaskGraphEdge<T, AT>, TaskGraphSearchOptionsBuilder, R> reducer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Stream<VisitedNode<TaskGraphNode<T, AT>, TaskGraphEdge<T, AT>, TaskGraphSearchOptionsBuilder>> reachableNodes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Stream<VisitedNode<TaskGraphNode<T, AT>, TaskGraphEdge<T, AT>, TaskGraphSearchOptionsBuilder>> reachableNodes(
			TaskGraphSearchOptionsBuilder options) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void forEachReachableNode(
			ReachableNodeVisitor<TaskGraphNode<T, AT>, TaskGraphEdge<T, AT>, TaskGraphSearchOptionsBuilder> visitor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void forEachReachableNode(TaskGraphSearchOptionsBuilder options,
			ReachableNodeVisitor<TaskGraphNode<T, AT>, TaskGraphEdge<T, AT>, TaskGraphSearchOptionsBuilder> visitor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <R> Result<R> reduceReachableNodes(R initialValue,
			ReachableNodeReducer<TaskGraphNode<T, AT>, TaskGraphEdge<T, AT>, TaskGraphSearchOptionsBuilder, R> reducer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <R> Result<R> reduceReachableNodes(TaskGraphSearchOptionsBuilder options, R initialValue,
			ReachableNodeReducer<TaskGraphNode<T, AT>, TaskGraphEdge<T, AT>, TaskGraphSearchOptionsBuilder, R> reducer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Stream<VisitedEdge<TaskGraphNode<T, AT>, TaskGraphEdge<T, AT>, TaskGraphSearchOptionsBuilder>> reachableEdges() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Stream<VisitedEdge<TaskGraphNode<T, AT>, TaskGraphEdge<T, AT>, TaskGraphSearchOptionsBuilder>> reachableEdges(
			TaskGraphSearchOptionsBuilder options) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void forEachReachableEdge(
			ReachableEdgeVisitor<TaskGraphNode<T, AT>, TaskGraphEdge<T, AT>, TaskGraphSearchOptionsBuilder> visitor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void forEachReachableEdge(TaskGraphSearchOptionsBuilder options,
			ReachableEdgeVisitor<TaskGraphNode<T, AT>, TaskGraphEdge<T, AT>, TaskGraphSearchOptionsBuilder> visitor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <R> Result<R> reduceReachableEdges(R initialValue,
			ReachableEdgeReducer<TaskGraphNode<T, AT>, TaskGraphEdge<T, AT>, TaskGraphSearchOptionsBuilder, R> reducer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <R> Result<R> reduceReachableEdges(TaskGraphSearchOptionsBuilder options, R initialValue,
			ReachableEdgeReducer<TaskGraphNode<T, AT>, TaskGraphEdge<T, AT>, TaskGraphSearchOptionsBuilder, R> reducer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Stream<WalkInProgress<TaskGraphNode<T, AT>, TaskGraphEdge<T, AT>, TaskGraphSearchOptionsBuilder>> walk() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Stream<WalkInProgress<TaskGraphNode<T, AT>, TaskGraphEdge<T, AT>, TaskGraphSearchOptionsBuilder>> walk(
			TaskGraphSearchOptionsBuilder options) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void visitWalk(WalkId walk,
			WalkVisitor<TaskGraphNode<T, AT>, TaskGraphEdge<T, AT>, TaskGraphSearchOptionsBuilder> visitor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <R> Result<R> reduceWalk(WalkId walk, R initialValue,
			WalkReducer<TaskGraphNode<T, AT>, TaskGraphEdge<T, AT>, TaskGraphSearchOptionsBuilder, R> reducer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setNodeName(String nodeName) {
		// TODO Auto-generated method stub
		
	}


}
