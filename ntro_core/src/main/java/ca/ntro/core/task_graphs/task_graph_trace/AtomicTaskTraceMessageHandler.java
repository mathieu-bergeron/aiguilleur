package ca.ntro.core.task_graphs.task_graph_trace;

import ca.ntro.core.task_graphs.generic_task_graph.GenericAtomicTaskNtro;

public class      AtomicTaskTraceMessageHandler 
        
       extends    AtomicTaskTraceNtro 
       
       implements AtomicTaskTrace {
	

	
	public AtomicTaskTraceMessageHandler() {
	}

	public AtomicTaskTraceMessageHandler(GenericAtomicTaskNtro<?,?> task, TaskTraceNtro parentTrace) {
		super(task, parentTrace);
	}


	@Override
	public void notifyCurrentResultWasUsed() {
		if(hasNext()) {

			advanceToNext();

		}else {

			clearResults();

		}
	}

	@Override
	public void notifyCurrentResultCouldNotBeUsed() {
		// XXX: nothing for a message handler
	}

}
