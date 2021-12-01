package ca.ntro.core.task_graphs.task_graph;

import ca.ntro.core.graphs.writers.GraphWriter;

public abstract class TaskGraphNtro<T  extends Task<T,AT,TG>, 
                                    AT extends AtomicTask<T,AT,TG>,
                                    TG extends TaskGraph<T,AT,TG>> 

	   implements TaskGraph<T,AT,TG> {
	
	private InternalHierarchicalDagBuilder<T,AT,TG> hdag = new InternalHierarchicalDagBuilderNtro<>();

	private InternalTaskGraphWriter<T,AT,TG> internalWriter = new InternalTaskGraphWriterNtro<>();

	public TaskGraphNtro() {
		hdag = new InternalHierarchicalDagBuilderNtro<T,AT,TG>();
	}

	public TaskGraphNtro(String graphName) {
		hdag = new InternalHierarchicalDagBuilderNtro<T,AT,TG>(graphName);
	}

	public InternalHierarchicalDagBuilder<T, AT, TG> getHdag() {
		return hdag;
	}

	public void setHdag(InternalHierarchicalDagBuilder<T, AT, TG> hdag) {
		this.hdag = hdag;
	}

	@Override
	public T findTask(TaskId id) {
		return hdag.findNode(id).task();
	}

	@Override
	public TG addTask(T task) {
		TaskGraphNode<T,AT,TG> node = new TaskGraphNodeBuilderNtro<T,AT,TG>(task.id());

		hdag.addNode(node);

		return (TG) this;
	}

	@Override
	public T createTask(String id) {
		return createTask(new TaskIdNtro(id));
	}

	@Override
	public T createTask(TaskId id) {
		TaskGraphNodeBuilder<T,AT,TG> node = new TaskGraphNodeBuilderNtro<T,AT,TG>(id);
		
		T task = createTask(id, node, (TG) this);
		
		return (T) task;
	}
	
	protected abstract T createTask(TaskId id, TaskGraphNodeBuilder<T,AT,TG> node, TG graph);
	

	@Override
	public void write(GraphWriter writer) {
		internalWriter.write(hdag, writer);
	}
}
