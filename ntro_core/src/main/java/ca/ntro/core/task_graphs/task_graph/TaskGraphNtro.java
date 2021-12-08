package ca.ntro.core.task_graphs.task_graph;

import ca.ntro.core.graphs.graph_writer.GraphWriter;

public abstract class TaskGraphNtro<T  extends Task<T,AT>, 
                                    AT extends AtomicTask<T,AT>>

	   implements TaskGraph<T,AT> {
	
	private InternalHierarchicalDagBuilder<T,AT> hdagBuilder = new InternalHierarchicalDagBuilderNtro<>();

	private InternalTaskGraphWriter<T,AT> internalWriter = new InternalTaskGraphWriterNtro<>();

	public TaskGraphNtro() {
		hdagBuilder = new InternalHierarchicalDagBuilderNtro<T,AT>();
	}

	public TaskGraphNtro(String graphName) {
		hdagBuilder = new InternalHierarchicalDagBuilderNtro<T,AT>();
		hdagBuilder.setGraphName(graphName);
	}

	public InternalHierarchicalDagBuilder<T, AT> getHdag() {
		return hdagBuilder;
	}

	public void setHdag(InternalHierarchicalDagBuilder<T, AT> hdag) {
		this.hdagBuilder = hdag;
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
		TaskGraphNodeBuilder<T,AT> node = (TaskGraphNodeBuilder<T, AT>) getHdag().addNode(id);

		T task = createTaskImpl(id, node, (TaskGraph<T,AT>) this);
		
		return (T) task;
	}
	
	// JSweet error: "supplied parameters do not match any signature of call target"
	// if we keep name "createTask"
	protected abstract T createTaskImpl(TaskId id, TaskGraphNodeBuilder<T,AT> node, TaskGraph<T,AT> graph);
	

	@Override
	public void write(GraphWriter writer) {
		internalWriter.write(hdagBuilder.graph(), writer);
	}
}
