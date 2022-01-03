package ca.ntro.core.task_graphs.task_graph_trace;

import ca.ntro.core.task_graphs.generic_task_graph.GenericAtomicTaskNtro;

public class      AtomicTaskTraceCondition 
        
       extends    AtomicTaskTraceNtro 
       
       implements AtomicTaskTrace {
	

	
	public AtomicTaskTraceCondition() {
	}

	public AtomicTaskTraceCondition(GenericAtomicTaskNtro<?,?> task, TaskTraceNtro parentTrace) {
		super(task, parentTrace);
	}



	@Override
	public void notifyWaitingForResult() {
	}

	@Override
	public void notifyCurrentResultWasUsed() {
	}

	@Override
	public void notifyCurrentResultCouldNotBeUsed() {
	}

}
