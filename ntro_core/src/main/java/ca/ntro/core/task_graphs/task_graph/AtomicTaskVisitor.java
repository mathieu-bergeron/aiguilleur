package ca.ntro.core.task_graphs.task_graph;

import ca.ntro.core.exceptions.Break;

public interface AtomicTaskVisitor<T  extends Task<T,AT,TG>, 
                                   AT extends AtomicTask<T,AT,TG>,
                                   TG extends TaskGraph<T,AT>> {
	
	void visitAtomicTask(AT atomicTask) throws Break;

}
