package ca.ntro.core.task_graphs.task_graph;

import ca.ntro.core.task_graphs.generic_task_graph.GenericAtomicTask;
import ca.ntro.core.task_graphs.handlers.CancelHandler;
import ca.ntro.core.task_graphs.handlers.ExecuteHandler;
import ca.ntro.core.wrappers.future.ExceptionHandler;

public interface AtomicTask extends GenericAtomicTask<Task, AtomicTask> {
	
	void execute(ExecuteHandler executeHandler);
	void cancel(CancelHandler cancelHandler);
	void handleException(ExceptionHandler exceptionHandler);

}
