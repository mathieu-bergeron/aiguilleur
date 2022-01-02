package ca.ntro.core.task_graphs.task_graph_trace;

import ca.ntro.core.task_graphs.generic_task_graph.GenericAtomicTask;
import ca.ntro.core.task_graphs.generic_task_graph.GenericAtomicTaskNtro;

public abstract class AtomicTaskTraceNtro 
       implements     AtomicTaskTrace {

	private GenericAtomicTaskNtro<?,?> task;

	public GenericAtomicTaskNtro<?, ?> getTask() {
		return task;
	}

	public void setTask(GenericAtomicTaskNtro<?, ?> task) {
		this.task = task;
	}
	
	
	
	public AtomicTaskTraceNtro() {
	}

	public AtomicTaskTraceNtro(GenericAtomicTaskNtro<?,?> task) {
		setTask(task);
	}

	

	@Override
	public GenericAtomicTask<?, ?> task() {
		return getTask();
	}
	
	
	
	
	

	@Override
	public void addResult(Object value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clearResults() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean hasCurrent() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object current() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isWaiting() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void advanceToNext() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyCurrentResultWasUsed() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyCurrentResultCouldNotBeUsed() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyWaitingForResult() {
		// TODO Auto-generated method stub
		
	}
}
