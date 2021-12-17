package ca.ntro.core.task_graphs.executable_task_graph.handlers;

public interface Notifyer {

	void registerResult(Object value);
	void registerException(Throwable t);

}
