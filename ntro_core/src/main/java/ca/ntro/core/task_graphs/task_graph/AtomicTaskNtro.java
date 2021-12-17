package ca.ntro.core.task_graphs.task_graph;

import ca.ntro.core.identifyers.Key;
import ca.ntro.core.wrappers.result.Result;
import ca.ntro.core.wrappers.result.ResultNtro;

public class      AtomicTaskNtro<T  extends Task<T,AT>, 
                                 AT extends AtomicTask<T,AT>>

       implements AtomicTask<T,AT> {
	
	private AtomicTaskId id;
	private T parentTask;
	private ResultNtro<Object> result = new ResultNtro<>();


	public AtomicTaskId getId() {
		return id;
	}

	public void setId(AtomicTaskId id) {
		this.id = id;
	}

	public T getParentTask() {
		return parentTask;
	}

	public void setParentTask(T parentTask) {
		this.parentTask = parentTask;
	}

	public ResultNtro<Object> getResult() {
		return result;
	}

	public void setResult(ResultNtro<Object> result) {
		this.result = result;
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

	@Override
	public T parentTask() {
		return getParentTask();
	}

	@Override
	public void registerResult(Object value) {
		getResult().registerValue(value);
	}

	@Override
	public void registerException(Throwable t) {
		getResult().registerException(t);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <R> Result<R> result() {
		return (Result<R>) getResult();
	}

	@Override
	public boolean isBlocked() {
		return getResult().hasException();
	}

	@Override
	public boolean isInProgress() {
		return !isBlocked()
				&& !isDone();
	}

	@Override
	public boolean isDone() {
		return !isBlocked()
				&& getResult().hasValue();
	}
}
