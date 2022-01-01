package ca.ntro.core.task_graphs.generic_task_graph_trace;

import ca.ntro.core.task_graphs.generic_task_graph.GenericAtomicTask;
import ca.ntro.core.task_graphs.generic_task_graph.GenericAtomicTaskNtro;

public abstract class GenericAtomicTaskTraceNtro 
       extends        GenericTraceNtro<Object>
       implements     GenericAtomicTaskTrace {

	private GenericAtomicTaskNtro<?,?> task;

	public GenericAtomicTaskNtro<?, ?> getTask() {
		return task;
	}

	public void setTask(GenericAtomicTaskNtro<?, ?> task) {
		this.task = task;
	}
	
	
	
	public GenericAtomicTaskTraceNtro() {
	}

	public GenericAtomicTaskTraceNtro(GenericAtomicTaskNtro<?,?> task) {
		setTask(task);
	}

	

	@Override
	public GenericAtomicTask<?, ?> task() {
		return getTask();
	}
}
