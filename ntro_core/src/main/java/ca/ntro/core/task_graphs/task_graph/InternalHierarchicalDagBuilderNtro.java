package ca.ntro.core.task_graphs.task_graph;

import ca.ntro.core.graphs.generics.generic_graph.EdgeType;
import ca.ntro.core.graphs.generics.generic_graph.GenericGraph;
import ca.ntro.core.graphs.generics.generic_graph.GenericGraphBuilder;
import ca.ntro.core.graphs.generics.generic_graph.GenericGraphStructure;
import ca.ntro.core.graphs.generics.generic_graph.GraphId;
import ca.ntro.core.graphs.generics.generic_graph.NodeId;
import ca.ntro.core.graphs.hierarchical_dag.HierarchicalDag;
import ca.ntro.core.graphs.hierarchical_dag.HierarchicalDagBuilderNtro;

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

	@Override
	protected TaskGraphEdge<T, AT> createEdge(TaskGraphNodeBuilder<T, AT> fromNode, EdgeType edgeType,
			TaskGraphNodeBuilder<T, AT> toNode) {
		// TODO Auto-generated method stub
		return null;
	}

}
