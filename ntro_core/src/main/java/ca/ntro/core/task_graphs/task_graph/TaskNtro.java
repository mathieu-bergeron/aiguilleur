package ca.ntro.core.task_graphs.task_graph;

import java.util.HashMap;
import java.util.Map;

import ca.ntro.core.graphs.Direction;
import ca.ntro.core.graphs.SearchOptions;
import ca.ntro.core.graphs.SearchOptionsNtro;
import ca.ntro.core.graphs.SearchStrategy;
import ca.ntro.core.stream._Reducer;
import ca.ntro.core.stream.Stream;
import ca.ntro.core.stream.StreamNtro;
import ca.ntro.core.wrappers.result.ResultNtro;

public class      TaskNtro<T  extends Task<T,AT>, 
                           AT extends AtomicTask<T,AT>>

	   implements Task<T,AT> {
	
	private TaskId id;
	private TaskGraph<T,AT> graph;
	private TaskGraphNodeBuilder<T,AT> node;

	private Map<String, AT> entryTasks = new HashMap<>();
	private Map<String, AT> exitTasks = new HashMap<>();

	public TaskId getId() {
		return id;
	}

	public void setId(TaskId id) {
		this.id = id;
	}

	public TaskGraph<T,AT> getGraph() {
		return graph;
	}

	public void setGraph(TaskGraph<T,AT> graph) {
		this.graph = graph;
	}
	
	public TaskGraphNodeBuilder<T, AT> getNode() {
		return node;
	}

	public void setNode(TaskGraphNodeBuilder<T, AT> node) {
		this.node = node;
	}

	public Map<String, AT> getEntryTasks() {
		return entryTasks;
	}

	public void setEntryTasks(Map<String, AT> entryTasks) {
		this.entryTasks = entryTasks;
	}

	public Map<String, AT> getExitTasks() {
		return exitTasks;
	}

	public void setExitTasks(Map<String, AT> exitTasks) {
		this.exitTasks = exitTasks;
	}


	public TaskNtro(TaskId id, TaskGraphNodeBuilder<T,AT> node, TaskGraph<T,AT> graph) {
		setId(id);
		setNode(node);
		setGraph(graph);
	}

	@Override
	public TaskId id() {
		return getId();
	}

	@Override
	public TaskGraph<T,AT> parentGraph() {
		return getGraph();
	}

	@Override
	public T parentTask() {
		T parentTask = null;

		if(getNode().hasParent()) {
			parentTask = getNode().parent().task();
		}
		
		return parentTask;
	}

	@Override
	public boolean hasParentTask() {
		return getNode().hasParent();
	}

	@Override
	public boolean isBlocked() {
		return !arePreviousTasksDone()
				|| !areParentEntryTasksDone();
	}

	protected boolean arePreviousTasksDone() {
		return previousTasks().ifAll(previousTask -> {
			return previousTask.isDone();
		});
	}
	
	protected boolean areParentEntryTasksDone() {
		boolean done = true;

		if(hasParentTask()) {
			done = parentTask().entryTasks().ifAll(entryTask -> {
				return entryTask.isDone();
			});
		}

		return done;
	}

	@Override
	public boolean isInProgress() {
		return !isBlocked()
				&& !isDone();
	}

	@Override
	public boolean isDone() {
		return !isBlocked()
				&& areEntryTasksDone()
				&& areSubTasksDone()
				&& areExitTasksDone();
	}
	
	protected boolean areEntryTasksDone() {
		return entryTasks().ifAll(entryTask -> {
			return isAtomicTaskDone(entryTask);
		}); 
	}

	private boolean isAtomicTaskDone(AT atomicTask) {
		return atomicTask.result().hasValue()
				&& !atomicTask.result().hasException();
	}

	protected boolean areExitTasksDone() {
		return exitTasks().ifAll(exitTask -> {
			return isAtomicTaskDone(exitTask);
		}); 
	}

	protected boolean areSubTasksDone() {
		return subTasks().ifAll(subTask -> {
			return subTask.isDone();
		});
	}

	@Override
	public AT findEntryTask(AtomicTaskId id) {
		return getEntryTasks().get(id.toKey().toString());
	}

	@Override
	public AT findExitTask(AtomicTaskId id) {
		return getExitTasks().get(id.toKey().toString());
	}

	@Override
	public T addSubTask(T subTask) {
		getNode().addSubNode(toNode(subTask));

		return asTask();
	}

	@SuppressWarnings("unchecked")
	private TaskGraphNodeBuilder<T,AT> toNode(T task) {
		return ((TaskNtro<T,AT>) task).getNode();
	}

	@SuppressWarnings("unchecked")
	private T asTask() {
		return (T) this;
	}
	
	@Override
	public T addPreviousTask(T previousTask) {
		toNode(previousTask).addEdge("", getNode());

		return asTask();
	}

	@Override
	public T addNextTask(T nextTask) {
		getNode().addEdge("", toNode(nextTask));

		return asTask();
	}

	@Override
	public T addEntryTask(AT entryTask) {
		getEntryTasks().put(entryTask.id().toKey().toString(), entryTask);

		return asTask();
	}

	@Override
	public T addExitTask(AT exitTask) {
		getExitTasks().put(exitTask.id().toKey().toString(), exitTask);

		return asTask();
	}

	protected <R> void _reduceNeighborTasks(Direction direction, ResultNtro<R> result, _Reducer<T,R> _reducer) {

		_reduceReachableTasksInternal(neighborSearchOptions(direction), result, _reducer);
	}
	
	protected SearchOptions neighborSearchOptions(Direction direction) {

		SearchOptionsNtro options = new SearchOptionsNtro();
		options.setSearchStrategy(SearchStrategy.DEPTH_FIRST_SEARCH);
		options.setDirections(new Direction[] {direction});
		options.setMaxDistance(1);
		options.setSortEdgesByName(false);
		
		return options;
	}

	protected TaskGraphSearchOptionsBuilder defaultSearchOptions() {
		return new TaskGraphSearchOptionsBuilderNtro();
	}

	protected <R> void _reduceReachableTasksInternal(SearchOptions options, 
			                                         ResultNtro<R> result, 
			                                         _Reducer<T,R> reducer) {

		getNode().reduceReachableNodes(options, (__, walked, node) -> {
			
			try {

				reducer._reduce(result, node.task());

			}catch(Throwable t) {
				
				result.registerException(t);
			}
			
			return null;
		});
	}

	protected <R> void _reduceAtomicTasks(Map<String, AT> atomicTasks, 
			                              ResultNtro<R> result, 
			                              _Reducer<AT,R> _reducer) {
		
		for(AT atomicTask : atomicTasks.values()) {
			try {

				_reducer._reduce(result, atomicTask);

			} catch(Throwable t) {
				
				result.registerException(t);
				break;
			}
		}
	}



	@Override
	public Stream<T> previousTasks() {
		return new StreamNtro<T>() {
			@Override
			public <R> void _reduce(ResultNtro<R> result, _Reducer<T, R> _reducer) {
				_reduceNeighborTasks(Direction.BACKWARD, result, _reducer);
			}
		};
	}

	@Override
	public Stream<AT> entryTasks() {
		return new StreamNtro<AT>() {
			@Override
			public <R> void _reduce(ResultNtro<R> result, _Reducer<AT, R> _reducer) {
				_reduceAtomicTasks(getEntryTasks(), result, _reducer);
			}
		};
	}

	@Override
	public Stream<T> subTasks() {
		return new StreamNtro<T>() {
			@Override
			public <R> void _reduce(ResultNtro<R> result, _Reducer<T, R> _reducer) {
				_reduceNeighborTasks(Direction.DOWN, result, _reducer);
			}
		};
	}

	@Override
	public Stream<AT> exitTasks() {
		return new StreamNtro<AT>() {
			@Override
			public <R> void _reduce(ResultNtro<R> result, _Reducer<AT, R> _reducer) {
				_reduceAtomicTasks(getExitTasks(), result, _reducer);
			}
		};
	}

	@Override
	public Stream<T> nextTasks() {
		return new StreamNtro<T>() {
			@Override
			public <R> void _reduce(ResultNtro<R> result, _Reducer<T, R> _reducer) {
				_reduceNeighborTasks(Direction.FORWARD, result, _reducer);
			}
		};
	}

	@Override
	public Stream<T> reachableTasks() {
		return reachableTasks(defaultSearchOptions());
	}

	@Override
	public Stream<T> reachableTasks(TaskGraphSearchOptionsBuilder options) {
		return new StreamNtro<T>() {
			@Override
			public <R> void _reduce(ResultNtro<R> result, _Reducer<T, R> _reducer) {
				
				getNode().reduceReachableNodes(options, (__, walked, node) -> {
					
					try {
						
						_reducer._reduce(result, node.task());

					}catch(Throwable t) {
						
						result.registerException(t);
					}
					
					return null;
				});
			}
		};
	}
}
