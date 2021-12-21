package ca.ntro.core.task_graphs.task_graph;

import ca.ntro.core.identifyers.Key;
import ca.ntro.core.values.ObjectMap;

public class      AtomicTaskNtro<T  extends Task<T,AT>, 
                                 AT extends AtomicTask<T,AT>>

       implements AtomicTask<T,AT> {
	
	private AtomicTaskId id;
	private TaskNtro<T,AT> parentTask;
	private ResultsAccumulator resultsAccumulator = new ResultsAccumulatorNtro();
	private AtomicTaskState state = AtomicTaskState.BLOCKED;

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

	public ResultsAccumulator getResultsAccumulator() {
		return resultsAccumulator;
	}

	public void setResultsAccumulator(ResultsAccumulator resultsAccumulator) {
		this.resultsAccumulator = resultsAccumulator;
	}

	public AtomicTaskState getState() {
		return state;
	}

	public void setState(AtomicTaskState state) {
		this.state = state;
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
		return getState() == AtomicTaskState.BLOCKED;
	}

	@Override
	public boolean isInProgress() {
		return getState() == AtomicTaskState.IN_PROGRESS;
	}

	@Override
	public boolean isDone() {
		return getState() == AtomicTaskState.DONE;
	}

	@Override
	public boolean hasResult() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ObjectMap result() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addResult(Object value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clearResults() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyTaskBlocked() {
		setState(AtomicTaskState.BLOCKED);
		
	}

	@Override
	public void notifyTaskInProgress() {
		setState(AtomicTaskState.IN_PROGRESS);
	}

	@Override
	public void notifyTaskDone() {
		setState(AtomicTaskState.DONE);
	}
}
