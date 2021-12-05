package ca.ntro.core.task_graphs.task_graph;

import ca.ntro.core.graphs.EdgeReducer;
import ca.ntro.core.graphs.EdgeType;
import ca.ntro.core.graphs.EdgeVisitor;
import ca.ntro.core.graphs.GraphId;
import ca.ntro.core.graphs.NodeId;
import ca.ntro.core.graphs.NodeReducer;
import ca.ntro.core.graphs.NodeVisitor;
import ca.ntro.core.graphs.generic_graph.GenericGraph;
import ca.ntro.core.graphs.generic_graph.GenericGraphBuilder;
import ca.ntro.core.graphs.generic_graph.GenericGraphStructure;
import ca.ntro.core.graphs.hierarchical_dag.HierarchicalDag;
import ca.ntro.core.graphs.hierarchical_dag.HierarchicalDagBuilderNtro;
import ca.ntro.core.graphs.writers.GraphWriter;
import ca.ntro.core.wrappers.result.Result;

public class InternalHierarchicalDagBuilderNtro<T  extends Task<T,AT>, 
                                                AT extends AtomicTask<T,AT>>

       extends HierarchicalDagBuilderNtro<TaskGraphNode<T,AT>,
	                                      TaskGraphEdge<T,AT>,
	                                      TaskGraphSearchOptionsBuilder,
	                                      TaskGraphNodeBuilder<T,AT>,
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
	public GraphId id() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String label() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void write(GraphWriter writer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public TaskGraphSearchOptionsBuilder defaultSearchOptions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TaskGraphNode<T, AT> findNode(String nodeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TaskGraphNode<T, AT> findNode(NodeId nodeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void forEachStartNode(
			NodeVisitor<TaskGraphNode<T, AT>, TaskGraphEdge<T, AT>, TaskGraphSearchOptionsBuilder> visitor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <R> Result<R> reduceStartNodes(R initialValue,
			NodeReducer<TaskGraphNode<T, AT>, TaskGraphEdge<T, AT>, TaskGraphSearchOptionsBuilder, R> reducer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void forEachNode(
			NodeVisitor<TaskGraphNode<T, AT>, TaskGraphEdge<T, AT>, TaskGraphSearchOptionsBuilder> visitor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <R> Result<R> reduceNodes(R initialValue,
			NodeReducer<TaskGraphNode<T, AT>, TaskGraphEdge<T, AT>, TaskGraphSearchOptionsBuilder, R> reducer) {
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
	protected HierarchicalDag<TaskGraphNode<T, AT>, TaskGraphEdge<T, AT>, TaskGraphSearchOptionsBuilder> createGraph(
			GraphId id,
			GenericGraphStructure<TaskGraphNode<T, AT>, TaskGraphEdge<T, AT>, TaskGraphSearchOptionsBuilder> graphStructure) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected TaskGraphNodeBuilder<T, AT> createNodeBuilder(NodeId nodeId,
			GenericGraphBuilder<TaskGraphNode<T, AT>, TaskGraphEdge<T, AT>, TaskGraphSearchOptionsBuilder, TaskGraphNodeBuilder<T, AT>, GenericGraph<TaskGraphNode<T, AT>, TaskGraphEdge<T, AT>, TaskGraphSearchOptionsBuilder>> graphBuilder) {
		// TODO Auto-generated method stub
		return null;
	}

}
