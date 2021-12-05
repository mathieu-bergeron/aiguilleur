package ca.ntro.core.task_graphs.task_graph;

import java.util.HashMap;
import java.util.Map;

import ca.ntro.core.exceptions.Break;
import ca.ntro.core.graphs.Direction;
import ca.ntro.core.graphs.SearchOptions;
import ca.ntro.core.graphs.SearchOptionsNtro;
import ca.ntro.core.graphs.SearchStrategy;
import ca.ntro.core.stream.GenericReducer;
import ca.ntro.core.stream.InternalReducer;
import ca.ntro.core.stream.Stream;
import ca.ntro.core.stream.StreamNtro;
import ca.ntro.core.wrappers.result.Result;
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
		return ifAllPreviousTasksMatch(previousTask -> {
			return previousTask.isDone();
		});
	}
	
	protected boolean areParentEntryTasksDone() {
		boolean done = true;

		if(hasParentTask()) {
			done = parentTask().ifAllEntryTasksMatch(entryTask -> {
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
		return ifAllEntryTasksMatch(entryTask -> {
			return isAtomicTaskDone(entryTask);
		}); 
	}

	private boolean isAtomicTaskDone(AT atomicTask) {
		return atomicTask.result().hasValue()
				&& !atomicTask.result().hasException();
	}

	protected boolean areExitTasksDone() {
		return ifAllExitTasksMatch(exitTask -> {
			return isAtomicTaskDone(exitTask);
		}); 
	}

	protected boolean areSubTasksDone() {
		return ifAllSubTasksMatch(subTask -> {
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

	@Override
	public void forEachPreviousTask(TaskVisitor<T, AT> visitor) {
		reducePreviousTasks(null, (__, task) -> {

			visitor.visitTask(task);

			return null;

		}).throwException();
	}

	@Override
	public boolean ifSomePreviousTaskMatches(TaskMatcher<T, AT> matcher) {
		return ifSomeNeighborTaskMatches(Direction.BACKWARD, matcher);
	}

	@Override
	public boolean ifAllPreviousTasksMatch(TaskMatcher<T, AT> matcher) {
		return ifAllNeighborTasksMatch(Direction.BACKWARD, matcher);
	}

	@Override
	public <R> Result<R> reducePreviousTasks(R initialValue, TaskReducer<T, AT, R> reducer) {
		ResultNtro<R> result = new ResultNtro<R>(initialValue);

		reduceNeighborTasks(Direction.BACKWARD, result, reducer);

		return result;
	}

	protected <R> void reduceNeighborTasks(Direction direction, ResultNtro<R> result, TaskReducer<T, AT, R> reducer) {

		_reduceReachableTasks(neighborSearchOptions(direction), result, reducer);
	}

	protected <R> void reduceNeighborTasks(Direction direction, ResultNtro<R> result, InternalReducer<T,R> reducer) {

		_reduceReachableTasksInternal(neighborSearchOptions(direction), result, reducer);
	}
	
	protected SearchOptions neighborSearchOptions(Direction direction) {

		SearchOptionsNtro options = new SearchOptionsNtro();
		options.setSearchStrategy(SearchStrategy.DEPTH_FIRST_SEARCH);
		options.setDirections(new Direction[] {direction});
		options.setMaxDistance(1);
		options.setSortEdgesByName(false);
		
		return options;
	}


	protected boolean ifSomeNeighborTaskMatches(Direction direction, TaskMatcher<T, AT> matcher) {
		return _ifSomeReachableTaskMatches(neighborSearchOptions(direction), matcher);
	}

	protected boolean ifAllNeighborTasksMatch(Direction direction, TaskMatcher<T, AT> matcher) {
		return _ifAllReachableTasksMatch(neighborSearchOptions(direction), matcher);
	}

	@Override
	public void forEachSubTask(TaskVisitor<T, AT> visitor) {
		reduceSubTasks(null, (__, task) -> {

			visitor.visitTask(task);

			return null;

		}).throwException();
	}

	@Override
	public <R> Result<R> reduceSubTasks(R initialValue, TaskReducer<T, AT, R> reducer) {
		ResultNtro<R> result = new ResultNtro<R>(initialValue);

		reduceNeighborTasks(Direction.DOWN, result, reducer);

		return result;
	}

	@Override
	public void forEachNextTask(TaskVisitor<T, AT> visitor) {
		reduceNextTasks(null, (__, task) -> {

			visitor.visitTask(task);

			return null;

		}).throwException();
	}

	@Override
	public <R> Result<R> reduceNextTasks(R initialValue, TaskReducer<T, AT, R> reducer) {
		ResultNtro<R> result = new ResultNtro<R>(initialValue);

		reduceNeighborTasks(Direction.FORWARD, result, reducer);

		return result;
	}



	@Override
	public boolean ifAllSubTasksMatch(TaskMatcher<T, AT> matcher) {
		return ifAllNeighborTasksMatch(Direction.DOWN, matcher);
	}

	@Override
	public boolean ifSomeSubTaskMatches(TaskMatcher<T, AT> matcher) {
		return ifSomeNeighborTaskMatches(Direction.DOWN, matcher);
	}


	@Override
	public boolean ifAllNextTasksMatch(TaskMatcher<T, AT> matcher) {
		return ifAllNeighborTasksMatch(Direction.FORWARD, matcher);
	}

	@Override
	public boolean ifSomeNextTaskMatches(TaskMatcher<T, AT> matcher) {
		return ifSomeNeighborTaskMatches(Direction.FORWARD, matcher);
	}


	@Override
	public boolean ifAllReachableTasksMatch(TaskMatcher<T, AT> matcher) {
		return _ifAllReachableTasksMatch(defaultOptions(), matcher);
	}

	protected boolean _ifAllReachableTasksMatch(SearchOptions options, TaskMatcher<T, AT> matcher) {
		ResultNtro<Boolean> result = new ResultNtro<>(true);
		
		_reduceReachableTasks(options, result, (accumulator, task) -> {
			if(accumulator == false) {
				throw new Break();
			}
			
			if(!matcher.matches(task)) {
				accumulator = false;
			}
			
			return accumulator;
		});
		
		return result.value();
	}
	
	protected TaskGraphSearchOptionsBuilder defaultSearchOptions() {
		return new TaskGraphSearchOptionsBuilderNtro();
	}
	
	private SearchOptions defaultOptions() {
		return defaultSearchOptions().toSearchOptions();
	}

	@Override
	public boolean ifSomeReachableTaskMatches(TaskMatcher<T, AT> matcher) {
		return _ifSomeReachableTaskMatches(defaultOptions(), matcher);
	}

	protected boolean _ifSomeReachableTaskMatches(SearchOptions options, TaskMatcher<T, AT> matcher) {
		ResultNtro<Boolean> result = new ResultNtro<>(false);

		_reduceReachableTasks(options, result, (accumulator, task) -> {
			if(accumulator == true) {
				throw new Break();
			}
			
			if(matcher.matches(task)) {
				accumulator = true;
			}
			
			return accumulator;
		});
		
		return result.value();
	}

	@Override
	public void forEachReachableTask(TaskVisitor<T, AT> visitor) {
		_forEachReachableTask(defaultOptions(), visitor);
	}

	@Override
	public <R> Result<R> reduceReachableTasks(R initialValue, TaskReducer<T, AT, R> reducer) {
		ResultNtro<R> result = new ResultNtro<>(initialValue);

		_reduceReachableTasks(defaultOptions(), result, reducer);
		
		return result;
	}

	@Override
	public boolean ifAllReachableTasksMatch(TaskGraphSearchOptionsBuilder options, TaskMatcher<T, AT> matcher) {
		return _ifAllReachableTasksMatch(options.toSearchOptions(), matcher);
	}

	@Override
	public boolean ifSomeReachableTaskMatches(TaskGraphSearchOptionsBuilder options, TaskMatcher<T, AT> matcher) {
		return _ifSomeReachableTaskMatches(options.toSearchOptions(), matcher);
	}

	@Override
	public void forEachReachableTask(TaskGraphSearchOptionsBuilder options, TaskVisitor<T, AT> visitor) {
		_forEachReachableTask(options.toSearchOptions(), visitor);
	}
	
	protected void _forEachReachableTask(SearchOptions options, TaskVisitor<T, AT> visitor) {
		ResultNtro<?> result = new ResultNtro<>();

		_reduceReachableTasks(options, result, (__, task) -> {
			
			visitor.visitTask(task);
			
			return null;
		});
	}

	@Override
	public <R> Result<R> reduceReachableTasks(TaskGraphSearchOptionsBuilder options, 
			                                  R initialValue, 
			                                  TaskReducer<T, AT, R> reducer) {
		
		ResultNtro<R> result = new ResultNtro<R>(initialValue);

		_reduceReachableTasks(options.toSearchOptions(), result, reducer);
		
		return result;
	}

	protected <R> void _reduceReachableTasks(SearchOptions options, 
			                                 ResultNtro<R> result, 
			                                 TaskReducer<T, AT, R> reducer) {

		getNode().reduceReachableNodes(options, (__, walked, node) -> {
			
			try {

				result.registerValue(reducer.reduceTask(result.value(), node.task()));

			}catch(Throwable t) {
				
				result.registerException(t);
			}
			
			return null;
		});
	}

	protected <R> void _reduceReachableTasksInternal(SearchOptions options, 
			                                         ResultNtro<R> result, 
			                                         InternalReducer<T,R> reducer) {

		getNode().reduceReachableNodes(options, (__, walked, node) -> {
			
			try {

				reducer.reduce(result, node.task());

			}catch(Throwable t) {
				
				result.registerException(t);
			}
			
			return null;
		});
	}

	@Override
	public T findFirstReachableTaskThatMatches(TaskMatcher<T, AT> matcher) {
		return _findFirstReachableTaskThatMatches(defaultOptions(), matcher);
	}

	@Override
	public T findFirstReachableTaskThatMatches(TaskGraphSearchOptionsBuilder options, TaskMatcher<T, AT> matcher) {
		return _findFirstReachableTaskThatMatches(options.toSearchOptions(), matcher);
	}

	protected T _findFirstReachableTaskThatMatches(SearchOptions options, TaskMatcher<T, AT> matcher) {
		ResultNtro<T> result = new ResultNtro<T>();
		
		_forEachReachableTask(options, (task) -> {
			
			if(matcher.matches(task)) {
				result.registerValue(task);
				
				throw new Break();
			}
		});
		
		return result.value();
	}

	@Override
	public T findFirstPreviousTaskThatMatches(TaskMatcher<T, AT> matcher) {
		return _findFirstReachableTaskThatMatches(neighborSearchOptions(Direction.BACKWARD), matcher);
	}

	@Override
	public T findFirstSubTaskThatMatches(TaskMatcher<T, AT> matcher) {
		return _findFirstReachableTaskThatMatches(neighborSearchOptions(Direction.DOWN), matcher);
	}

	@Override
	public T findFirstNextTaskThatMatches(TaskMatcher<T, AT> matcher) {
		return _findFirstReachableTaskThatMatches(neighborSearchOptions(Direction.FORWARD), matcher);
	}
	
	
	

	@Override
	public AT findFirstEntryTaskThatMatches(AtomicTaskMatcher<T, AT> matcher) {
		ResultNtro<AT> result = new ResultNtro<AT>();
		
		reduceEntryTasks(null, (__, entryTask) -> {
			if(matcher.matches(entryTask)) {
				
				result.registerValue(entryTask);
				throw new Break();
			}

			return null;
		});
		
		return result.value();
	}
	
	@Override
	public AT findFirstExitTaskThatMatches(AtomicTaskMatcher<T, AT> matcher) {
		ResultNtro<AT> result = new ResultNtro<AT>();

		reduceExitTasks(null, (__, exitTask) -> {
			if(matcher.matches(exitTask)) {
				
				result.registerValue(exitTask);
				throw new Break();
			}

			return null;
		});
		
		return result.value();
	}

	@Override
	public boolean ifAllEntryTasksMatch(AtomicTaskMatcher<T,AT> matcher) {
		return ifAllAtomicTasksMatch(getEntryTasks(), matcher);
	}

	@Override
	public boolean ifSomeEntryTaskMatches(AtomicTaskMatcher<T,AT> matcher) {
		return ifSomeAtomicTaskMatches(getEntryTasks(), matcher);
	}

	@Override
	public void forEachEntryTask(AtomicTaskVisitor<T, AT> visitor) {
		reduceEntryTasks(null, (__, atomicTask) -> {
			
			visitor.visitAtomicTask(atomicTask);
			
			return null;
			
		}).value();
	}

	@Override
	public <R> Result<R> reduceEntryTasks(R initialValue, AtomicTaskReducer<T, AT, R> reducer) {
		ResultNtro<R> result = new ResultNtro<R>(initialValue);
		
		reduceAtomicTasks(getEntryTasks(), result, reducer);
		
		return result;
	}

	protected boolean ifSomeAtomicTaskMatches(Map<String, AT> atomicTasks, 
			                             	  AtomicTaskMatcher<T,AT> matcher) {
		
		ResultNtro<Boolean> result = new ResultNtro<>(false);
		
		reduceAtomicTasks(atomicTasks, result, (accumulator, atomicTask) -> {
			if(accumulator == true) {
				throw new Break();
			}
			
			if(matcher.matches(atomicTask)) {
				accumulator = true;
			}
			
			return accumulator;
		});
		
		return result.value();
	}

	protected boolean ifAllAtomicTasksMatch(Map<String, AT> atomicTasks, 
			                             	AtomicTaskMatcher<T,AT> matcher) {
		
		ResultNtro<Boolean> result = new ResultNtro<>(true);
		
		reduceAtomicTasks(atomicTasks, result, (accumulator, atomicTask) -> {
			if(accumulator == false) {
				throw new Break();
			}
			
			if(!matcher.matches(atomicTask)) {
				accumulator = false;
			}
			
			return accumulator;
		});
		
		return result.value();
	}

	protected <R> void reduceAtomicTasks(Map<String, AT> atomicTasks, 
			                             ResultNtro<R> result, 
			                             AtomicTaskReducer<T, AT, R> reducer) {
		
		for(AT atomicTask : atomicTasks.values()) {
			try {
				
				result.registerValue(reducer.reduceAtomicTask(result.value(), atomicTask));
				
			} catch(Throwable t) {
				
				result.registerException(t);
				break;
			}
		}
	}

	@Override
	public boolean ifAllExitTasksMatch(AtomicTaskMatcher<T, AT> matcher) {
		return ifAllAtomicTasksMatch(getExitTasks(), matcher);
	}

	@Override
	public boolean ifSomeExitTaskMatches(AtomicTaskMatcher<T, AT> matcher) {
		return ifSomeAtomicTaskMatches(getExitTasks(), matcher);
	}


	@Override
	public void forEachExitTask(AtomicTaskVisitor<T, AT> visitor) {
		reduceExitTasks(null, (__, atomicTask) -> {
			
			visitor.visitAtomicTask(atomicTask);
			
			return null;
			
		}).value();
	}

	@Override
	public <R> Result<R> reduceExitTasks(R initialValue, AtomicTaskReducer<T, AT, R> reducer) {
		ResultNtro<R> result = new ResultNtro<R>(initialValue);
		
		reduceAtomicTasks(getExitTasks(), result, reducer);
		
		return result;
	}

	@Override
	public Stream<T> previousTasks() {
		return new StreamNtro<T>() {
			@Override
			protected <R> void _reduce(ResultNtro<R> result, InternalReducer<T, R> reducer) {
				reduceNeighborTasks(Direction.BACKWARD, result, reducer);
			}
		};
	}
}
