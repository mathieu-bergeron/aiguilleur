package ca.ntro.core.task_graphs.task_graph;

public class TaskNtro<T  extends Task<T,AT,TG>, 
                      AT extends AtomicTask<T,AT,TG>,
                      TG extends TaskGraph<T,AT,TG>> 

	   implements Task<T,AT,TG> {
	
	private TaskId id;
	private TG graph;
	private TaskState state = TaskState.QUEUED;

	public TaskId getId() {
		return id;
	}

	public void setId(TaskId id) {
		this.id = id;
	}

	public TG getGraph() {
		return graph;
	}

	public void setGraph(TG graph) {
		this.graph = graph;
	}

	@Override
	public TaskId id() {
		return getId();
	}

	@Override
	public TG parentGraph() {
		return getGraph();
	}

	public TaskState getState() {
		return state;
	}

	public void setState(TaskState state) {
		this.state = state;
	}
	
	public TaskNtro(TaskId id, TG graph) {
		setId(id);
		setGraph(graph);
	}

	@Override
	public boolean isQueued() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isInProgress() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isDone() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public AT findEntryTask(AtomicTaskId id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AT findExitTask(AtomicTaskId id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T addSubTask(T subTask) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T addPreviousTask(T previousTask) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T addNextTask(T nextTask) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T addEntryTask(AT entryTask) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T addExitTask(AT exitTask) {
		// TODO Auto-generated method stub
		return null;
	}

}
