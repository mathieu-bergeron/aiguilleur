package ca.ntro.core.task_graphs.task_graph;

import ca.ntro.core.task_graphs.generic_task_graph.GenericTaskGraph;
import ca.ntro.core.task_graphs.generic_task_graph_trace.GenericTaskGraphTrace;

public interface TaskGraphOptions {
	
	boolean shouldHalt(GenericTaskGraph<?,?> graph, GenericTaskGraphTrace trace);
	boolean shouldWriteGraph();
	long    maxDelayMillis();

}
