package ca.ntro.core.task_graphs.task_graph;

import java.util.HashMap;
import java.util.Map;

import ca.ntro.core.exceptions.Break;
import ca.ntro.core.graphs.Direction;
import ca.ntro.core.graphs.SearchOptionsNtro;
import ca.ntro.core.graphs.SearchStrategy;
import ca.ntro.core.wrappers.result.Result;
import ca.ntro.core.wrappers.result.ResultNtro;

public class      TaskNtro<T  extends Task<T,AT,TG>, 
                           AT extends AtomicTask<T,AT,TG>,
                           TG extends TaskGraph<T,AT>> 

	   implements Task<T,AT,TG> {
	
	private TaskId id;
	private TG graph;
	private TaskGraphNodeBuilder<T,AT,TG> node;

	private TaskState state = TaskState.QUEUED;

	private Map<String, AT> entryTasks = new HashMap<>();
	private Map<String, AT> exitTasks = new HashMap<>();

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

	public TaskState getState() {
		return state;
	}

	public void setState(TaskState state) {
		this.state = state;
	}
	
	public TaskGraphNodeBuilder<T, AT, TG> getNode() {
		return node;
	}

	public void setNode(TaskGraphNodeBuilder<T, AT, TG> node) {
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


	public TaskNtro(TaskId id, TaskGraphNodeBuilder<T,AT,TG> node, TG graph) {
		setId(id);
		setNode(node);
		setGraph(graph);
	}

	@Override
	public TaskId id() {
		return getId();
	}

	@Override
	public TG parentGraph() {
		return getGraph();
	}

	@Override
	public boolean isQueued() {
		return ifSomePreviousTaskMatches(previousTask -> {
			return previousTask.isQueued() || previousTask.isInProgress();
		});
	}

	@Override
	public boolean isInProgress() {
		return !isQueued()
				&& !isDone();
	}

	@Override
	public boolean isDone() {
		return !isQueued()
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
				|| atomicTask.result().hasException();
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
	private TaskGraphNodeBuilder<T,AT,TG> toNode(T task) {
		return ((TaskNtro<T,AT,TG>) task).getNode();
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
	public void forEachPreviousTask(TaskVisitor<T, AT, TG> visitor) {
		reducePreviousTasks(null, (__, task) -> {

			visitor.visitTask(task);

			return null;

		}).throwException();
	}

	@Override
	public boolean ifSomePreviousTaskMatches(TaskMatcher<T, AT, TG> matcher) {
		return ifSomeNeighborTaskMatches(Direction.BACKWARD, matcher);
	}

	@Override
	public boolean ifAllPreviousTasksMatch(TaskMatcher<T, AT, TG> matcher) {
		return ifAllNeighborTasksMatch(Direction.BACKWARD, matcher);
	}

	@Override
	public <R> Result<R> reducePreviousTasks(R initialValue, TaskReducer<T, AT, TG, R> reducer) {
		ResultNtro<R> result = new ResultNtro<R>(initialValue);

		reduceNeighborTasks(Direction.BACKWARD, result, reducer);

		return result;
	}

	protected <R> void reduceNeighborTasks(Direction direction, ResultNtro<R> result, TaskReducer<T, AT, TG, R> reducer) {

		SearchOptionsNtro options = new SearchOptionsNtro();
		options.setSearchStrategy(SearchStrategy.DEPTH_FIRST_SEARCH);
		options.setDirections(new Direction[] {direction});
		options.setMaxDistance(1);
		options.setSortEdgesByName(false);

		getNode().reduceReachableNodes(options, (__, walked, node) -> {
			
			try {

				result.registerValue(reducer.reduceTask(result.value(), node.task()));

			}catch(Throwable t) {
				
				result.registerException(t);
			}
			
			return null;
		});
	}

	protected boolean ifSomeNeighborTaskMatches(Direction direction, TaskMatcher<T, AT, TG> matcher) {
		ResultNtro<Boolean> result = new ResultNtro<>(false);
		
		reduceNeighborTasks(direction, result, (accumulator, task) -> {
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

	protected boolean ifAllNeighborTasksMatch(Direction direction, TaskMatcher<T, AT, TG> matcher) {
		ResultNtro<Boolean> result = new ResultNtro<>(true);
		
		reduceNeighborTasks(direction, result, (accumulator, task) -> {
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

	@Override
	public void forEachSubTask(TaskVisitor<T, AT, TG> visitor) {
		reduceSubTasks(null, (__, task) -> {

			visitor.visitTask(task);

			return null;

		}).throwException();
	}

	@Override
	public <R> Result<R> reduceSubTasks(R initialValue, TaskReducer<T, AT, TG, R> reducer) {
		ResultNtro<R> result = new ResultNtro<R>(initialValue);

		reduceNeighborTasks(Direction.DOWN, result, reducer);

		return result;
	}

	@Override
	public void forEachNextTask(TaskVisitor<T, AT, TG> visitor) {
		reduceNextTasks(null, (__, task) -> {

			visitor.visitTask(task);

			return null;

		}).throwException();
	}

	@Override
	public <R> Result<R> reduceNextTasks(R initialValue, TaskReducer<T, AT, TG, R> reducer) {
		ResultNtro<R> result = new ResultNtro<R>(initialValue);

		reduceNeighborTasks(Direction.FORWARD, result, reducer);

		return result;
	}

	@Override
	public boolean ifAllEntryTasksMatch(AtomicTaskMatcher<T,AT,TG> matcher) {
		return ifAllAtomicTasksMatch(getEntryTasks(), matcher);
	}

	@Override
	public boolean ifSomeEntryTaskMatches(AtomicTaskMatcher<T,AT,TG> matcher) {
		return ifSomeAtomicTaskMatches(getEntryTasks(), matcher);
	}

	@Override
	public void forEachEntryTask(AtomicTaskVisitor<T, AT, TG> visitor) {
		reduceEntryTasks(null, (__, atomicTask) -> {
			
			visitor.visitAtomicTask(atomicTask);
			
			return null;
			
		}).value();
	}

	@Override
	public <R> Result<R> reduceEntryTasks(R initialValue, AtomicTaskReducer<T, AT, TG, R> reducer) {
		ResultNtro<R> result = new ResultNtro<R>(initialValue);
		
		reduceAtomicTasks(getEntryTasks(), result, reducer);
		
		return result;
	}

	protected boolean ifSomeAtomicTaskMatches(Map<String, AT> atomicTasks, 
			                             	  AtomicTaskMatcher<T,AT,TG> matcher) {
		
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
			                             	AtomicTaskMatcher<T,AT,TG> matcher) {
		
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
			                             AtomicTaskReducer<T, AT, TG, R> reducer) {
		
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
	public boolean ifAllExitTasksMatch(AtomicTaskMatcher<T, AT, TG> matcher) {
		return ifAllAtomicTasksMatch(getExitTasks(), matcher);
	}

	@Override
	public boolean ifSomeExitTaskMatches(AtomicTaskMatcher<T, AT, TG> matcher) {
		return ifSomeAtomicTaskMatches(getExitTasks(), matcher);
	}


	@Override
	public void forEachExitTask(AtomicTaskVisitor<T, AT, TG> visitor) {
		reduceExitTasks(null, (__, atomicTask) -> {
			
			visitor.visitAtomicTask(atomicTask);
			
			return null;
			
		}).value();
	}

	@Override
	public <R> Result<R> reduceExitTasks(R initialValue, AtomicTaskReducer<T, AT, TG, R> reducer) {
		ResultNtro<R> result = new ResultNtro<R>(initialValue);
		
		reduceAtomicTasks(getExitTasks(), result, reducer);
		
		return result;
	}


	@Override
	public boolean ifAllSubTasksMatch(TaskMatcher<T, AT, TG> matcher) {
		return ifAllNeighborTasksMatch(Direction.DOWN, matcher);
	}

	@Override
	public boolean ifSomeSubTaskMatches(TaskMatcher<T, AT, TG> matcher) {
		return ifSomeNeighborTaskMatches(Direction.DOWN, matcher);
	}


	@Override
	public boolean ifAllNextTasksMatch(TaskMatcher<T, AT, TG> matcher) {
		return ifAllNeighborTasksMatch(Direction.FORWARD, matcher);
	}

	@Override
	public boolean ifSomeNextTaskMatches(TaskMatcher<T, AT, TG> matcher) {
		return ifSomeNeighborTaskMatches(Direction.FORWARD, matcher);
	}


}
