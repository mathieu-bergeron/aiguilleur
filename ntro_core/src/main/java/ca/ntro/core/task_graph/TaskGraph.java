package ca.ntro.core.task_graph;

import ca.ntro.core.values.ObjectMap;
import ca.ntro.core.wrappers.future.Future;

public interface TaskGraph {
	
	Future<ObjectMap> executeAsync();
	ObjectMap execute() throws Throwable;

}
