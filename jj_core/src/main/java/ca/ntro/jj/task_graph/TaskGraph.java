package ca.ntro.jj.task_graph;

import ca.ntro.jj.wrappers.future.Future;

public interface TaskGraph {
	
	Future<Void> execute();
	Future<Void> executeBlocking();

}
