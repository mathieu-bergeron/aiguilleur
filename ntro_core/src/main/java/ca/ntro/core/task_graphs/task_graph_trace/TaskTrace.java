package ca.ntro.core.task_graphs.task_graph_trace;

import ca.ntro.core.values.ObjectMap;

public interface TaskTrace 

       extends   GenericTraceAccessor<ObjectMap> {
	
	boolean isBlocked();
	boolean isInProgress();
	boolean isDone();

}
