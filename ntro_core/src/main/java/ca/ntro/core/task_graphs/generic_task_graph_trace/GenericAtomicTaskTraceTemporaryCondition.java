package ca.ntro.core.task_graphs.generic_task_graph_trace;

import ca.ntro.core.task_graphs.generic_task_graph.GenericAtomicTaskNtro;

public class      GenericAtomicTaskTraceTemporaryCondition 
        
       extends    GenericAtomicTaskTraceNtro 
       
       implements GenericAtomicTaskTrace {
	

	
	public GenericAtomicTaskTraceTemporaryCondition() {
	}

	public GenericAtomicTaskTraceTemporaryCondition(GenericAtomicTaskNtro<?,?> task) {
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
