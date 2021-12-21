package ca.ntro.core.task_graphs.task_graph;

import java.util.ArrayList;
import java.util.List;

public class ResultAccumulatorNtro implements ResultAccumulator {
	
	private AtomicTaskOptions options = new AtomicTaskOptionsDefault();
	private List<Object> results = new ArrayList<>();

	public List<Object> getResults() {
		return results;
	}

	public void setResults(List<Object> results) {
		this.results = results;
	}

	public AtomicTaskOptions getOptions() {
		return options;
	}

	public void setOptions(AtomicTaskOptions options) {
		this.options = options;
	}
	
	
	
	
	

	public ResultAccumulatorNtro() {
	}

	
	
	
	

	@Override
	public boolean hasNextResult() {
		boolean hasNextResult = false;
		
		if(getOptions().consumeFirstResult()
				&& getResults().size() > 0) {
			
			hasNextResult = true;

		}else if(!getOptions().consumeFirstResult()
				&& getResults().size() > 1) {
			
			hasNextResult = false;
			
			
		}

		return hasNextResult;
	}

	@Override
	public Object nextResult() {
		Object result = null;
		
		if(hasNextResult()) {

			result = getResults().get(0);
			
			if(getResults().size() > 1
					|| getOptions().consumeFirstResult()) {

				consumeFirstResult();
			}
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

	private void consumeFirstResult() {
		results = results.subList(1, results.size());
	}

	public void registerOptions(AtomicTaskOptions options) {
		setOptions(options);
	}
}
