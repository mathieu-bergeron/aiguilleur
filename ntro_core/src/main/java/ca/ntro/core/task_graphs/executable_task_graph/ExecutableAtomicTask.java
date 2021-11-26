package ca.ntro.core.task_graphs.executable_task_graph;

import ca.ntro.core.task_graphs.task_graph.AtomicTask;
import ca.ntro.core.values.ObjectMap;

public interface ExecutableAtomicTask extends AtomicTask {
	
	void execute(ObjectMap results, Notifyer notifyer);

}
