package ca.ntro.core.task_graphs.generic_task_graph;

import ca.ntro.core.exceptions.Break;

public interface AtomicTaskVisitor<T  extends GenericTask<T,AT>, 
                                   AT extends GenericAtomicTask<T,AT>> {
	
	void visitAtomicTask(AT atomicTask) throws Break;

}
