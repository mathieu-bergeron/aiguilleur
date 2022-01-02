package ca.ntro.core.task_graphs.task_graph_trace;

import ca.ntro.core.task_graphs.generic_task_graph.AtomicTaskStateMutator;

public interface AtomicTaskTraceMutator extends AtomicTaskStateMutator {

	void notifyCurrentResultWasUsed();
	void notifyCurrentResultCouldNotBeUsed();

}
