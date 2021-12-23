package ca.ntro.core.task_graphs.task_graph;

import java.util.HashSet;
import java.util.Set;

import ca.ntro.core.identifyers.Key;
import ca.ntro.core.task_graphs.task_graph_trace.AtomicTaskTraceNtro;

public abstract class AtomicTaskNtro<T  extends Task<T,AT>, 
                      AT extends AtomicTask<T,AT>>

       implements     AtomicTask<T,AT> {
	
	private AtomicTaskId id;
	private TaskNtro<T,AT> parentTask;
	private boolean isBlocked = false;
	
	private Set<AtomicTaskTraceNtro> traces = new HashSet<>();

	public AtomicTaskId getId() {
		return id;
	}

	public void setId(AtomicTaskId id) {
		this.id = id;
	}

	public TaskNtro<T, AT> getParentTask() {
		return parentTask;
	}

	public void setParentTask(TaskNtro<T, AT> parentTask) {
		this.parentTask = parentTask;
	}

	public boolean getIsBlocked() {
		return isBlocked;
	}

	public void setIsBlocked(boolean isBlocked) {
		this.isBlocked = isBlocked;
	}

	public Set<AtomicTaskTraceNtro> getTraces() {
		return traces;
	}

	public void setTraces(Set<AtomicTaskTraceNtro> traces) {
		this.traces = traces;
	}
	
	
	
	
	
	
	

	public AtomicTaskNtro() {
	}

	public AtomicTaskNtro(String atomicTaskId) {
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
	public boolean isBlocked() {
		return getIsBlocked();
	}

	@Override
	public void addResult(Object result) {
		setIsBlocked(false);
		for(AtomicTaskTraceNtro trace : getTraces()) {
			trace.addResult(result);
		}
	}

	@Override
	public void clearResults() {
		for(AtomicTaskTraceNtro trace : getTraces()) {
			trace.clearResults();
		}
	}

	@Override
	public void notifyTaskBlocked() {
		setIsBlocked(true);
	}
}
