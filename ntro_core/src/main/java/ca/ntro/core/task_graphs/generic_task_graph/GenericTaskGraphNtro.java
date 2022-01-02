package ca.ntro.core.task_graphs.generic_task_graph;


import java.util.HashMap;
import java.util.Map;

import ca.ntro.core.graph_writer.GraphWriter;
import ca.ntro.core.graphs.hierarchical_dag.HierarchicalDagNodeBuilder;
import ca.ntro.core.graphs.hierarchical_dag.HierarchicalDagWriterOptionsNtro;
import ca.ntro.core.identifyers.Key;
import ca.ntro.core.initialization.Factory;
import ca.ntro.core.stream.Stream;
import ca.ntro.core.task_graphs.task_graph_trace.TaskGraphTrace;
import ca.ntro.core.task_graphs.task_graph_trace.TaskGraphTraceNtro;
import ca.ntro.core.task_graphs.task_graph_writer.InternalTaskGraphWriterNtro;
import ca.ntro.core.values.ObjectMap;
import ca.ntro.core.values.ObjectMapNtro;

public class      GenericTaskGraphNtro<T  extends GenericTask<T,AT>, 
                                       AT extends GenericAtomicTask<T,AT>>

	   implements GenericTaskGraph<T,AT> {
	
	private InternalHierarchicalDagBuilderNtro<T,AT> hdagBuilder = new InternalHierarchicalDagBuilderNtro<T,AT>();

	private InternalTaskGraphWriterNtro<T,AT> internalWriter = new InternalTaskGraphWriterNtro<>();
	
	private Class<? extends T> taskClass;
	private Class<? extends AT> defaultAtomicTaskClass;
	
	private Map<String, GenericAtomicTaskNtro<T,AT>> atomicTasks = new HashMap<>();

	public InternalHierarchicalDagBuilderNtro<T, AT> getHdagBuilder() {
		return hdagBuilder;
	}

	public void setHdagBuilder(InternalHierarchicalDagBuilderNtro<T, AT> hdagBuilder) {
		this.hdagBuilder = hdagBuilder;
	}

	public InternalTaskGraphWriterNtro<T, AT> getInternalWriter() {
		return internalWriter;
	}

	public void setInternalWriter(InternalTaskGraphWriterNtro<T, AT> internalWriter) {
		this.internalWriter = internalWriter;
	}

	public Class<? extends T> getTaskClass() {
		return taskClass;
	}

	public void setTaskClass(Class<? extends T> taskClass) {
		this.taskClass = taskClass;
	}

	public Class<? extends AT> getDefaultAtomicTaskClass() {
		return defaultAtomicTaskClass;
	}

	public void setDefaultAtomicTaskClass(Class<? extends AT> defaultAtomicTaskClass) {
		this.defaultAtomicTaskClass = defaultAtomicTaskClass;
	}

	public Map<String, GenericAtomicTaskNtro<T, AT>> getAtomicTasks() {
		return atomicTasks;
	}

	public void setAtomicTasks(Map<String, GenericAtomicTaskNtro<T, AT>> atomicTasks) {
		this.atomicTasks = atomicTasks;
	}
	
	
	

	public GenericTaskGraphNtro() {
	}
	
	
	
	
	public void initialize() {
		getHdagBuilder().setNodeFactory(() -> {
			return new TaskGraphNodeNtro<>();
		});

		getHdagBuilder().setEdgeFactory(() -> {
			return new TaskGraphEdgeNtro<>();
		});
		
		getHdagBuilder().initialize();
	}
	
	@Override
	public T findTask(String id) {
		return findTask(new TaskIdNtro(id));
	}

	@Override
	public T findTask(TaskId id) {
		return getHdagBuilder().graph().findNode(id).task();
	}

	@Override
	public AT findAtomicTask(String id) {
		return findAtomicTask(AtomicTaskId.fromKey(new Key(id)));
	}

	@Override
	public AT findAtomicTask(AtomicTaskId id) {
		return (AT) getAtomicTasks().get(id.toKey().toString());
	}

	@Override
	public void write(GraphWriter writer) {
		HierarchicalDagWriterOptionsNtro options = new HierarchicalDagWriterOptionsNtro();

		writer.initialize(getHdagBuilder().getGraph().id(), options);
		internalWriter.write(hdagBuilder.graph(), options, writer);
	}

	@Override
	public void setGraphName(String graphName) {
		getHdagBuilder().setGraphName(graphName);
	}

	public String getGraphName() {
		return getHdagBuilder().getGraph().id().toKey().toString();
	}

	@SuppressWarnings("unchecked")
	@Override
	public T addTask(TaskId id) {
		HierarchicalDagNodeBuilder<TaskGraphNode<T,AT>,TaskGraphEdge<T,AT>> nodeBuilder = hdagBuilder.addNode(id);
		
		GenericTaskNtro<T,AT> task = newTaskInstance();

		task.setNodeBuilder(nodeBuilder);
		
		task.setGraph(this);

		TaskGraphNodeNtro<T,AT> node = (TaskGraphNodeNtro<T, AT>) nodeBuilder.node();
		
		node.setTask((T) task);
		
		return (T) task;
	}

	@Override
	public T addTask(String id) {
		return addTask(new TaskIdNtro(id));
	}

	@SuppressWarnings("unchecked")
	private GenericTaskNtro<T,AT> newTaskInstance() {
		return (GenericTaskNtro<T,AT>) Factory.newInstance(taskClass);
	}

	@SuppressWarnings("unchecked")
	public GenericAtomicTaskNtro<T, AT> newAtomicTaskInstance(Class<? extends AT> atomicTaskClass) {
		return (GenericAtomicTaskNtro<T,AT>) Factory.newInstance(atomicTaskClass);
	}

	@SuppressWarnings("unchecked")
	public AT addAtomicTask(AtomicTaskId id, GenericTaskNtro<T,AT> parentTask) {
		GenericAtomicTaskNtro<T,AT> atomicTask =  (GenericAtomicTaskNtro<T, AT>) Factory.newInstance(defaultAtomicTaskClass);

		atomicTask.setId(id);
		atomicTask.setParentTask(parentTask);
		
		getAtomicTasks().put(id.toKey().toString(), atomicTask);
		
		return (AT) atomicTask;
	}

	@Override
	public Stream<T> tasks() {
		return hdagBuilder.graph().nodes().map(node -> node.task());
	}

	@Override
	public TaskGraphTrace newTrace() {
		/* TODO: create a GraphResultsIterator
		 * 
		 *       this mimmicks the graph structure
		 *       
		 *       and determines the state of each task
		 *       
		 *       when printing the state of the graph,
		 *       we need an iterator
		 *       
		 *       XXX: we should probably push results
		 *            only to the iterator copy
		 */

		return new TaskGraphTraceNtro(this);
	}
	
}
