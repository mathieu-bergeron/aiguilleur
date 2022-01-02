package ca.ntro.core.task_graphs.task_graph_trace;

import java.util.HashMap;
import java.util.Map;

import ca.ntro.core.graph_writer.GraphWriter;
import ca.ntro.core.graphs.generics.graph.GenericGraph;
import ca.ntro.core.graphs.hierarchical_dag.HierarchicalDagWriterOptionsNtro;
import ca.ntro.core.identifyers.Id;
import ca.ntro.core.stream.Stream;
import ca.ntro.core.task_graphs.generic_task_graph.GenericAtomicTask;
import ca.ntro.core.task_graphs.generic_task_graph.GenericAtomicTaskNtro;
import ca.ntro.core.task_graphs.generic_task_graph.GenericTaskGraphNtro;
import ca.ntro.core.task_graphs.task_graph_writer.InternalTaskGraphTraceWriterNtro;
import ca.ntro.core.values.ObjectMap;

public class TaskGraphTraceNtro 

       extends GenericTraceNtro<ObjectMap>

       implements TaskGraphTrace, ObjectMap {
	
	private GenericTaskGraphNtro<?,?> graph;
	private Map<String, AtomicTaskTrace> traces = new HashMap<>();
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
		return trace.hasCurrent();
	}

	@Override
	public <O> O get(Class<O> _class, Id id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <O> O get(Class<O> _class, String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object get(Id id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object get(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addAll(ObjectMap other) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Stream<String> keys() {
		// TODO Auto-generated method stub
		return null;
	}

}
