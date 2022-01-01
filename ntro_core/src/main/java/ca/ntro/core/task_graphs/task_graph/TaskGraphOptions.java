package ca.ntro.core.task_graphs.task_graph;

public interface TaskGraphOptions {
	
	boolean shouldHalt(TaskGraph graph);
	boolean shouldWriteGraph();
	long    maxDelayMillis();

}
