package ca.ntro.core.task_graphs.task_graph_trace;

import java.util.ArrayList;
import java.util.List;

import ca.ntro.core.task_graphs.generic_task_graph.GenericAtomicTask;
import ca.ntro.core.task_graphs.generic_task_graph.GenericAtomicTaskNtro;

public abstract class AtomicTaskTraceNtro 
       implements     AtomicTaskTrace {

	private GenericAtomicTaskNtro<?,?> task;
	private TaskTraceNtro parentTrace;
	private List<Object> results = new ArrayList<>();
	private boolean isWaiting = false;

	public GenericAtomicTaskNtro<?, ?> getTask() {
		return task;
	}

	public void setTask(GenericAtomicTaskNtro<?, ?> task) {
		this.task = task;
	}
	
	public List<Object> getResults() {
		return results;
	}

	public void setResults(List<Object> results) {
		this.results = results;
	}

	public TaskTraceNtro getParentTrace() {
		return parentTrace;
	}

	public void setParentTrace(TaskTraceNtro parentTrace) {
		this.parentTrace = parentTrace;
	}

	public void setIsWaiting(boolean isWaiting) {
		this.isWaiting = isWaiting;
	}

	public boolean getIsWaiting() {
		return isWaiting;
	}

	public AtomicTaskTraceNtro() {
	}

	public AtomicTaskTraceNtro(GenericAtomicTaskNtro<?,?> task, TaskTraceNtro parentTask) {
		setTask(task);
		setParentTrace(parentTask);
	}

	

	@Override
	public GenericAtomicTask<?, ?> task() {
		return getTask();
	}

	@Override
	public void addResult(Object value) {
		getParentTrace().getParentTrace().notifyNewResult(getTask().id(), value);
	}

	public void silentlyAddResult(Object value) {
		setIsWaiting(false);
		getResults().add(value);
	}

	@Override
	public void clearResults() {
		getParentTrace().getParentTrace().notifyClearResults(getTask().id());
		getResults().clear();
	}

	@Override
	public boolean hasCurrent() {
		return getResults().size() > 0;
	}

	@Override
	public Object current() {
		Object result = null;
		
		if(hasCurrent()) {
			result = getResults().get(0);
		}
		
		return result;
	}

	@Override
	public boolean isWaiting() {
		return getIsWaiting();
	}

	@Override
	public boolean hasNext() {
		return getResults().size() > 1;
	}

	@Override
	public void advanceToNext() {
		if(getResults().size() > 1) {

			setResults(getResults().subList(1, getResults().size()));

		}else {

			getResults().clear();

		}
	}

	@Override
	public void notifyWaitingForResult() {
		getParentTrace().getParentTrace().notifyWaitingForResult(getTask().id());
	}

}
