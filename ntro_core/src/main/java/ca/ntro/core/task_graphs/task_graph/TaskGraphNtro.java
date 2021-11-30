package ca.ntro.core.task_graphs.task_graph;

import ca.ntro.core.graphs.hierarchical_dag.HierarchicalDag;
import ca.ntro.core.graphs.hierarchical_dag.HierarchicalDagSearchOptions;
import ca.ntro.core.values.ObjectMap;

public class TaskGraphNtro<T extends Task<AT>, AT extends AtomicTask> 

       implements TaskGraph<T,AT> {

	private HierarchicalDag<TaskGraphNode<T,AT>, TaskGraphEdge<T,AT>, HierarchicalDagSearchOptions> hdag;

	@Override
	public T findTask(TaskId id) {
		return hdag.findNode(id).task();
	}

	@Override
	public void notifyAtomicTaskCompleted(AT atomicTask, ObjectMap results) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isAtomicTaskCompleted(AT atomicTask) {
		// TODO Auto-generated method stub
		return false;
	}

}
