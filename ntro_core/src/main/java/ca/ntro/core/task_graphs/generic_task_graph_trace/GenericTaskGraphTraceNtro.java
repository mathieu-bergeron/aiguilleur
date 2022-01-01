package ca.ntro.core.task_graphs.generic_task_graph_trace;

import java.util.HashSet;
import java.util.Set;

import ca.ntro.core.graph_writer.GraphWriter;
import ca.ntro.core.task_graphs.generic_task_graph.GenericTaskGraphNtro;
import ca.ntro.core.task_graphs.generic_task_graph.GenericTaskNtro;
import ca.ntro.core.values.ObjectMap;

public class GenericTaskGraphTraceNtro 

       extends GenericTraceNtro<ObjectMap>

       implements GenericTaskGraphTrace {
	
	private GenericTaskGraphNtro<?,?> graph;
	private Set<GenericTaskTraceNtro> traces = new HashSet<>();

	public GenericTaskGraphNtro<?, ?> getGraph() {
		return graph;
	}

	public void setGraph(GenericTaskGraphNtro<?, ?> graph) {
		this.graph = graph;
	}
	
	
	
	public GenericTaskGraphTraceNtro() {
	}


	public GenericTaskGraphTraceNtro(GenericTaskGraphNtro<?, ?> graph) {
		setGraph(graph);
	}
	
	public void initialize() {
		getGraph().tasks().forEach(task -> {
			
			GenericTaskTraceNtro trace = (GenericTaskTraceNtro) task.newTrace();
			trace.setTask((GenericTaskNtro<?,?>) task);
			trace.initialize();

			traces.add(trace);
		});
	}

	@Override
	public void write(GraphWriter writer) {
		
	}

	@Override
	public void notifyWaitingForResult() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyCurrentResultWasUsed() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyCurrentResultCouldNotBeUsed() {
		// TODO Auto-generated method stub
		
	}

}
