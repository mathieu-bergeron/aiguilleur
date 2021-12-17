package ca.ntro.core.task_graphs.executable_task_graph;

import ca.ntro.core.task_graphs.task_graph.Task;

public interface ExecutableTask extends Task<ExecutableTaskNtro, ExecutableAtomicTaskNtro> {

	void execute();
	void suspend();
	void stop();

}
