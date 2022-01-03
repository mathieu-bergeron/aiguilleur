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
	public void silentlyAddResult(Object value) {
		setIsWaiting(false);
		getResults().set(0,value);
	}

	@Override
	public void advanceToNext() {
		// XXX: nothing
	}

	@Override
	public void notifyCurrentResultWasUsed() {
		// XXX: nothing
	}

	@Override
	public void notifyCurrentResultCouldNotBeUsed() {
		// XXX: nothing
	}

}
