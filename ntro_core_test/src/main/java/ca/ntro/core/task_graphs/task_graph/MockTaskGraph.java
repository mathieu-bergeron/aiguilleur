package ca.ntro.core.task_graphs.task_graph;

import ca.ntro.core.graphs.hierarchical_dag.HierarchicalDagNodeBuilder;

public class MockTaskGraph extends TaskGraphNtro<MockTask, MockAtomicTask> {

	public MockTaskGraph() {
		super();
	}

	public MockTaskGraph(String graphName) {
		super(graphName);
	}

	@Override
	protected MockTask createTaskImpl(TaskId id,
			HierarchicalDagNodeBuilder<TaskGraphNode<MockTask, MockAtomicTask>, TaskGraphEdge<MockTask, MockAtomicTask>> node,
			TaskGraph<MockTask, MockAtomicTask> graph) {
		
		return new MockTask(id,node,graph);
	}



}
