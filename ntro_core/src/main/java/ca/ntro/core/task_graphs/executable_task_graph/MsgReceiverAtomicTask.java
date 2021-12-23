package ca.ntro.core.task_graphs.executable_task_graph;

import ca.ntro.core.task_graphs.task_graph_trace.AtomicTaskTrace;

public class MsgReceiverAtomicTask extends ExecutableAtomicTaskNtro {

	@Override
	public AtomicTaskTrace newTrace() {
		// XXX: different type of atomic tasks
		//      have different type of resultsIterator
		throw new RuntimeException("TODO");
	}

}
