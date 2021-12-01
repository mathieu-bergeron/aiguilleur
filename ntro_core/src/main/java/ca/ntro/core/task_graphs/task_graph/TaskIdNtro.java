package ca.ntro.core.task_graphs.task_graph;

import ca.ntro.core.graphs.NodeIdNtro;
import ca.ntro.core.identifyers.Key;

public class TaskIdNtro extends NodeIdNtro implements TaskId {

	public TaskIdNtro(Key key) {
		super(key);
	}

	public TaskIdNtro(String key) {
		super(key);
	}
}
