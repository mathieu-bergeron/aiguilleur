package ca.ntro.core.task_graphs.generic_task_graph_trace;

import ca.ntro.core.task_graphs.generic_task_graph.GenericAtomicTaskNtro;

public class      GenericAtomicTaskTraceMessageHandler 
        
       extends    GenericAtomicTaskTraceNtro 
       
       implements GenericAtomicTaskTrace {
	

	
	public GenericAtomicTaskTraceMessageHandler() {
	}

	public GenericAtomicTaskTraceMessageHandler(GenericAtomicTaskNtro<?,?> task) {
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
