package ca.ntro.core.task_graphs.task_graph;

import ca.ntro.core.identifyers.Key;
import ca.ntro.core.wrappers.result.Result;
import ca.ntro.core.wrappers.result.ResultNtro;

public class      AtomicTaskNtro<T  extends Task<T,AT>, 
                                 AT extends AtomicTask<T,AT>>

       implements AtomicTask<T,AT> {
	
	private AtomicTaskId id;
	private TaskNtro<T,AT> parentTask;
	private ResultNtro<Object> result = new ResultNtro<>();


	public AtomicTaskId getId() {
		return id;
	}

	public void setId(AtomicTaskId id) {
		this.id = id;
	}

	public TaskNtro<T,AT> getParentTask() {
		return parentTask;
	}

	public void setParentTask(TaskNtro<T,AT> parentTask) {
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

	@SuppressWarnings("unchecked")
	@Override
	public T parentTask() {
		return (T) getParentTask();
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
	public boolean isWaiting() {
		return getResult().hasException();
	}

	@Override
	public boolean isRunning() {
		return !isWaiting()
				&& !isDone();
	}

	@Override
	public boolean isDone() {
		return !isWaiting()
				&& getResult().hasValue();
	}
}
