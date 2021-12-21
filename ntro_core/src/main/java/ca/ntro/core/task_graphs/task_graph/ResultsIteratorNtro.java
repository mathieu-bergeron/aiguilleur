package ca.ntro.core.task_graphs.task_graph;

import ca.ntro.core.values.ObjectMap;

public class ResultsIteratorNtro implements ResultsIterator {
	
	private ResultsAccumulatorNtro accumulator = new ResultsAccumulatorNtro();
	private int currentIndex = 0;

	public ResultsAccumulatorNtro getAccumulator() {
		return accumulator;
	}

	public void setAccumulator(ResultsAccumulatorNtro accumulator) {
		this.accumulator = accumulator;
	}

	public int getCurrentIndex() {
		return currentIndex;
	}

	public void setCurrentIndex(int currentIndex) {
		this.currentIndex = currentIndex;
	}
	
	
	
	public ResultsIteratorNtro() {
	}

	public ResultsIteratorNtro(ResultsAccumulatorNtro resultsAccumulatorNtro) {
		setAccumulator(resultsAccumulatorNtro);
	}
	
	
	public void decrementIndex(int dec) {
		currentIndex -= dec;
	}

	@Override
	public boolean hasNextResults() {
		return getAccumulator().hasResults(currentIndex + 1);
	}

	@Override
	public ObjectMap nextResults() {
		currentIndex++;
		getAccumulator().registerCurrentIndex(currentIndex);
		return getAccumulator().results(currentIndex);
	}


}
