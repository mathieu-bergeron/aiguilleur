package ca.ntro.core.task_graphs.executable_task_graph;

import ca.ntro.core.task_graphs.task_graph.AtomicTask;
import ca.ntro.core.wrappers.future.ExceptionHandler;

public interface ExecutableAtomicTask extends AtomicTask<ExecutableTaskNtro, ExecutableAtomicTaskNtro> {

	void execute(ExecuteHandler executeHandler);

	void cancel(CancelHandler cancelHandler);

	void handleException(ExceptionHandler exceptionHandler);

}
