package ca.ntro.core.task_graphs.task_graph;

public interface Task<T extends Task<T,IT,AT,IAT>, 
					  IT extends ImmutableTask<IT,AT,IAT>,
                      AT extends AtomicTask<AT,IAT>,
                      IAT extends ImmutableAtomicTask<IAT>>

       extends ImmutableTask<T,AT,IAT> {

	T addSubTask(T subTask);
	
	T addPreviousTask(T previousTask);
	
	T addNextTask(T nextTask);

	T addEntryTask(AT entryTask);

	T addExitTask(AT exitTask);

	IT toImmutableTask();
}
