package ca.ntro.core.task_graphs.generic_task_graph;

import ca.ntro.core.graphs.hierarchical_dag.HierarchicalDagBuilderNtro;

public class InternalHierarchicalDagBuilderNtro<T  extends GenericTask<T,AT>, 
                           						AT extends GenericAtomicTask<T,AT>>

       extends   HierarchicalDagBuilderNtro<TaskGraphNode<T,AT>, TaskGraphEdge<T,AT>> 
	
	   implements InternalHierarchicalDagBuilder<T,AT> {

}
