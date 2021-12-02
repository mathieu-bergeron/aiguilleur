package ca.ntro.core.task_graphs.task_graph;

public class MockTask extends TaskNtro<MockTask, MockAtomicTask, TaskGraph<MockTask, MockAtomicTask>> {

	public MockTask(TaskId id, 
			        TaskGraphNodeBuilder<MockTask, MockAtomicTask, TaskGraph<MockTask, MockAtomicTask>> node,
			        TaskGraph<MockTask, MockAtomicTask> graph) {
		super(id, node, graph);
	}

}
