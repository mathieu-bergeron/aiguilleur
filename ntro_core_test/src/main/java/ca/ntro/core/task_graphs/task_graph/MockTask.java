package ca.ntro.core.task_graphs.task_graph;

import ca.ntro.core.graphs.hierarchical_dag.HierarchicalDagNodeBuilder;

public class MockTask extends TaskNtro<MockTask, MockAtomicTask> {

	public MockTask(TaskId id,
			HierarchicalDagNodeBuilder<TaskGraphNode<MockTask, MockAtomicTask>, TaskGraphEdge<MockTask, MockAtomicTask>> node,
			TaskGraph<MockTask, MockAtomicTask> graph) {

		super(id, node, graph);
	}


}
