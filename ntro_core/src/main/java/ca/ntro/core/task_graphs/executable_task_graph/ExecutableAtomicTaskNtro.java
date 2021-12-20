package ca.ntro.core.task_graphs.executable_task_graph;

import ca.ntro.core.task_graphs.task_graph.AtomicTaskNotifyer;
import ca.ntro.core.task_graphs.task_graph.AtomicTaskNtro;
import ca.ntro.core.values.ObjectMap;
import ca.ntro.core.values.ObjectMapNtro;
import ca.ntro.core.wrappers.future.ExceptionHandler;
import ca.ntro.core.wrappers.optionnal.Optionnal;
import ca.ntro.core.wrappers.optionnal.OptionnalNtro;
import ca.ntro.core.wrappers.result.Result;
import ca.ntro.core.wrappers.result.ResultNtro;

public class      ExecutableAtomicTaskNtro 

       extends    AtomicTaskNtro<ExecutableTaskNtro, ExecutableAtomicTaskNtro>

       implements ExecutableAtomicTask {
	
	
	private ExecuteHandler   executeHandler   = new ExecuteHandlerDefault();
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
	
	


	public void execute() {
		try {

			getExecuteHandler().execute((ObjectMap) parentTask().parentGraph(), this);

		}catch(Throwable t) {

			ExecutableAtomicTaskNtro.this.notifyTaskFailed(t); 
			((ExecutableTaskGraphNtro) ExecutableAtomicTaskNtro.this.parentTask().parentGraph()).notifyOfException(t);
		}
	}

	public void cancel() {
		try {

			getCancelHandler().cancel((ObjectMap) parentTask().parentGraph(), this);

		}catch(Throwable t) {

			ExecutableAtomicTaskNtro.this.notifyTaskFailed(t); 
			((ExecutableTaskGraphNtro) ExecutableAtomicTaskNtro.this.parentTask().parentGraph()).notifyOfException(t);
		}
	}
}
