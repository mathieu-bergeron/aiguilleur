package ca.ntro.core.task_graphs.task_graph_trace;

public interface GenericTraceMutator<V extends Object> {
	
	
	void notifyCurrentWasUsed();
	void notifyCurrentCouldNotBeUsed();


}
