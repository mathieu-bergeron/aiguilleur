package ca.ntro.jj.tasks;

public interface GraphTraceConnector {

	void addGraphWriter(GraphTraceWriter writer);
	void addTaskStateListener(TaskStateListener listener);


}
