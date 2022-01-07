package ca.ntro.core.task_graphs.ttask_graphs.generic_ttask_graph;

import ca.ntro.core.task_graphs.task_graph_trace.TaskGraphTrace;
import ca.ntro.core.task_graphs.task_graph_trace.TaskTrace;

public interface GenericSimpleTask<T  extends GenericTTask<T,ST,ET,TG,G>,
                                   ST extends GenericSimpleTask<T,ST,ET,TG,G>,
                                   ET extends GenericExecutableTask<T,ST,ET,TG,G>,
                                   TG extends GenericTaskGroup<T,ST,ET,TG,G>,
                                   G  extends GenericTTaskGraph<T,ST,ET,TG,G>>

       extends   GenericTTask<T,ST,ET,TG,G> {
	
	boolean isExecutableTask();
	ET asExecutableTask();
	
	TaskTrace newTrace(TaskGraphTrace parentTrace);

}