package ca.ntro.core.task_graphs.generic_task_graph;

import ca.ntro.core.task_graphs.handlers.CancelHandler;
import ca.ntro.core.task_graphs.handlers.ExecuteHandler;
import ca.ntro.core.task_graphs.task_graph_trace.AtomicTaskTrace;
import ca.ntro.core.task_graphs.task_graph_trace.TaskTrace;
import ca.ntro.core.wrappers.future.ExceptionHandler;

public interface GenericAtomicTask<T  extends GenericTask<T,AT>, 
                                   AT extends GenericAtomicTask<T,AT>> 

       extends   AtomicTaskMutator {
	
	AtomicTaskId id();
	T parentTask();

	void execute(ExecuteHandler executeHandler);
	void cancel(CancelHandler cancelHandler);
	void handleException(ExceptionHandler exceptionHandler);
	
	AtomicTaskTrace newTrace(TaskTrace parentTrace);

}
