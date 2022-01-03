package ca.ntro.core.task_graphs.task_graph_trace;

public enum TaskState {
	
	BLOCKED,
	EXECUTING_ENTRY_TASKS,
	EXECUTING_SUB_TASKS,
	EXECUTING_EXIT_TASKS,
	HAS_NEXT,
	DONE;

}
