package ca.ntro.core.task_graphs.task_graph;

import ca.ntro.core.task_graphs.generic_task_graph.GenericAtomicTaskMessageHandler;
import ca.ntro.core.task_graphs.generic_task_graph.GenericAtomicTaskNtro;

public class MockAtomicTask extends GenericAtomicTaskMessageHandler<MockTask, MockAtomicTask> {

	public MockAtomicTask() {
		super();
	}

	public MockAtomicTask(String atomicTaskId) {
		super(atomicTaskId);
	}
	

}
