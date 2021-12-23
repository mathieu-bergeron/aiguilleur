package ca.ntro.core.task_graphs.task_graph_trace;

import java.util.HashSet;
import java.util.Set;

import ca.ntro.core.task_graphs.task_graph.TaskNtro;
import ca.ntro.core.values.ObjectMap;

public class     TaskTraceNtro 

       extends   GenericTraceNtro<ObjectMap>

       implements TaskTrace {

	private TaskNtro<?,?> task;
	private Set<TaskTraceNtro> traces = new HashSet<>();

	public TaskNtro<?, ?> getTask() {
		return task;
	}

	public void setTask(TaskNtro<?, ?> task) {
		this.task = task;
	}

	public Set<TaskTraceNtro> getTraces() {
		return traces;
	}

	public void setTraces(Set<TaskTraceNtro> traces) {
		this.traces = traces;
	}
	
	public void initialize() {
		task.previousTasks().forEach(previousTask -> {
			
			TaskTraceNtro trace = (TaskTraceNtro) previousTask.newTrace();
			traces.add(trace);
		});
		
		if(task.hasParentTask()) {
			traces.add((TaskTraceNtro) task.parentTask().newEntryTasksTrace());
		}
	}

	@Override
	public boolean hasCurrent() {
		return traces.stream().anyMatch(trace -> trace.hasCurrent());
	}
	
	
	
	
	
	
}
