package ca.ntro.jj.tasks.generic;

public interface GenericTask<GT extends GenericTask<?,?>, GAT extends GenericAtomicTask> {

	GT addPreviousTask(GT previousTask);
	GT addEntryTask(GAT entryTask);
	GT addSubTask(GT subTask);
	GT addExitTask(GAT exitTask);
	GT addNextTask(GT nextTask);
}
