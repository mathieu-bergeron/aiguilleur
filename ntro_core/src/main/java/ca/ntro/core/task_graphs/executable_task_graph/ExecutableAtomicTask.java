package ca.ntro.core.task_graphs.executable_task_graph;

import ca.ntro.core.task_graphs.task_graph.AtomicTask;
import ca.ntro.core.wrappers.future.ExceptionHandler;

public interface ExecutableAtomicTask extends AtomicTask<ExecutableTaskNtro, ExecutableAtomicTaskNtro> {

	void execute(ExecuteHandler onStartHandler);

	void cancel(CancelHandler onCancelHandler);

	void handleException(ExceptionHandler exceptionHandler);

}
