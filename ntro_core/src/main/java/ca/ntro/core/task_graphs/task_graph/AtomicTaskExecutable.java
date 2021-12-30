package ca.ntro.core.task_graphs.task_graph;

import ca.ntro.core.task_graphs.generic_task_graph.GenericAtomicTask;
import ca.ntro.core.wrappers.future.ExceptionHandler;

public interface AtomicTaskExecutable extends GenericAtomicTask<ExecutableTaskNtro, ExecutableAtomicTaskNtro> {

	void execute(ExecuteHandler executeHandler);

	void cancel(CancelHandler cancelHandler);

	void handleException(ExceptionHandler exceptionHandler);

}
