package ca.ntro.core.task_graphs.task_graph;

public class MockTask extends TaskNtro<MockTask, MockAtomicTask, MockTaskGraph> {

	public MockTask(TaskId id, 
			        TaskGraphNodeBuilder<MockTask, MockAtomicTask, MockTaskGraph> node,
			        MockTaskGraph graph) {

		super(id, node, graph);
	}

}
