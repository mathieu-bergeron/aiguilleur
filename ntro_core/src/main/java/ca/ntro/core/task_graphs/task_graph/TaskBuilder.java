package ca.ntro.core.task_graphs.task_graph;

public interface TaskBuilder<A extends Task<AT>, AT extends AtomicTask> {
	
	AtomicTask addEntryTask();

	AtomicTask addExitTask();
	
	A addSubTask();
	
	A addPreviousTask();
	
	A addNextTask();

}
