package ca.ntro.app.frontend.tasks;

import ca.ntro.core.task_graphs.generic_task_graph.TaskId;
import ca.ntro.core.task_graphs.task_graph.TaskGraphNtro;
import ca.ntro.core.task_graphs.task_graph.TaskNtro;

public class TypedFrontendTaskDescriptorNtro<O extends Object> implements TypedFrontendTaskDescriptor<O>  {
	
	private TaskId id;
	private TaskGraphNtro taskGraph;

	public TaskId getId() {
		return id;
	}

	public void setId(TaskId id) {
		this.id = id;
	}

	public TaskGraphNtro getTaskGraph() {
		return taskGraph;
	}

	public void setTaskGraph(TaskGraphNtro taskGraph) {
		this.taskGraph = taskGraph;
	}


	@Override
	public TaskId id() {
		return getId();
	}

	@Override
	public void cancel() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
}
