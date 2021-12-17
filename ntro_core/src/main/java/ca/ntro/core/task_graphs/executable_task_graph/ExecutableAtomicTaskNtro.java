package ca.ntro.core.task_graphs.executable_task_graph;

import ca.ntro.core.task_graphs.executable_task_graph.handlers.Notifyer;
import ca.ntro.core.task_graphs.executable_task_graph.handlers.OnResumeHandler;
import ca.ntro.core.task_graphs.executable_task_graph.handlers.OnResumeHandlerNull;
import ca.ntro.core.task_graphs.executable_task_graph.handlers.OnStartHandler;
import ca.ntro.core.task_graphs.executable_task_graph.handlers.OnStartHandlerNull;
import ca.ntro.core.task_graphs.executable_task_graph.handlers.OnStopHandler;
import ca.ntro.core.task_graphs.executable_task_graph.handlers.OnStopHandlerNull;
import ca.ntro.core.task_graphs.executable_task_graph.handlers.OnSuspendHandler;
import ca.ntro.core.task_graphs.executable_task_graph.handlers.OnSuspendHandlerNull;
import ca.ntro.core.task_graphs.task_graph.AtomicTaskNtro;
import ca.ntro.core.values.ObjectMap;
import ca.ntro.core.values.ObjectMapNtro;
import ca.ntro.core.wrappers.optionnal.Optionnal;
import ca.ntro.core.wrappers.optionnal.OptionnalNtro;
import ca.ntro.core.wrappers.result.Result;
import ca.ntro.core.wrappers.result.ResultNtro;

public class      ExecutableAtomicTaskNtro 

       extends    AtomicTaskNtro<ExecutableTaskNtro, ExecutableAtomicTaskNtro>

       implements ExecutableAtomicTask {
	
	
	private OnStartHandler   onStartHandler   = new OnStartHandlerNull();
	private OnSuspendHandler onSuspendHandler = new OnSuspendHandlerNull();
	private OnResumeHandler  onResumeHandler  = new OnResumeHandlerNull();
	private OnStopHandler    onStopHandler    = new OnStopHandlerNull();

	public OnStartHandler getOnStartHandler() {
		return onStartHandler;
	}

	public void setOnStartHandler(OnStartHandler onStartHandler) {
		this.onStartHandler = onStartHandler;
	}

	public OnSuspendHandler getOnSuspendHandler() {
		return onSuspendHandler;
	}

	public void setOnSuspendHandler(OnSuspendHandler onSuspendHandler) {
		this.onSuspendHandler = onSuspendHandler;
	}

	public OnResumeHandler getOnResumeHandler() {
		return onResumeHandler;
	}

	public void setOnResumeHandler(OnResumeHandler onResumeHandler) {
		this.onResumeHandler = onResumeHandler;
	}

	public OnStopHandler getOnStopHandler() {
		return onStopHandler;
	}

	public void setOnStopHandler(OnStopHandler onStopHandler) {
		this.onStopHandler = onStopHandler;
	}
	
	
	
	
	public ExecutableAtomicTaskNtro() {
	}

	

	@Override
	public void onStart(OnStartHandler onStartHandler) {
		setOnStartHandler(onStartHandler);
	}

	@Override
	public void onSuspend(OnSuspendHandler onSuspendHandler) {
		setOnSuspendHandler(onSuspendHandler);
	}

	@Override
	public void onResume(OnResumeHandler onResumeHandler) {
		setOnResumeHandler(onResumeHandler);
	}

	@Override
	public void onStop(OnStopHandler onStopHandler) {
		setOnStopHandler(onStopHandler);
	}

	@Override
	public void start() {
		try {

			getOnStartHandler().start((ObjectMap) parentTask().parentGraph(), value -> {

				ExecutableAtomicTaskNtro.this.registerResult(value);
				((ExecutableTaskGraphNtro) ExecutableAtomicTaskNtro.this.parentTask().parentGraph()).notifyOfNewResult();
			});

		}catch(Throwable t) {
			ExecutableAtomicTaskNtro.this.registerException(t); 
			((ExecutableTaskGraphNtro) ExecutableAtomicTaskNtro.this.parentTask().parentGraph()).notifyOfException(t);
		}
	}

	@Override
	public void suspend() {
		throw new RuntimeException("TODO");
	}

	@Override
	public void resume() {
		throw new RuntimeException("TODO");
	}

	@Override
	public void stop() {
		throw new RuntimeException("TODO");
	}
}
