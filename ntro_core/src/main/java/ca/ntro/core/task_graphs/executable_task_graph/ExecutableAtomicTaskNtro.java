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
	public boolean start() {
		OptionnalNtro<Boolean> hasChanged = new OptionnalNtro<>(false);

		getOnStartHandler().start((ObjectMap) parentTask().parentGraph(), new Notifyer() {

			@Override
			public void registerResult(Object value) {
				ExecutableAtomicTaskNtro.this.registerResult(value);
				hasChanged.registerValue(true);
				
				((ExecutableTaskGraphNtro) ExecutableAtomicTaskNtro.this.parentTask().parentGraph()).notifyOfChange();
			}

			@Override
			public void registerException(Throwable t) {
				ExecutableAtomicTaskNtro.this.registerException(t);

				((ExecutableTaskGraphNtro) ExecutableAtomicTaskNtro.this.parentTask().parentGraph()).notifyOfChange();
			}
		});

		return hasChanged.value();
	}

	@Override
	public boolean suspend() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean resume() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean stop() {
		// TODO Auto-generated method stub
		return false;
	}
}
