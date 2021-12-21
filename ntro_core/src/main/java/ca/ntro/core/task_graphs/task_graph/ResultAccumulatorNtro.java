package ca.ntro.core.task_graphs.task_graph;

import java.util.ArrayList;
import java.util.List;

public class ResultAccumulatorNtro implements ResultAccumulator {
	
	private List<Object> results = new ArrayList<>();

	public List<Object> getResults() {
		return results;
	}

	public void setResults(List<Object> results) {
		this.results = results;
	}

	
	public ResultAccumulatorNtro() {
	}

	

	@Override
	public boolean hasResult() {
		return !getResults().isEmpty();
	}

	@Override
	public Object result() {
		Object result = null;
		
		if(hasResult()) {
			result = getResults().get(0);
		}
		
		return result;
	}

	@Override
	public void addResult(Object result) {
		getResults().add(result);
	}

	@Override
	public void clearResults() {
		getResults().clear();
	}

	@Override
	public boolean hasNextResult() {
		return getResults().size() > 1;
	}

	@Override
	public Object nextResult() {
		Object result = null;
		
		if(!results.isEmpty()) {
			result = results.get(0);
		}
		
		forgetFirstResult();
		
		return result;
	}

	private void forgetFirstResult() {
		results = results.subList(1, results.size());
	}
}
