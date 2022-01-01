package ca.ntro.core.task_graphs.task_graph_writer;

import ca.ntro.core.graphs.hierarchical_dag.InternalHierarchicalDagWriterNtro;
import ca.ntro.core.task_graphs.generic_task_graph.GenericAtomicTask;
import ca.ntro.core.task_graphs.generic_task_graph.GenericTask;
import ca.ntro.core.task_graphs.generic_task_graph.TaskGraphEdge;
import ca.ntro.core.task_graphs.generic_task_graph.TaskGraphNode;

public class InternalTaskGraphWriterNtro<T  extends GenericTask<T,AT>, 
                                         AT extends GenericAtomicTask<T,AT>>

       extends InternalHierarchicalDagWriterNtro<TaskGraphNode<T,AT>,
	                                             TaskGraphEdge<T,AT>>

	   implements InternalTaskGraphWriter<T,AT> {
	
}
