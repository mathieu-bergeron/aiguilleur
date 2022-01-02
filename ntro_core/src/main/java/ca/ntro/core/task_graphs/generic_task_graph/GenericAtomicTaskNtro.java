package ca.ntro.core.task_graphs.generic_task_graph;

import java.util.HashSet;
import java.util.Set;

import ca.ntro.core.identifyers.Key;
import ca.ntro.core.task_graphs.task_graph_trace.AtomicTaskTraceNtro;
import ca.ntro.core.task_graphs.task_graph_trace.TaskTrace;

public abstract class GenericAtomicTaskNtro<T  extends GenericTask<T,AT>, 
                                            AT extends GenericAtomicTask<T,AT>>

       implements     GenericAtomicTask<T,AT> {
	
	private AtomicTaskId id;
	private GenericTaskNtro<T,AT> parentTask;
	private boolean isWaitingForResult = false;
	
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
	public boolean isBlocked(TaskTrace trace) {
		return false;
	}

	@Override
	public boolean isInProgress(TaskTrace trace) {
		return getIsWaitingForResult();
	}

	@Override
	public boolean isDone(TaskTrace trace) {
		return trace.hasCurrent() 
				&& trace.current().contains(this.id)
				&& !trace.hasNext();
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

}
