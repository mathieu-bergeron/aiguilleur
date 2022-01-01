package ca.ntro.core.task_graphs.generic_task_graph_trace;

import ca.ntro.core.graph_writer.GraphWriter;
import ca.ntro.core.values.ObjectMap;

public interface GenericTaskGraphTrace extends GenericTraceAccessor<ObjectMap> {
	
	void write(GraphWriter writer);

}
