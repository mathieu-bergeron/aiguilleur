package ca.ntro.core.task_graphs.task_graph;

import ca.ntro.core.identifyers.Key;

public abstract class AtomicTaskNtro<T  extends Task<T,AT>, 
                      AT extends AtomicTask<T,AT>>

       implements     AtomicTask<T,AT> {
	
	private AtomicTaskId id;
	private TaskNtro<T,AT> parentTask;
	private ResultAccumulatorDefault resultAccumulator = new ResultAccumulatorDefault();
	private boolean isBlocked = false;

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

	public ResultAccumulatorDefault getResultAccumulator() {
		return resultAccumulator;
	}

	public void setResultAccumulator(ResultAccumulatorDefault resultAccumulator) {
		this.resultAccumulator = resultAccumulator;
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
	public boolean isInProgress() {
		return !isBlocked()
				&& !isDone();
	}

	@Override
	public boolean isDone() {
		return !getResultAccumulator().hasNextResult();
	}

	@Override
	public void addResult(Object result) {
		setIsBlocked(false);
		getResultAccumulator().addResult(result);
	}

	@Override
	public void clearResults() {
		getResultAccumulator().clearResults();
	}

	@Override
	public void notifyTaskBlocked() {
		setIsBlocked(true);
	}
}
