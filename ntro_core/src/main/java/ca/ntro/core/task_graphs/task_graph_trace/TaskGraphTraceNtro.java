package ca.ntro.core.task_graphs.task_graph_trace;

import java.util.HashMap;
import java.util.Map;

import ca.ntro.core.graph_writer.GraphWriter;
import ca.ntro.core.graphs.generics.graph.GenericGraph;
import ca.ntro.core.graphs.hierarchical_dag.HierarchicalDagWriterOptionsNtro;
import ca.ntro.core.identifyers.Id;
import ca.ntro.core.stream.Stream;
import ca.ntro.core.stream.StreamNtro;
import ca.ntro.core.stream.Visitor;
import ca.ntro.core.task_graphs.generic_task_graph.GenericAtomicTask;
import ca.ntro.core.task_graphs.generic_task_graph.GenericAtomicTaskNtro;
import ca.ntro.core.task_graphs.generic_task_graph.GenericTaskGraphNtro;
import ca.ntro.core.task_graphs.task_graph_writer.InternalTaskGraphTraceWriterNtro;
import ca.ntro.core.values.ObjectMap;

public class      TaskGraphTraceNtro 

       implements TaskGraphTrace, ObjectMap {
	
	private GenericTaskGraphNtro<?,?> graph;
	private Map<String, AtomicTaskTraceNtro> traces = new HashMap<>();
	private InternalTaskGraphTraceWriterNtro<?,?> internalWriter = new InternalTaskGraphTraceWriterNtro(this);

	public GenericTaskGraphNtro<?, ?> getGraph() {
		return graph;
	}

	public void setGraph(GenericTaskGraphNtro<?, ?> graph) {
		this.graph = graph;
	}
	
	
	
	public TaskGraphTraceNtro() {
	}


	public TaskGraphTraceNtro(GenericTaskGraphNtro<?, ?> graph) {
		setGraph(graph);
	}
	
	public void initialize() {
		getGraph().tasks().forEach(task -> {

			task.entryTasks().forEach(entryTask -> initializeAtomicTaskTrace(entryTask));
			task.exitTasks().forEach(exitTask -> initializeAtomicTaskTrace(exitTask));
		});
	}
	
	private void initializeAtomicTaskTrace(GenericAtomicTask<?,?> atomicTask) {
		AtomicTaskTraceNtro trace = (AtomicTaskTraceNtro) atomicTask.newTrace();
		
		trace.setTask((GenericAtomicTaskNtro<?,?>) atomicTask);
		
		traces.put(atomicTask.id().toKey().toString(), trace);
	}
	
	private Stream<AtomicTaskTraceNtro> traces(){
		return new StreamNtro<AtomicTaskTraceNtro>() {
			@Override
			public void _forEach(Visitor<AtomicTaskTraceNtro> visitor) throws Throwable {
				for(AtomicTaskTraceNtro trace : traces.values()) {
					visitor.visit(trace);
				}
			}
		};
	}
	
	

	@Override
	public void write(GraphWriter writer) {
		
		String graphName = getGraph().getGraphName();
		graphName += "__trace00";
		getGraph().setGraphName(graphName);
		
		internalWriter.write((GenericGraph) getGraph().getHdagBuilder().getGraph(), new HierarchicalDagWriterOptionsNtro(), writer);
	}

	@Override
	public boolean contains(Id id) {
		return contains(id.toKey().toString());
	}

	@Override
	public boolean contains(String id) {
		AtomicTaskTrace trace = traces.get(id);
		return trace != null
				&& trace.hasCurrent();
	}

	@SuppressWarnings("unchecked")
	@Override
	public <O> O get(Class<O> _class, Id id) {
		return (O) get(id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <O> O get(Class<O> _class, String id) {
		return (O) get(id);
	}

	@Override
	public Object get(Id id) {
		return get(id.toKey().toString());
	}

	@Override
	public Object get(String id) {
		Object result = null;
		
		AtomicTaskTraceNtro trace = traces.get(id);
		
		if(trace != null
				&& trace.hasCurrent()) {
			
			result = trace.current();
		}
		
		return result;
	}


	@Override
	public Stream<String> ids() {
		return new StreamNtro<String>() {
			@Override
			public void _forEach(Visitor<String> visitor) throws Throwable {
				for(String key : traces.keySet()) {
					visitor.visit(key);
				}
			}
		};
	}

	@Override
	public Stream<Object> objects() {
		return traces().reduceToStream((trace, visitor) -> {
			if(trace.hasCurrent()) {
				visitor.visit(trace.current());
			}
		});
	}

	@Override
	public boolean hasCurrent() {
		return traces().ifSome(trace -> trace.hasCurrent());
	}

	@Override
	public ObjectMap current() {
		return (ObjectMap) this;
	}

	@Override
	public boolean isWaiting() {
		return traces().ifSome(trace -> trace.isWaiting());
	}

	@Override
	public boolean hasNext() {
		return traces().ifSome(trace -> trace.hasNext());
	}

	@Override
	public void advanceToNext() {
		traces().forEach(trace -> trace.advanceToNext());
	}


}
