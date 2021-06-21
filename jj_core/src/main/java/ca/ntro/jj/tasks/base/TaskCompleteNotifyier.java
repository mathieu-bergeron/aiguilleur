package ca.ntro.jj.tasks.base;

import ca.ntro.jj.tasks.results.AtomicTaskResult;

public interface TaskCompleteNotifyier {
	
	void taskComplete();
	void taskComplete(AtomicTaskResult atomicTaskResult);

}
