package ca.ntro.jj.tasks.task_graph;

import ca.ntro.jj.tasks.base.AtomicTask;
import ca.ntro.jj.tasks.base.Task;
import ca.ntro.jj.tasks.generic.GenericTaskGraph;
import ca.ntro.jj.values.ObjectMap;
import ca.ntro.jj.wrappers.future.Future;

public interface TaskGraph<T extends Task<?,AtomicTask>>

       extends    GenericTaskGraph<T, AtomicTask> {
	
	/* TODO:
	 * Execute one Task, then call sleep
	 */
	ObjectMap executeBlocking();

	
	/* TODO:
	 * 
	 * execute 1 task, then call executeLater
	 * executerLater(execute)
	 * 
	 * in effect: executing 1 task, then add an event 
	 * in the queue that will execute the next one
	 * 
	 * NOTE: we must have non-blocking ModelLocks for this to work correctly
	 *       i.e. the ModelLocks is a set of queues (one for each Model)
	 *       when we are next in queue, the lock is acquired and our execute is called
	 */
	Future<ObjectMap> execute();

}
