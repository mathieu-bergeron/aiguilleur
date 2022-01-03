package ca.ntro.core.task_graphs.generic_task_graph;

import java.util.HashSet;
import java.util.Set;

import ca.ntro.core.identifyers.Key;
import ca.ntro.core.task_graphs.generic_task_graph.handlers.CancelHandler;
import ca.ntro.core.task_graphs.generic_task_graph.handlers.ExecuteHandler;
import ca.ntro.core.task_graphs.task_graph_trace.AtomicTaskTrace;
import ca.ntro.core.task_graphs.task_graph_trace.AtomicTaskTraceNtro;
import ca.ntro.core.task_graphs.task_graph_trace.TaskTrace;
import ca.ntro.core.task_graphs.task_graph_trace.TaskTraceNtro;
import ca.ntro.core.wrappers.future.ExceptionHandler;

public abstract class GenericAtomicTaskNtro<T  extends GenericTask<T,AT>, 
                                            AT extends GenericAtomicTask<T,AT>>

       implements     GenericAtomicTask<T,AT> {
	
	private AtomicTaskId id;
	private GenericTaskNtro<T,AT> parentTask;
	private boolean isWaitingForResult = false;

	private ExecuteHandler executeHandler;
	private CancelHandler cancelHandler;
	private ExceptionHandler exceptionHandler;
	
	private Set<AtomicTaskTraceNtro> traces = new HashSet<>();

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

	public boolean getIsWaitingForResult() {
		return isWaitingForResult;
	}

	public void setIsWaitingForResult(boolean isWaitingForResult) {
		this.isWaitingForResult = isWaitingForResult;
	}

	public Set<AtomicTaskTraceNtro> getTraces() {
		return traces;
	}

	public void setTraces(Set<AtomicTaskTraceNtro> traces) {
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
		setIsWaitingForResult(false);
		for(AtomicTaskTraceNtro trace : getTraces()) {
			trace.addResult(result);
		}
	}

	@Override
	public void clearResults() {
		setIsWaitingForResult(false);
		for(AtomicTaskTraceNtro trace : getTraces()) {
			trace.clearResults();
		}
	}

	@Override
	public void notifyWaitingForResult() {
		setIsWaitingForResult(true);
	}

	@Override
	public AtomicTaskTrace newTrace(TaskTrace parentTrace) {
		AtomicTaskTrace trace = newTraceInstance((TaskTraceNtro) parentTrace);
		
		getTraces().add((AtomicTaskTraceNtro) trace);
		
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
