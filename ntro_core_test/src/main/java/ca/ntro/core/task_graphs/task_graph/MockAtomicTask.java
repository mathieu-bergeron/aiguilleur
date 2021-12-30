package ca.ntro.core.task_graphs.task_graph;

import ca.ntro.core.task_graphs.generic_task_graph.GenericAtomicTaskNtro;
import ca.ntro.core.task_graphs.generic_task_graph_trace.AtomicTaskTrace;

public class MockAtomicTask extends GenericAtomicTaskNtro<MockTask, MockAtomicTask> {

	public MockAtomicTask() {
		super();
	}

	public MockAtomicTask(String atomicTaskId) {
		super(atomicTaskId);
	}
	

	@Override
	public AtomicTaskTrace newTrace() {
		// XXX: different type of tasks have different traces
		throw new RuntimeException("TODO");
	}

}
