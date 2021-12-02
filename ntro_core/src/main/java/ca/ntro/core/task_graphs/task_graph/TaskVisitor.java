package ca.ntro.core.task_graphs.task_graph;

import ca.ntro.core.exceptions.Break;

public interface TaskVisitor<T  extends Task<T,AT>, 
                             AT extends AtomicTask<T,AT>> {
	
	void visitTask(T task) throws Break;

}
