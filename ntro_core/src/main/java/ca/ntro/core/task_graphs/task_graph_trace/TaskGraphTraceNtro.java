package ca.ntro.core.task_graphs.task_graph_trace;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import ca.ntro.core.graph_writer.GraphWriter;
import ca.ntro.core.graphs.generics.graph.GenericGraph;
import ca.ntro.core.graphs.hierarchical_dag.HierarchicalDagWriterOptionsNtro;
import ca.ntro.core.stream.Stream;
import ca.ntro.core.stream.StreamNtro;
import ca.ntro.core.stream.Visitor;
import ca.ntro.core.task_graphs.generic_task_graph.GenericTaskGraphNtro;
import ca.ntro.core.task_graphs.generic_task_graph.TaskId;
import ca.ntro.core.task_graphs.task_graph_writer.InternalTaskGraphTraceWriterNtro;

public class      TaskGraphTraceNtro 

       implements TaskGraphTrace {
	
	private GenericTaskGraphNtro<?,?> graph;
	private Map<String, TaskTraceNtro> traces = new HashMap<>();
	private InternalTaskGraphTraceWriterNtro<?,?> internalWriter = new InternalTaskGraphTraceWriterNtro(this);
	private long currentState = 0;

	public GenericTaskGraphNtro<?, ?> getGraph() {
		return graph;
	}

	public void setGraph(GenericTaskGraphNtro<?, ?> graph) {
		this.graph = graph;
	}

	public Map<String, TaskTraceNtro> getTraces() {
		return traces;
	}

	public void setTraces(Map<String, TaskTraceNtro> traces) {
		this.traces = traces;
	}

	public long getCurrentState() {
		return currentState;
	}

	public void setCurrentState(long currentState) {
		this.currentState = currentState;
	}

	public InternalTaskGraphTraceWriterNtro<?, ?> getInternalWriter() {
		return internalWriter;
	}

	public void setInternalWriter(InternalTaskGraphTraceWriterNtro<?, ?> internalWriter) {
		this.internalWriter = internalWriter;
	}
	
	
	
	

	public TaskGraphTraceNtro() {
	}


	public TaskGraphTraceNtro(GenericTaskGraphNtro<?, ?> graph) {
		setGraph(graph);
		initialize();
	}
	
	
	public void initialize() {
		getGraph().tasks().forEach(task -> {
			traces.put(task.id().toKey().toString(), (TaskTraceNtro) task.newTrace());
		});
	}
	
	private Stream<TaskTraceNtro> traces(){
		return new StreamNtro<TaskTraceNtro>() {
			@Override
			public void _forEach(Visitor<TaskTraceNtro> visitor) throws Throwable {
				for(TaskTraceNtro trace : getTraces().values()) {
					visitor.visit(trace);
				}
			}
		};
	}

	@Override
	public TaskTrace getTaskTrace(TaskId id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TaskTrace getTaskTrace(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasNext() {
		return traces().ifSome(trace -> trace.hasNext());
	}

	@Override
	public void advanceToNext() {
		traces().forEach(trace -> trace.advanceToNext());
		currentState++;
	}

	@Override
	public void writeCurrentState(GraphWriter writer) {
		
		String graphName = getGraph().getGraphName();
		graphName += "__" + currentState;
		getGraph().setGraphName(graphName);
		
		internalWriter.write((GenericGraph) getGraph().getHdagBuilder().getGraph(), new HierarchicalDagWriterOptionsNtro(), writer);
	}

	@Override
	public void writeTrace(GraphWriter writer) {
		writeCurrentState(writer);
		
		while(hasNext()) {
			advanceToNext();
			writeCurrentState(writer);
		}
	}

}
