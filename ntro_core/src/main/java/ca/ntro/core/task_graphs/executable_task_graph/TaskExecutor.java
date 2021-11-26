package ca.ntro.core.task_graphs.executable_task_graph;

import ca.ntro.core.values.ObjectMap;

public interface TaskExecutor {
	
	void execute(ObjectMap results, Notifyer notifyer);

}
