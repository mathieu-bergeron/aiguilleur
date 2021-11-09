package ca.ntro.jj.task_graph;

import ca.ntro.jj.values.ObjectMap;
import ca.ntro.jj.wrappers.future.Future;

public interface TaskGraph {
	
	Future<ObjectMap> execute();
	ObjectMap executeBlocking() throws Throwable;

}
