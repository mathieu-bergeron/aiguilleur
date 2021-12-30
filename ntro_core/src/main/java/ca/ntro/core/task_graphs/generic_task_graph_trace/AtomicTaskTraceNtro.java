package ca.ntro.core.task_graphs.generic_task_graph_trace;

import ca.ntro.core.task_graphs.generic_task_graph.GenericAtomicTask;
import ca.ntro.core.task_graphs.generic_task_graph.GenericAtomicTaskNtro;

public class      AtomicTaskTraceNtro 
       extends    GenericTraceNtro<Object>
       implements AtomicTaskTrace {

	private GenericAtomicTaskNtro<?,?> task;

	public GenericAtomicTaskNtro<?, ?> getTask() {
		return task;
	}

	public void setTask(GenericAtomicTaskNtro<?, ?> task) {
		this.task = task;
	}

	@Override
	public GenericAtomicTask<?, ?> task() {
		return getTask();
	}
	
	
}
