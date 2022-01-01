package ca.ntro.core.task_graphs.task_graph;

import ca.ntro.core.wrappers.future.ExceptionHandler;

public class AtomicTaskExecutor {

	private ExecuteHandler executeHandler;
	private CancelHandler cancelHandler;
	private ExceptionHandler exceptionHandler;
	
	
	public ExecuteHandler getExecuteHandler() {
		return executeHandler;
	}

	public void setExecuteHandler(ExecuteHandler executeHandler) {
		this.executeHandler = executeHandler;
	}

	public CancelHandler getCancelHandler() {
		return cancelHandler;
	}

	public void setCancelHandler(CancelHandler cancelHandler) {
		this.cancelHandler = cancelHandler;
	}

	public ExceptionHandler getExceptionHandler() {
		return exceptionHandler;
	}

	public void setExceptionHandler(ExceptionHandler exceptionHandler) {
		this.exceptionHandler = exceptionHandler;
	}
	


}
