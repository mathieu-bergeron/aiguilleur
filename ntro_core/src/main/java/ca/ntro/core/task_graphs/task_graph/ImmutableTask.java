package ca.ntro.core.task_graphs.task_graph;


public interface ImmutableTask<IT extends ImmutableTask<IT,AT,IAT>, 
                               AT extends AtomicTask<AT,IAT>,
                               IAT extends ImmutableAtomicTask<IAT>> {
	
	TaskId id();

	boolean isQueued();
	boolean isInProgress();
	boolean isDone();

	AT findEntryTask(AtomicTaskId id);
	AT findExitTask(AtomicTaskId id);

}
