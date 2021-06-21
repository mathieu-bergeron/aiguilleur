package ca.ntro.jj.tasks;

import ca.ntro.jj.tasks.base.AtomicTask;
import ca.ntro.jj.tasks.base.TaskCompleteNotifyier;
import ca.ntro.jj.tasks.results.AtomicTaskResult;
import ca.ntro.jj.tasks.results.AtomicTaskResultJj;
import ca.ntro.jj.tasks.results.TaskResults;
import ca.ntro.jj.wrappers.result.ExceptionHandler;

public class AtomicTaskJj implements AtomicTask {

	@Override
	public void handleException(ExceptionHandler exceptionHandler) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public AtomicTaskResult execute(TaskResults results, TaskCompleteNotifyier notifyier) {
		return null;
	}

}
