package ca.ntro.core.task_graphs.generic_task_graph;

import ca.ntro.core.exceptions.Break;

public interface AtomicTaskMatcher<T  extends GenericTask<T,AT>, 
                                   AT extends GenericAtomicTask<T,AT>> {
	
	boolean matches(AT atomicTask) throws Break;

}
