package ca.ntro.core.task_graphs.task_graph;

public class MockAtomicTask extends AtomicTaskNtro<MockTask, MockAtomicTask> {

	public MockAtomicTask() {
		super();
	}

	public MockAtomicTask(String atomicTaskId) {
		super(atomicTaskId);
	}
	

	@Override
	public ResultIterator resultIterator() {
		// XXX: different type of tasks have different resultsIterator();
		throw new RuntimeException("TODO");
	}

}
