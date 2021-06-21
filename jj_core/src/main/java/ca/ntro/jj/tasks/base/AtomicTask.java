package ca.ntro.jj.tasks.base;

import ca.ntro.jj.common.ExceptionDelayer;

import ca.ntro.jj.tasks.meta.AtomicTaskMeta;
import ca.ntro.jj.tasks.results.ObjectMap;

public interface AtomicTask<ATM extends AtomicTaskMeta> extends AtomicTaskMeta, ExceptionDelayer<ATM> {

	ATM execute(ObjectMap previousResults, TaskCompleteNotifyier notifyier);

}
