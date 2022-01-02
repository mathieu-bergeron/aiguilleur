package ca.ntro.core.task_graphs.task_graph_trace;

import ca.ntro.core.task_graphs.generic_task_graph.AtomicTaskMutator;

public interface AtomicTaskTraceMutator extends AtomicTaskMutator {

	void notifyCurrentResultWasUsed();
	void notifyCurrentResultCouldNotBeUsed();

}
