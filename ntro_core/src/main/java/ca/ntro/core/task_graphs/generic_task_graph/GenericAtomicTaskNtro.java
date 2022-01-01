package ca.ntro.core.task_graphs.generic_task_graph;

import java.util.HashSet;
import java.util.Set;

import ca.ntro.core.identifyers.Key;
import ca.ntro.core.task_graphs.generic_task_graph_trace.GenericAtomicTaskTraceNtro;

public abstract class GenericAtomicTaskNtro<T  extends GenericTask<T,AT>, 
                                            AT extends GenericAtomicTask<T,AT>>

       implements     GenericAtomicTask<T,AT> {
	
	private AtomicTaskId id;
	private GenericTaskNtro<T,AT> parentTask;
	private boolean isBlocked = false;
	
	private Set<GenericAtomicTaskTraceNtro> traces = new HashSet<>();

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

	public boolean getIsBlocked() {
		return isBlocked;
	}

	public void setIsBlocked(boolean isBlocked) {
		this.isBlocked = isBlocked;
	}

	public Set<GenericAtomicTaskTraceNtro> getTraces() {
		return traces;
	}

	public void setTraces(Set<GenericAtomicTaskTraceNtro> traces) {
		this.traces = traces;
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
	public boolean isBlocked() {
		return getIsBlocked();
	}

	@Override
	public void addResult(Object result) {
		setIsBlocked(false);
		for(GenericAtomicTaskTraceNtro trace : getTraces()) {
			trace.addResult(result);
		}
	}

	@Override
	public void clearResults() {
		for(GenericAtomicTaskTraceNtro trace : getTraces()) {
			trace.clearResults();
		}
	}

	@Override
	public void notifyWaitingForResult() {
		setIsBlocked(true);
	}
	
}
