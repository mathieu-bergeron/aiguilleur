package ca.ntro.core.task_graphs.task_graph_trace;

import ca.ntro.core.graph_writer.GraphWriter;
import ca.ntro.core.task_graphs.generic_task_graph.TaskId;

public interface TaskGraphTrace extends GenericTraceNexter {
	
	TaskTrace getTaskTrace(TaskId id);
	TaskTrace getTaskTrace(String id);

	boolean isWaiting();

	void writeCurrentState(GraphWriter writer);
	void writeTrace(GraphWriter writer);
	
	void onExecutionStep(ExecutionStepHandler handler);

}
