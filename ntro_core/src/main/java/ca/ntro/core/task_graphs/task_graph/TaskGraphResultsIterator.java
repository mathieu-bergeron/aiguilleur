package ca.ntro.core.task_graphs.task_graph;

import ca.ntro.core.graph_writer.GraphWriter;

public interface TaskGraphResultsIterator extends ResultsIterator {
	
	void write(GraphWriter writer);

}
