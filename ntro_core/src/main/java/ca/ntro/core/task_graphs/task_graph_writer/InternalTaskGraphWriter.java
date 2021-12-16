package ca.ntro.core.task_graphs.task_graph_writer;

import ca.ntro.core.graphs.hierarchical_dag.InternalHierarchicalDagWriter;
import ca.ntro.core.task_graphs.task_graph.AtomicTask;
import ca.ntro.core.task_graphs.task_graph.Task;
import ca.ntro.core.task_graphs.task_graph.TaskGraphEdge;
import ca.ntro.core.task_graphs.task_graph.TaskGraphNode;

public interface InternalTaskGraphWriter<T  extends Task<T,AT>, 
                                         AT extends AtomicTask<T,AT>>

       extends InternalHierarchicalDagWriter<TaskGraphNode<T,AT>,
	                                         TaskGraphEdge<T,AT>> {
	

}
