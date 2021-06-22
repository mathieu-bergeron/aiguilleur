package ca.ntro.jj.tasks.meta;

public interface TaskMeta<TM extends TaskMeta<?,?>, ATM extends AtomicTaskMeta> {

	TM addPreviousTask(TM previousTask);
	TM addEntryTask(ATM entryTask);
	TM addSubTask(TM subTask);
	TM addExitTask(ATM exitTask);
	TM addNextTask(TM nextTask);
}
