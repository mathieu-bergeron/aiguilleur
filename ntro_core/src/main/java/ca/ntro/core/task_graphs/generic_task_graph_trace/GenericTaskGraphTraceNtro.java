package ca.ntro.core.task_graphs.generic_task_graph_trace;

import java.util.HashSet;
import java.util.Set;

import ca.ntro.core.graph_writer.GraphWriter;
import ca.ntro.core.task_graphs.generic_task_graph.GenericTaskGraphNtro;
import ca.ntro.core.task_graphs.generic_task_graph.GenericTaskNtro;
import ca.ntro.core.task_graphs.task_graph_writer.InternalTaskGraphTraceWriterNtro;
import ca.ntro.core.values.ObjectMap;

public class GenericTaskGraphTraceNtro 

       extends GenericTraceNtro<ObjectMap>

       implements GenericTaskGraphTrace {
	
	private GenericTaskGraphNtro<?,?> graph;
	private Set<GenericTaskTraceNtro> traces = new HashSet<>();
	private InternalTaskGraphTraceWriterNtro<?,?> internalWriter = new InternalTaskGraphTraceWriterNtro(this);

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
		
		throw new RuntimeException("TODO: write the graph structure && the task states corresponding to the current trace frame");
		
		/*
		String graphName = getGraph().getGraphName();
		graphName += "__trace00";
		getGraph().setGraphName(graphName);
		
		internalWriter.write((GenericGraph) getGraph().getHdagBuilder().getGraph(), new HierarchicalDagWriterOptionsNtro(), writer);
		*/
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
