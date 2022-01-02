package ca.ntro.core.task_graphs.task_graph_trace;

import ca.ntro.core.task_graphs.generic_task_graph.GenericAtomicTaskNtro;

public class      AtomicTaskTraceTemporaryCondition 
        
       extends    AtomicTaskTraceNtro 
       
       implements AtomicTaskTrace {
	

	
	public AtomicTaskTraceTemporaryCondition() {
	}

	public AtomicTaskTraceTemporaryCondition(GenericAtomicTaskNtro<?,?> task) {
		super(task);
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
