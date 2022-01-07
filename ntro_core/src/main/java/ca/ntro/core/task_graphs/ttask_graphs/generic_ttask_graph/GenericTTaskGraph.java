package ca.ntro.core.task_graphs.ttask_graphs.generic_ttask_graph;

import ca.ntro.core.graph_writer.GraphWriter;
import ca.ntro.core.task_graphs.generic_task_graph.TaskId;
import ca.ntro.core.task_graphs.task_graph_trace.TaskGraphTrace;

public interface GenericTTaskGraph<T  extends GenericTTask<T,ST,ET,TG,G>,
                                   ST extends GenericSimpleTask<T,ST,ET,TG,G>,
                                   ET extends GenericExecutableTask<T,ST,ET,TG,G>,
                                   TG extends GenericTaskGroup<T,ST,ET,TG,G>,
                                   G  extends GenericTTaskGraph<T,ST,ET,TG,G>> 

       extends   GenericTaskGroup<T,ST,ET,TG,G> {
	
	void setGraphName(String graphName);

	T findTask(TaskId id);
	T findTask(String id);
	
	void write(GraphWriter writer);

	TaskGraphTrace newTrace();
}
