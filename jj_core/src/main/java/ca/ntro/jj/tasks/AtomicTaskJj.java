package ca.ntro.jj.tasks;

import ca.ntro.jj.common.ExceptionDelayerJj;
import ca.ntro.jj.tasks.base.AtomicTask;
import ca.ntro.jj.tasks.base.TaskCompleteNotifyier;
import ca.ntro.jj.tasks.results.NamedResults;

public class AtomicTaskJj<ATM extends AtomicTaskJj<ATM>> extends ExceptionDelayerJj<ATM> implements AtomicTask<ATM> {

	@SuppressWarnings("unchecked")
	@Override
	public ATM execute(NamedResults previousResults, TaskCompleteNotifyier notifyier) {
		
		notifyier.taskComplete();
		
		return (ATM) this;
	}

}
