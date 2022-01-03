package ca.ntro.core.task_graphs.task_graph;

import ca.ntro.core.task_graphs.generic_task_graph.GenericAtomicTaskEventHandler;
import ca.ntro.core.task_graphs.generic_task_graph.handlers.CancelHandler;
import ca.ntro.core.task_graphs.generic_task_graph.handlers.ExecuteHandler;
import ca.ntro.core.wrappers.future.ExceptionHandler;

public class      AtomicTaskEventHandler 

       extends    GenericAtomicTaskEventHandler<Task, AtomicTask> 

       implements AtomicTask {

	private AtomicTaskExecutor executor = new AtomicTaskExecutor();

	public AtomicTaskExecutor getExecutor() {
		return executor;
	}

	public void setExecutor(AtomicTaskExecutor executor) {
		this.executor = executor;
	}

	@Override
	public void execute(ExecuteHandler executeHandler) {
		getExecutor().setExecuteHandler(executeHandler);
	}

	@Override
	public void cancel(CancelHandler cancelHandler) {
		getExecutor().setCancelHandler(cancelHandler);
	}

	@Override
	public void handleException(ExceptionHandler exceptionHandler) {
		getExecutor().setExceptionHandler(exceptionHandler);
	}



}
