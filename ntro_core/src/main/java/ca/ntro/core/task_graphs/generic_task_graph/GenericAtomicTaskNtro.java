package ca.ntro.core.task_graphs.generic_task_graph;

import java.util.HashSet;
import java.util.Set;

import ca.ntro.core.identifyers.Key;
import ca.ntro.core.task_graphs.handlers.CancelHandler;
import ca.ntro.core.task_graphs.handlers.ExecuteHandler;
import ca.ntro.core.task_graphs.task_graph_trace.AtomicTaskTrace;
import ca.ntro.core.task_graphs.task_graph_trace.TaskGraphTraceNtro;
import ca.ntro.core.task_graphs.task_graph_trace.TaskTrace;
import ca.ntro.core.task_graphs.task_graph_trace.TaskTraceNtro;
import ca.ntro.core.wrappers.future.ExceptionHandler;

public abstract class GenericAtomicTaskNtro<T  extends GenericTask<T,AT>, 
                                            AT extends GenericAtomicTask<T,AT>>

       implements     GenericAtomicTask<T,AT> {
	
	private AtomicTaskId id;
	private GenericTaskNtro<T,AT> parentTask;

	private ExecuteHandler executeHandler;
	private CancelHandler cancelHandler;
	private ExceptionHandler exceptionHandler;
	
	private Set<TaskGraphTraceNtro> traces = new HashSet<>();

	public AtomicTaskId getId() {
		return id;
	}

	public void setId(AtomicTaskId id) {
		this.id = id;
	}

	public GenericTaskNtro<T, AT> getParentTask() {
		return parentTask;
	}

	public void setParentTask(GenericTaskNtro<T, AT> parentTask) {
		this.parentTask = parentTask;
	}

	public Set<TaskGraphTraceNtro> getTraces() {
		return traces;
	}

	public void setTraces(Set<TaskGraphTraceNtro> traces) {
		this.traces = traces;
	}

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
	
	
	
	
	
	
	

	public GenericAtomicTaskNtro() {
	}

	public GenericAtomicTaskNtro(String atomicTaskId) {
		setId(AtomicTaskId.fromKey(new Key(atomicTaskId)));
	}
	
	
	
	
	@Override
	public AtomicTaskId id() {
		return getId();
	}

	@SuppressWarnings("unchecked")
	@Override
	public T parentTask() {
		return (T) getParentTask();
	}

	@Override
	public void addResult(Object result) {
		for(TaskGraphTraceNtro trace : getTraces()) {
			trace.notifyNewResult(getId(), result);
		}
	}

	@Override
	public void clearResults() {
		for(TaskGraphTraceNtro trace : getTraces()) {
			trace.notifyClearResults(id);
		}
	}

	@Override
	public void notifyWaitingForResult() {
		for(TaskGraphTraceNtro trace : getTraces()) {
			trace.notifyWaitingForResult(getId());
		}
	}

	@Override
	public AtomicTaskTrace newTrace(TaskTrace parentTrace) {
		AtomicTaskTrace trace = newTraceInstance((TaskTraceNtro) parentTrace);
		
		getTraces().add(((TaskTraceNtro) parentTrace).getParentTrace());
		
		return trace;
	}

	protected abstract AtomicTaskTrace newTraceInstance(TaskTraceNtro parentTrace);
	
	@Override
	public void execute(ExecuteHandler executeHandler) {
		setExecuteHandler(executeHandler);
	}

	@Override
	public void cancel(CancelHandler cancelHandler) {
		setCancelHandler(cancelHandler);
	}

	@Override
	public void handleException(ExceptionHandler exceptionHandler) {
		setExceptionHandler(exceptionHandler);
	}

}
