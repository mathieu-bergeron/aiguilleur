package ca.ntro.core.task_graphs.task_graph;


import java.util.HashMap;
import java.util.Map;

import ca.ntro.core.graph_writer.GraphWriter;
import ca.ntro.core.graphs.hierarchical_dag.HierarchicalDagNodeBuilder;
import ca.ntro.core.graphs.hierarchical_dag.HierarchicalDagWriterOptionsNtro;
import ca.ntro.core.identifyers.Key;
import ca.ntro.core.initialization.Factory;
import ca.ntro.core.stream.Stream;
import ca.ntro.core.task_graphs.task_graph_writer.InternalTaskGraphWriterNtro;
import ca.ntro.core.values.ObjectMap;
import ca.ntro.core.values.ObjectMapNtro;

public class TaskGraphNtro<T  extends Task<T,AT>, 
                           AT extends AtomicTask<T,AT>>

	   implements TaskGraph<T,AT> {
	
	private InternalHierarchicalDagBuilderNtro<T,AT> hdagBuilder = new InternalHierarchicalDagBuilderNtro<T,AT>();

	private InternalTaskGraphWriterNtro<T,AT> internalWriter = new InternalTaskGraphWriterNtro<>();
	
	private Class<T> taskClass;
	private Class<AT> defaultAtomicTaskClass;
	
	private Map<String, AtomicTaskNtro<T,AT>> atomicTasks = new HashMap<>();

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

	public Class<T> getTaskClass() {
		return taskClass;
	}

	public void setTaskClass(Class<T> taskClass) {
		this.taskClass = taskClass;
	}

	public Class<AT> getDefaultAtomicTaskClass() {
		return defaultAtomicTaskClass;
	}

	public void setDefaultAtomicTaskClass(Class<AT> defaultAtomicTaskClass) {
		this.defaultAtomicTaskClass = defaultAtomicTaskClass;
	}

	public Map<String, AtomicTaskNtro<T, AT>> getAtomicTasks() {
		return atomicTasks;
	}

	public void setAtomicTasks(Map<String, AtomicTaskNtro<T, AT>> atomicTasks) {
		this.atomicTasks = atomicTasks;
	}
	
	
	

	public TaskGraphNtro() {
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

	@SuppressWarnings("unchecked")
	@Override
	public T addTask(TaskId id) {
		HierarchicalDagNodeBuilder<TaskGraphNode<T,AT>,TaskGraphEdge<T,AT>> nodeBuilder = hdagBuilder.addNode(id);
		
		TaskNtro<T,AT> task = newTaskInstance();

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
	private TaskNtro<T,AT> newTaskInstance() {
		return (TaskNtro<T,AT>) Factory.newInstance(taskClass);
	}

	@SuppressWarnings("unchecked")
	public AtomicTaskNtro<T, AT> newAtomicTaskInstance(Class<? extends AT> atomicTaskClass) {
		return (AtomicTaskNtro<T,AT>) Factory.newInstance(atomicTaskClass);
	}

	@SuppressWarnings("unchecked")
	public AT addAtomicTask(AtomicTaskId id, TaskNtro<T,AT> parentTask) {
		AtomicTaskNtro<T,AT> atomicTask =  (AtomicTaskNtro<T, AT>) Factory.newInstance(defaultAtomicTaskClass);

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
	public TaskGraphResultsIterator iterator() {
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

		return new TaskGraphResultsIterator(this);
	}
	
}
