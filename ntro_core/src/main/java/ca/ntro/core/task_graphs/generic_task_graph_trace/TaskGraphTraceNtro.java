package ca.ntro.core.task_graphs.generic_task_graph_trace;

import java.util.HashSet;
import java.util.Set;

import ca.ntro.core.graph_writer.GraphWriter;
import ca.ntro.core.task_graphs.generic_task_graph.TaskGraphNtro;
import ca.ntro.core.task_graphs.generic_task_graph.GenericTaskNtro;
import ca.ntro.core.values.ObjectMap;

public class TaskGraphTraceNtro 

       extends GenericTraceNtro<ObjectMap>

       implements TaskGraphTrace {
	
	private TaskGraphNtro<?,?> graph;
	private Set<TaskTraceNtro> traces = new HashSet<>();

	public TaskGraphNtro<?, ?> getGraph() {
		return graph;
	}

	public void setGraph(TaskGraphNtro<?, ?> graph) {
		this.graph = graph;
	}
	
	
	
	public TaskGraphTraceNtro() {
	}


	public TaskGraphTraceNtro(TaskGraphNtro<?, ?> graph) {
		setGraph(graph);
	}
	
	public void initialize() {
		getGraph().tasks().forEach(task -> {
			
			TaskTraceNtro trace = (TaskTraceNtro) task.newTrace();
			trace.setTask((GenericTaskNtro<?,?>) task);
			trace.initialize();

			traces.add(trace);
		});
	}

	@Override
	public void write(GraphWriter writer) {
		
	}

}
