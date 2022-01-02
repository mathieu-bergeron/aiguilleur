package ca.ntro.core.task_graphs.task_graph_trace;

import java.util.ArrayList;
import java.util.List;

import ca.ntro.core.task_graphs.generic_task_graph.GenericAtomicTask;
import ca.ntro.core.task_graphs.generic_task_graph.GenericAtomicTaskNtro;

public abstract class AtomicTaskTraceNtro 
       implements     AtomicTaskTrace {

	private GenericAtomicTaskNtro<?,?> task;
	private List<Object> results = new ArrayList<>();

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
	

	public AtomicTaskTraceNtro() {
	}

	public AtomicTaskTraceNtro(GenericAtomicTaskNtro<?,?> task) {
		setTask(task);
	}

	

	@Override
	public GenericAtomicTask<?, ?> task() {
		return getTask();
	}

	@Override
	public void addResult(Object value) {
		getResults().add(value);
	}

	@Override
	public void clearResults() {
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
		return getTask().getIsWaitingForResult();
	}

	@Override
	public boolean hasNext() {
		return getResults().size() > 1;
	}

	@Override
	public void advanceToNext() {
		setResults(getResults().subList(1, getResults().size()));
	}

}
