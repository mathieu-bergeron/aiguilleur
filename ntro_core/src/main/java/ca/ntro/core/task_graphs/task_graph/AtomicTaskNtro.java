package ca.ntro.core.task_graphs.task_graph;

import ca.ntro.core.wrappers.result.Result;

public class      AtomicTaskNtro<T  extends Task<T,AT,TG>, 
                                 AT extends AtomicTask<T,AT,TG>,
                                 TG extends TaskGraph<T,AT>> 

       implements AtomicTask<T,AT,TG> {
	
	private AtomicTaskId id;
	private T parentTask;
	private Result<?> result;

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

	public Result<?> getResult() {
		return result;
	}

	public void setResult(Result<?> result) {
		this.result = result;
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
	public <R> void registerResult(Result<R> result) {
		setResult(result);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <R> Result<R> result() {
		return (Result<R>) getResult();
	}
}
