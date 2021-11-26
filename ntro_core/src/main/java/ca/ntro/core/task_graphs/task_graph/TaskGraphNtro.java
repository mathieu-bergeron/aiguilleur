package ca.ntro.core.task_graphs.task_graph;

import ca.ntro.core.graphs.EdgeValueNull;
import ca.ntro.core.graphs.hierarchical_dag.HierarchicalDag;
import ca.ntro.core.values.ObjectMap;

public class TaskGraphNtro<T extends Task, AT extends AtomicTask>
	   implements TaskGraph<T,AT>, TaskGraphBuilder<T,AT> {
	
	private HierarchicalDag<T, EdgeValueNull> dag;
	

	public TaskGraphNtro() {
	}

	public TaskGraphNtro(String graphName) {

	}

	
	
	
	
	
	@Override
	public AT findAtomicTask(AtomicTaskId id) {
		// TODO Auto-generated method stub
		return null;
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
