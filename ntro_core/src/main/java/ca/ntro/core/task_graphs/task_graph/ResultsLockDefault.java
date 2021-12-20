package ca.ntro.core.task_graphs.task_graph;

import ca.ntro.core.values.ObjectMap;

public class ResultsLockDefault implements ResultsLock {
	
	private Task<?,?> parentTask;

	public Task<?, ?> getParentTask() {
		return parentTask;
	}

	public void setParentTask(Task<?, ?> parentTask) {
		this.parentTask = parentTask;
	}
	
	
	
	public ResultsLockDefault() {
	}
	
	public ResultsLockDefault(Task<?,?> parentTask) {
		setParentTask(parentTask);
	}



	@Override
	public boolean isBlockedWaitingForResults(ObjectMap results) {
		return false;
	}

}
