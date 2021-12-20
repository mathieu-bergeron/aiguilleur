package ca.ntro.core.task_graphs.executable_task_graph;

import ca.ntro.core.task_graphs.executable_task_graph.handlers.OnNewResultsHandler;
import ca.ntro.core.task_graphs.executable_task_graph.handlers.OnStopHandler;
import ca.ntro.core.task_graphs.task_graph.AtomicTask;
import ca.ntro.core.wrappers.future.ExceptionHandler;

public interface ExecutableAtomicTask extends AtomicTask<ExecutableTaskNtro, ExecutableAtomicTaskNtro> {
	

	void onNewResults(OnNewResultsHandler onNewResultsHandler);

	void onStop(OnStopHandler onStopHandler);
	
	void handleException(ExceptionHandler exceptionHandler);

}
