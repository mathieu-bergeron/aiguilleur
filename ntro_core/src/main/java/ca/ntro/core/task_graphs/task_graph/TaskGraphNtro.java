package ca.ntro.core.task_graphs.task_graph;

import java.util.Map;

import ca.ntro.core.exceptions.Break;
import ca.ntro.core.graph_writer.GraphWriter;
import ca.ntro.core.graphs.hierarchical_dag.HierarchicalDagNodeBuilder;
import ca.ntro.core.graphs.hierarchical_dag.HierarchicalDagWriterOptionsNtro;
import ca.ntro.core.identifyers.Key;
import ca.ntro.core.initialization.Factory;
import ca.ntro.core.stream.Stream;
import ca.ntro.core.task_graphs.task_graph_writer.InternalTaskGraphWriter;
import ca.ntro.core.task_graphs.task_graph_writer.InternalTaskGraphWriterNtro;

public class TaskGraphNtro<T  extends Task<T,AT>, 
                           AT extends AtomicTask<T,AT>>

	   implements TaskGraph<T,AT> {
	
	private InternalHierarchicalDagBuilderNtro<T,AT> hdagBuilder = new InternalHierarchicalDagBuilderNtro<T,AT>();

	private InternalTaskGraphWriter<T,AT> internalWriter = new InternalTaskGraphWriterNtro<>();
	
	private Class<T> taskClass;
	private Class<AT> atomicTaskClass;
	
	public InternalHierarchicalDagBuilderNtro<T, AT> getHdagBuilder() {
		return hdagBuilder;
	}

	public void setHdagBuilder(InternalHierarchicalDagBuilderNtro<T, AT> hdagBuilder) {
		this.hdagBuilder = hdagBuilder;
	}

	public InternalTaskGraphWriter<T, AT> getInternalWriter() {
		return internalWriter;
	}

	public void setInternalWriter(InternalTaskGraphWriter<T, AT> internalWriter) {
		this.internalWriter = internalWriter;
	}

	public Class<T> getTaskClass() {
		return taskClass;
	}

	public void setTaskClass(Class<T> taskClass) {
		this.taskClass = taskClass;
	}

	public Class<AT> getAtomicTaskClass() {
		return atomicTaskClass;
	}

	public void setAtomicTaskClass(Class<AT> atomicTaskClass) {
		this.atomicTaskClass = atomicTaskClass;
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

		return tasks().reduceToResult((AT) null, (accumulator, task) -> {

			if(accumulator != null) {
				throw new Break();
			}

			accumulator = task.findAtomicTask(id);
			
			return accumulator;

		}).value();
	}

	@Override
	public void write(GraphWriter writer) {
		internalWriter.write(hdagBuilder.graph(), new HierarchicalDagWriterOptionsNtro(), writer);
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
	public AtomicTaskNtro<T, AT> newAtomicTaskInstance() {
		return (AtomicTaskNtro<T,AT>) Factory.newInstance(atomicTaskClass);
	}

	@Override
	public Stream<T> tasks() {
		return hdagBuilder.graph().nodes().map(node -> node.task());
	}
}
