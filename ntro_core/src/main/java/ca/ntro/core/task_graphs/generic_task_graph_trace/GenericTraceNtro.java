package ca.ntro.core.task_graphs.generic_task_graph_trace;

public abstract class GenericTraceNtro<V extends Object> 

       implements GenericTraceAccessor<V>,
                  GenericTraceMutator<V> {
	
	
	@Override
	public boolean hasCurrent() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public V current() {
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

	public void addResult(V result) {
		
	}

	public void clearResults() {

	}
	
	public void notifyIsWaiting() {
		
	}

}