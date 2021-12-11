package ca.ntro.core.task_graphs.task_graph;

import ca.ntro.core.graphs.graph_writer.GraphWriter;
import ca.ntro.core.graphs.hierarchical_dag.HierarchicalDagNodeBuilder;
import ca.ntro.core.graphs.hierarchical_dag.HierarchicalDagWriterOptionsNtro;
import ca.ntro.core.initialization.Factory;

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
	public T findTask(TaskId id) {
		return getHdagBuilder().graph().findNode(id).task();
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
		
		
		TaskNtro<T,AT> task = (TaskNtro<T,AT>) Factory.newInstance(taskClass);
		
		task.setNodeBuilder(nodeBuilder);

		TaskGraphNodeNtro<T,AT> node = (TaskGraphNodeNtro<T, AT>) nodeBuilder.node();
		
		node.setTask((T) task);
		
		return (T) task;
	}

	@Override
	public T addTask(String id) {
		return addTask(new TaskIdNtro(id));
	}
}
