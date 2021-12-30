package ca.ntro.core.task_graphs.task_graph_writer;

import ca.ntro.core.graphs.hierarchical_dag.InternalHierarchicalDagWriter;
import ca.ntro.core.task_graphs.generic_task_graph.GenericAtomicTask;
import ca.ntro.core.task_graphs.generic_task_graph.GenericTask;
import ca.ntro.core.task_graphs.generic_task_graph.TaskGraphEdge;
import ca.ntro.core.task_graphs.generic_task_graph.TaskGraphNode;

public interface InternalTaskGraphWriter<T  extends GenericTask<T,AT>, 
                                         AT extends GenericAtomicTask<T,AT>>

       extends InternalHierarchicalDagWriter<TaskGraphNode<T,AT>,
	                                         TaskGraphEdge<T,AT>> {
	

}
