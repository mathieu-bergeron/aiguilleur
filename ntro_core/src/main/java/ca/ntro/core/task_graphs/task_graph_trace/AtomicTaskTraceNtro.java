package ca.ntro.core.task_graphs.task_graph_trace;

import ca.ntro.core.task_graphs.task_graph.AtomicTask;
import ca.ntro.core.task_graphs.task_graph.AtomicTaskNtro;

public class      AtomicTaskTraceNtro 
       extends    GenericTraceNtro<Object>
       implements AtomicTaskTrace {

	private AtomicTaskNtro<?,?> task;

	public AtomicTaskNtro<?, ?> getTask() {
		return task;
	}

	public void setTask(AtomicTaskNtro<?, ?> task) {
		this.task = task;
	}

	@Override
	public AtomicTask<?, ?> task() {
		return getTask();
	}
	
	
}
