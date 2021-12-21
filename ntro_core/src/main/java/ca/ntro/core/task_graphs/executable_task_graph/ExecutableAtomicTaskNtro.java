package ca.ntro.core.task_graphs.executable_task_graph;

import ca.ntro.core.task_graphs.task_graph.AtomicTaskNtro;
import ca.ntro.core.values.ObjectMap;
import ca.ntro.core.wrappers.future.ExceptionHandler;

public class      ExecutableAtomicTaskNtro 

       extends    AtomicTaskNtro<ExecutableTaskNtro, ExecutableAtomicTaskNtro>

       implements ExecutableAtomicTask {
	
	
	private ExecuteHandler   executeHandler   = new ExecuteHandlerNull();
	private CancelHandler    cancelHandler  = new CancelHandlerDefault();
	private ExceptionHandler exceptionHandler = new ExceptionHandlerDefault();
	
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

	
	
	

	public ExecutableAtomicTaskNtro() {
	}
	
	

	@Override
	public void execute(ExecuteHandler onStartHandler) {
		setExecuteHandler(onStartHandler);
	}

	@Override
	public void cancel(CancelHandler onCancelHandler) {
		setCancelHandler(onCancelHandler);
	}

	@Override
	public void handleException(ExceptionHandler exceptionHandler) {
		setExceptionHandler(exceptionHandler);
	}
	
	@Override
	public void addResult(Object result){
		super.addResult(result);

		((ExecutableTaskGraphNtro) ExecutableAtomicTaskNtro.this.parentTask().parentGraph()).notifyOfNewResult();
	}

	public void execute(ObjectMap results) {
		try {

			getExecuteHandler().execute(results, this);

		}catch(Throwable t) {
			
			getExceptionHandler().handle(t);
			((ExecutableTaskGraphNtro) ExecutableAtomicTaskNtro.this.parentTask().parentGraph()).notifyOfException(t);
		}
	}

	public void cancel(ObjectMap results) {
		try {

			getCancelHandler().cancel(results, this);

		}catch(Throwable t) {

			getExceptionHandler().handle(t);
			((ExecutableTaskGraphNtro) ExecutableAtomicTaskNtro.this.parentTask().parentGraph()).notifyOfException(t);
		}
	}
}
