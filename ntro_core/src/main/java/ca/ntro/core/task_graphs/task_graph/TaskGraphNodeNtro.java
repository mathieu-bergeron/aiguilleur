package ca.ntro.core.task_graphs.task_graph;

import ca.ntro.core.graphs.hierarchical_dag.HierarchicalDagNodeNtro;

public class      TaskGraphNodeNtro<T  extends Task<T,AT>, 
                                    AT extends AtomicTask<T,AT>>

       extends    HierarchicalDagNodeNtro<TaskGraphNode<T,AT>,TaskGraphEdge<T,AT>>


	   implements TaskGraphNode<T,AT>

{

	@Override
	public T task() {
		// TODO Auto-generated method stub
		return null;
	}

}
