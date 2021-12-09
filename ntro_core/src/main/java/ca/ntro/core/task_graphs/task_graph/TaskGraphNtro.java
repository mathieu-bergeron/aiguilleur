package ca.ntro.core.task_graphs.task_graph;

import ca.ntro.core.graphs.graph_writer.GraphWriter;
import ca.ntro.core.graphs.hierarchical_dag.HierarchicalDagBuilder;
import ca.ntro.core.graphs.hierarchical_dag.HierarchicalDagNodeBuilder;

public abstract class TaskGraphNtro<T  extends Task<T,AT>, 
                                    AT extends AtomicTask<T,AT>>

	   implements TaskGraph<T,AT> {
	
	@SuppressWarnings("unchecked")
	private HierarchicalDagBuilder<TaskGraphNode<T,AT>,
	                               TaskGraphEdge<T,AT>> hdagBuilder = HierarchicalDagBuilder.newBuilder(TaskGraphNodeNtro.class,
	                                		                                                                TaskGraphEdgeNtro.class);

	private InternalTaskGraphWriter<T,AT> internalWriter = new InternalTaskGraphWriterNtro<>();

	public HierarchicalDagBuilder<TaskGraphNode<T, AT>, TaskGraphEdge<T, AT>> getHdagBuilder() {
		return hdagBuilder;
	}

	public void setHdagBuilder(HierarchicalDagBuilder<TaskGraphNode<T, AT>, TaskGraphEdge<T, AT>> hdagBuilder) {
		this.hdagBuilder = hdagBuilder;
	}

	public InternalTaskGraphWriter<T, AT> getInternalWriter() {
		return internalWriter;
	}

	public void setInternalWriter(InternalTaskGraphWriter<T, AT> internalWriter) {
		this.internalWriter = internalWriter;
	}

	public TaskGraphNtro() {
	}

	public TaskGraphNtro(String graphName) {
	}

	@Override
	public T findTask(TaskId id) {
		return hdagBuilder.graph().findNode(id).task();
	}

	@Override
	public void addTask(T task) {

		hdagBuilder.addNode(task.id());
	}

	@Override
	public T createTask(String id) {
		return createTask(new TaskIdNtro(id));
	}

	@Override
	public T createTask(TaskId id) {
		HierarchicalDagNodeBuilder<TaskGraphNode<T,AT>,TaskGraphEdge<T,AT>> node = (HierarchicalDagNodeBuilder<TaskGraphNode<T,AT>, TaskGraphEdge<T,AT>>) getHdagBuilder().addNode(id);

		T task = createTaskImpl(id, node, (TaskGraph<T,AT>) this);
		
		return (T) task;
	}
	
	// JSweet: createTaskImpl to avoid error: "supplied parameters do not match any signature of call target"
	protected abstract T createTaskImpl(TaskId id, HierarchicalDagNodeBuilder<TaskGraphNode<T,AT>,TaskGraphEdge<T,AT>> node, TaskGraph<T,AT> graph);

	@Override
	public void write(GraphWriter writer) {
		internalWriter.write(hdagBuilder.graph(), writer);
	}
}
