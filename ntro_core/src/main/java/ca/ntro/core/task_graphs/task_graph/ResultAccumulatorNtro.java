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
	public ResultIterator resultIterator() {
		// TODO Auto-generated method stub
		return null;
	}

}
