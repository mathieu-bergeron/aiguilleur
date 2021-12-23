package ca.ntro.core.task_graphs.task_graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ca.ntro.core.values.ObjectMap;
import ca.ntro.core.wrappers.optionnal.OptionnalNtro;

public class ResultsAccumulatorNtro implements ResultsAccumulator {
	
	private List<ObjectMap> results = new ArrayList<>();
	private Set<ResultsIteratorNtro> iterators = new HashSet<>();
	private OptionnalNtro<Integer> smallestIndex = new OptionnalNtro<Integer>();

	@Override
	public void pushResults(ObjectMap newResults) {
		results.add(newResults);
	}

	@Override
	public ResultsIterator resultsIterator() {
		return null;
	}

	public boolean hasResults(int index) {
		return index >= 0
				&& index < results.size();
	}

	public ObjectMap results(int index) {
		ObjectMap result = null;

		if(hasResults(index)) {
			result = results.get(index);
		}

		return result;
	}

	public void registerCurrentIndex(int currentIndex) {
		if(!smallestIndex.hasValue()
				|| currentIndex < smallestIndex.value()) {
			
			smallestIndex.registerValue(currentIndex);

			forgetUnusedResults(currentIndex);
			
			for(ResultsIteratorNtro iterator : iterators) {
				//iterator.decrementIndex(currentIndex);
			}
		}
	}

	private void forgetUnusedResults(int smallestIndex) {
		results = results.subList(smallestIndex, results.size());
	}

}
