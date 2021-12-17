package ca.ntro.core.task_graphs.executable_task_graph.handlers;

import ca.ntro.core.values.ObjectMap;

public interface OnResumeHandler {
	
	void resume(ObjectMap currentResults, Notifyer notifyer) throws Throwable;

}
