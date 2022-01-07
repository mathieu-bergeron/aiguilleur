package ca.ntro.core.task_graphs.ttask_graphs.ttask_graph_trace;

import ca.ntro.core.graph_writer.GraphWriter;
import ca.ntro.core.task_graphs.generic_task_graph.TaskId;
import ca.ntro.core.task_graphs.task_graph_trace.ExecutionStepHandler;
import ca.ntro.core.task_graphs.task_graph_trace.TaskGraphTrace;
import ca.ntro.core.task_graphs.task_graph_trace.TaskTrace;

public class TTaskGraphTraceNtro 

       implements TaskGraphTrace {

	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void advanceToNext() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public TaskTrace getTaskTrace(TaskId id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TaskTrace getTaskTrace(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isWaiting() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void writeCurrentState(GraphWriter writer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void writeTrace(GraphWriter writer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onExecutionStep(ExecutionStepHandler handler) {
		// TODO Auto-generated method stub
		
	}

}
