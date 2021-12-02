package ca.ntro.core.task_graphs.task_graph;

import ca.ntro.core.exceptions.Break;

public interface TaskMatcher<T  extends Task<T,AT>, 
                             AT extends AtomicTask<T,AT>> {
	
	boolean matches(T task) throws Break;

}
