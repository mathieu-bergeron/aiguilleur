package ca.ntro.core.task_graphs.task_graph;

import ca.ntro.core.exceptions.Break;

public interface AtomicTaskMatcher<T  extends Task<T,AT>, 
                                   AT extends AtomicTask<T,AT>> {
	
	boolean matches(AT atomicTask) throws Break;

}
