package ca.ntro.core.task_graphs.task_graph_trace;

import java.util.HashMap;
import java.util.Map;

import ca.ntro.core.graph_writer.GraphWriter;
import ca.ntro.core.graphs.common.Direction;
import ca.ntro.core.graphs.generics.graph.GenericGraph;
import ca.ntro.core.graphs.generics.graph.InternalSearchOptions;
import ca.ntro.core.graphs.generics.graph.InternalSearchOptionsNtro;
import ca.ntro.core.graphs.hierarchical_dag.HierarchicalDag;
import ca.ntro.core.graphs.hierarchical_dag.HierarchicalDagSearchOptions;
import ca.ntro.core.graphs.hierarchical_dag.HierarchicalDagWriterOptionsNtro;
import ca.ntro.core.path.Path;
import ca.ntro.core.stream.Stream;
import ca.ntro.core.stream.StreamNtro;
import ca.ntro.core.stream.Visitor;
import ca.ntro.core.task_graphs.generic_task_graph.GenericTaskGraphNtro;
import ca.ntro.core.task_graphs.generic_task_graph.GenericTaskNtro;
import ca.ntro.core.task_graphs.generic_task_graph.TaskGraphNode;
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
		HierarchicalDag<?,?> graph = getGraph().getHdagBuilder().getGraph();
		HierarchicalDagSearchOptions searchOptions = searchOptions(graph);
		
		getGraph().tasks().forEach(task -> {
			getTraces().put(task.id().toKey().toString(), (TaskTraceNtro) task.newTrace());
		});
		
		graph.visitNodes(searchOptions).forEach(visitedNode -> {
			GenericTaskNtro<?,?> task = (GenericTaskNtro<?, ?>) ((TaskGraphNode<?,?>) visitedNode.node()).task();
			TaskTraceNtro trace = getTraces().get(task.id().toKey().toString());
			
			task.nextTasks().forEach(nextTask -> {

				TaskTraceNtro nextTrace = getTraces().get(nextTask.id().toKey().toString());
						
				TaskTraceNtro propagator = (TaskTraceNtro) nextTask.newTrace();
				
				trace.addNext(propagator);
				nextTrace.addPrevious(propagator);
			});
			
			if(task.hasParentTask()) {
				TaskTraceNtro parentTrace = getTraces().get(task.parentTask().id().toKey().toString());
			}
			
		});
	}
	
	private String parentChildRelationId(GenericTaskNtro<?,?> parent, GenericTaskNtro<?,?> child) {
		return parent.id().toKey().toString() + Path.FILENAME_SEPARATOR + child.id().toKey().toString();
	}

	private HierarchicalDagSearchOptions searchOptions(HierarchicalDag<?, ?> graph) {
		HierarchicalDagSearchOptions searchOptions = graph.defaultSearchOptions();

		InternalSearchOptionsNtro internal = (InternalSearchOptionsNtro) searchOptions.internal();
		internal.setDirections(new Direction[] {Direction.DOWN, Direction.FORWARD});

		searchOptions.copyOptions(internal);
		
		return searchOptions;
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
		
		String graphName = getGraph().getGraphName();
		graphName += "_" + currentState;
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
