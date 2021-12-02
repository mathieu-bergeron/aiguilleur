package ca.ntro.core.task_graphs.task_graph;

import ca.ntro.core.graphs.writers.GraphWriter;

public abstract class TaskGraphNtro<T  extends Task<T,AT>, 
                                    AT extends AtomicTask<T,AT>>

	   implements TaskGraph<T,AT> {
	
	private InternalHierarchicalDagBuilder<T,AT> hdag = new InternalHierarchicalDagBuilderNtro<>();

	private InternalTaskGraphWriter<T,AT> internalWriter = new InternalTaskGraphWriterNtro<>();

	public TaskGraphNtro() {
		hdag = new InternalHierarchicalDagBuilderNtro<T,AT>();
	}

	public TaskGraphNtro(String graphName) {
		hdag = new InternalHierarchicalDagBuilderNtro<T,AT>(graphName);
	}

	public InternalHierarchicalDagBuilder<T, AT> getHdag() {
		return hdag;
	}

	public void setHdag(InternalHierarchicalDagBuilder<T, AT> hdag) {
		this.hdag = hdag;
	}

	@Override
	public T findTask(TaskId id) {
		return hdag.findNode(id).task();
	}

	@Override
	public void addTask(T task) {
		TaskGraphNode<T,AT> node = new TaskGraphNodeBuilderNtro<T,AT>(task.id());

		hdag.addNode(node);
	}

	@Override
	public T createTask(String id) {
		return createTask(new TaskIdNtro(id));
	}

	@Override
	public T createTask(TaskId id) {
		TaskGraphNodeBuilder<T,AT> node = new TaskGraphNodeBuilderNtro<T,AT>(id);
		
		T task = createTaskImpl(id, node, (TaskGraph<T,AT>) this);
		
		return (T) task;
	}
	
	// JSweet error: "supplied parameters do not match any signature of call target"
	// if we keep name "createTask"
	protected abstract T createTaskImpl(TaskId id, TaskGraphNodeBuilder<T,AT> node, TaskGraph<T,AT> graph);
	

	@Override
	public void write(GraphWriter writer) {
		internalWriter.write(hdag, writer);
	}
}
