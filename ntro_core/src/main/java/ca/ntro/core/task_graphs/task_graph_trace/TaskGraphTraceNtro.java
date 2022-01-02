package ca.ntro.core.task_graphs.task_graph_trace;

import java.util.HashMap;
import java.util.Map;

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
	private String graphName;
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

	public String getGraphName() {
		return graphName;
	}

	public void setGraphName(String graphName) {
		this.graphName = graphName;
	}
	
	
	

	public TaskGraphTraceNtro() {
	}


	public TaskGraphTraceNtro(GenericTaskGraphNtro<?, ?> graph) {
		setGraph(graph);
		setGraphName(graph.getGraphName());
		initialize();
	}
	
	
	public void initialize() {
		getGraph().tasks().forEach(task -> {
			getTraces().put(task.id().toKey().toString(), (TaskTraceNtro) task.newTrace());
		});
	}
	
	private Stream<TaskTraceNtro> traces(){
		return new StreamNtro<TaskTraceNtro>() {
			@Override
			public void forEach_(Visitor<TaskTraceNtro> visitor) throws Throwable {
				for(TaskTraceNtro trace : getTraces().values()) {
					visitor.visit(trace);
				}
			}
		};
	}

	@Override
	public TaskTrace getTaskTrace(TaskId id) {
		return getTaskTrace(id.toKey().toString());
	}

	@Override
	public TaskTrace getTaskTrace(String id) {
		return getTraces().get(id);
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
		
		getGraph().setGraphName(getGraphName() + "_" + currentState);
		
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
