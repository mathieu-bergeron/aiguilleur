package ca.ntro.core.task_graphs.executable_task_graph;

public interface ExecutableTaskGraphOptions {
	
	boolean shouldHalt(ExecutableTaskGraph graph);
	boolean shouldWriteGraph();
	long    maxDelayMillis();

}
