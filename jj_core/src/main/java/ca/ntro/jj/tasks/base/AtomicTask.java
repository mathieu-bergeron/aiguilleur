package ca.ntro.jj.tasks.base;

import ca.ntro.jj.common.ExceptionDelayer;
import ca.ntro.jj.tasks.meta.AtomicTaskMeta;
import ca.ntro.jj.tasks.results.AtomicTaskResult;
import ca.ntro.jj.tasks.results.TaskResults;

public interface AtomicTask extends AtomicTaskMeta, ExceptionDelayer {

	AtomicTaskResult execute(TaskResults results, TaskCompleteNotifyier notifyier);

}
