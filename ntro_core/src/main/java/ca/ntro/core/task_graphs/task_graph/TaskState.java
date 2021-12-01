package ca.ntro.core.task_graphs.task_graph;

public enum TaskState {
	
	QUEUED, 

	ENTRY_TASKS_IN_PROGRESS,

	SUB_TASKS_IN_PROGRESS,

	EXIT_TASKS_IN_PROGRESS,

	DONE;

}
