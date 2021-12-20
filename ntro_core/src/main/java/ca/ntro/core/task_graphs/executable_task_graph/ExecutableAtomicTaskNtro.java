package ca.ntro.core.task_graphs.executable_task_graph;

import ca.ntro.core.task_graphs.executable_task_graph.handlers.OnResumeHandler;
import ca.ntro.core.task_graphs.executable_task_graph.handlers.OnResumeHandlerNull;
import ca.ntro.core.task_graphs.executable_task_graph.handlers.OnStopHandler;
import ca.ntro.core.task_graphs.executable_task_graph.handlers.OnStopHandlerNull;
import ca.ntro.core.task_graphs.executable_task_graph.handlers.OnSuspendHandler;
import ca.ntro.core.task_graphs.executable_task_graph.handlers.OnSuspendHandlerNull;
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
	
	
	private OnResultsChangedHandler onResultsChangedHandler = new OnResultsChangedNull();
	private ExceptionHandler        exceptionHandler        = new ExceptionHandlerDefault();
	
	
	
	public ExecutableAtomicTaskNtro() {

	}


	public void start() {
		try {

			getOnStartHandler().start((ObjectMap) parentTask().parentGraph(), value -> {

				ExecutableAtomicTaskNtro.this.registerNewResult(value);
				((ExecutableTaskGraphNtro) ExecutableAtomicTaskNtro.this.parentTask().parentGraph()).notifyOfNewResult();
			});

		}catch(Throwable t) {
			ExecutableAtomicTaskNtro.this.notifyTaskFailed(t); 
			((ExecutableTaskGraphNtro) ExecutableAtomicTaskNtro.this.parentTask().parentGraph()).notifyOfException(t);
		}
	}

	public void suspend() {
		throw new RuntimeException("TODO");
	}

	public void resume() {
		throw new RuntimeException("TODO");
	}

	public void notifyTaskIsDone() {
		throw new RuntimeException("TODO");
	}
}
