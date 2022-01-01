package ca.ntro.core.task_graphs.generic_task_graph_trace;

import ca.ntro.core.task_graphs.generic_task_graph.GenericAtomicTaskNtro;

public class      GenericAtomicTaskTraceCondition 
        
       extends    GenericAtomicTaskTraceNtro 
       
       implements GenericAtomicTaskTrace {
	

	
	public GenericAtomicTaskTraceCondition() {
	}

	public GenericAtomicTaskTraceCondition(GenericAtomicTaskNtro<?,?> task) {
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
