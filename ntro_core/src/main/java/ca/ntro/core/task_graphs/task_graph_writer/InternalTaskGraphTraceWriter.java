package ca.ntro.core.task_graphs.task_graph_writer;

import ca.ntro.core.task_graphs.generic_task_graph.GenericAtomicTask;
import ca.ntro.core.task_graphs.generic_task_graph.GenericTask;

public interface InternalTaskGraphTraceWriter<T  extends GenericTask<T,AT>, 
                                              AT extends GenericAtomicTask<T,AT>>

       extends InternalTaskGraphWriter<T,AT> {

}
