package ca.ntro.core.task_graphs.task_graph;

import ca.ntro.core.task_graphs.generic_task_graph_trace.AtomicTaskTrace;

public class AtomicTaskEventHandler extends ExecutableAtomicTaskNtro {

	@Override
	public AtomicTaskTrace newTrace() {
		// XXX: different type of atomic tasks
		//      have different type of resultsIterator
		throw new RuntimeException("TODO");
	}

}
