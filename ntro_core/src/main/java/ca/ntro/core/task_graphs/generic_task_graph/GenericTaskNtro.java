package ca.ntro.core.task_graphs.generic_task_graph;

import java.util.HashMap;
import java.util.Map;

import ca.ntro.core.graphs.common.Direction;
import ca.ntro.core.graphs.generics.graph.SearchStrategy;
import ca.ntro.core.graphs.generics.graph.VisitedNode;
import ca.ntro.core.graphs.hierarchical_dag.HierarchicalDagNodeBuilder;
import ca.ntro.core.graphs.hierarchical_dag.HierarchicalDagSearchOptions;
import ca.ntro.core.identifyers.Key;
import ca.ntro.core.stream.Stream;
import ca.ntro.core.stream.StreamNtro;
import ca.ntro.core.stream.Visitor;
import ca.ntro.core.task_graphs.generic_task_graph_trace.ResultsAccumulatorNtro;
import ca.ntro.core.task_graphs.generic_task_graph_trace.TaskTrace;

public abstract class GenericTaskNtro<T  extends GenericTask<T,AT>, 
                               AT extends GenericAtomicTask<T,AT>>

	   implements     GenericTask<T,AT> {
	
	private TaskGraphNtro<T,AT> graph;
	private HierarchicalDagNodeBuilder<TaskGraphNode<T,AT>,TaskGraphEdge<T,AT>> nodeBuilder;

	private Map<String, AT> entryTasks = new HashMap<>();
	private Map<String, AT> exitTasks = new HashMap<>();
	
	private ResultsAccumulatorNtro resultsAccumulator = new ResultsAccumulatorNtro();
	
	public TaskGraphNtro<T,AT> getGraph() {
		return graph;
	}

	public void setGraph(TaskGraphNtro<T,AT> graph) {
		this.graph = graph;
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

	public HierarchicalDagNodeBuilder<TaskGraphNode<T, AT>, TaskGraphEdge<T, AT>> getNodeBuilder() {
		return nodeBuilder;
	}

	public void setNodeBuilder(HierarchicalDagNodeBuilder<TaskGraphNode<T, AT>, TaskGraphEdge<T, AT>> node) {
		this.nodeBuilder = node;
	}

	public ResultsAccumulatorNtro getResultsAccumulator() {
		return resultsAccumulator;
	}

	public void setResultsAccumulator(ResultsAccumulatorNtro resultsAccumulator) {
		this.resultsAccumulator = resultsAccumulator;
	}
	
	
	

	public GenericTaskNtro(){
		super();
	}
	
	
	
	
	

	@Override
	public TaskId id() {
		return (TaskId) getNodeBuilder().node().id();
	}

	@Override
	public TaskGraph<T,AT> parentGraph() {
		return getGraph();
	}

	@Override
	public T parentTask() {
		T parentTask = null;

		if(getNodeBuilder().node().hasParent()) {
			parentTask = getNodeBuilder().node().parent().task();
		}
		
		return parentTask;
	}

	@Override
	public boolean hasParentTask() {
		return getNodeBuilder().node().hasParent();
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
				throw new RuntimeException("FIXME: task state is in Trace now");
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
		throw new RuntimeException("FIXME: task state is in GraphTrace now");
		//return entryTasks().ifAll(entryTask -> entryTask.isDone());
	}

	protected boolean areExitTasksDone() {
		throw new RuntimeException("FIXME: task state is in GraphTrace now");
		//return exitTasks().ifAll(exitTask -> exitTask.isDone());
	}

	protected boolean areSubTasksDone() {
		return subTasks().ifAll(subTask -> subTask.isDone());
	}

	@Override
	public AT findEntryTask(String id) {
		return findEntryTask(AtomicTaskId.fromKey(new Key(id)));
	}

	@Override
	public AT findEntryTask(AtomicTaskId id) {
		return getEntryTasks().get(id.toKey().toString());
	}

	@Override
	public AT findExitTask(String id) {
		return findExitTask(AtomicTaskId.fromKey(new Key(id)));
	}

	@Override
	public AT findExitTask(AtomicTaskId id) {
		return getExitTasks().get(id.toKey().toString());
	}

	@Override
	public AT findAtomicTask(String id) {
		return findAtomicTask(AtomicTaskId.fromKey(new Key(id)));
	}

	@Override
	public AT findAtomicTask(AtomicTaskId id) {
		AT result = findEntryTask(id);
		
		if(result == null) {
			result = findExitTask(id);
		}
		
		return result;
	}

	@Override
	public T addSubTask(String id) {
		return addSubTask(new TaskIdNtro(id));
	}

	@Override
	public T addSubTask(TaskId id) {
		T subTask = getGraph().addTask(id);

		addSubTask(subTask);

		return subTask;
	}

	@Override
	public void addSubTask(T subTask) {
		getNodeBuilder().addSubNode(toNode(subTask));
		
	}
	
	@SuppressWarnings("unchecked")
	private GenericTaskNtro<T,AT> toTaskNtro(T task){
		return (GenericTaskNtro<T,AT>) task;
	}

	@SuppressWarnings("unchecked")
	private HierarchicalDagNodeBuilder<TaskGraphNode<T,AT>,TaskGraphEdge<T,AT>> toNode(T task) {
		return ((GenericTaskNtro<T,AT>) task).getNodeBuilder();
	}

	@Override
	public T addPreviousTask(String id) {
		return addPreviousTask(new TaskIdNtro(id));
	}

	@Override
	public T addPreviousTask(TaskId id) {
		T previousTask = getGraph().addTask(id);
		
		addPreviousTask(previousTask);

		return previousTask;
	}

	@Override
	public void addPreviousTask(T previousTask) {
		toNode(previousTask).addEdge("", getNodeBuilder());
	}
	
	
	@Override
	public T addNextTask(String id) {
		return addNextTask(new TaskIdNtro(id));
	}

	@Override
	public T addNextTask(TaskId id) {
		T nextTask = getGraph().addTask(id);
		
		addNextTask(nextTask);

		return nextTask;
	}

	@Override
	public void addNextTask(T nextTask) {
		getNodeBuilder().addEdge("", toNode(nextTask));
	}

	@SuppressWarnings("unchecked")
	private AT toAtomicTask(GenericAtomicTaskNtro<T,AT> atomicTaskNtro) {
		return (AT) atomicTaskNtro;
	}

	@Override
	public AT addEntryTask(String id) {
		return addEntryTask(AtomicTaskId.fromKey(id));
	}

	@Override
	public AT addEntryTask(AtomicTaskId id) {
		return addEntryTask(id, getGraph().getDefaultAtomicTaskClass());
	}

	@Override
	public AT addEntryTask(String id, Class<? extends AT> atomicTaskClass) {
		return addEntryTask(AtomicTaskId.fromKey(id), atomicTaskClass);
	}

	@Override
	public AT addEntryTask(AtomicTaskId id, Class<? extends AT> atomicTaskClass) {
		return addAtomicTaskTo(getEntryTasks(), id, atomicTaskClass);
	}

	@Override
	public void addEntryTask(AT entryTask) {
		addAtomicTaskTo(getEntryTasks(), entryTask);
	}

	@Override
	public AT addExitTask(String id) {
		return addExitTask(AtomicTaskId.fromKey(id));
	}

	@Override
	public AT addExitTask(AtomicTaskId id) {
		return addAtomicTaskTo(getExitTasks(), id, getGraph().getDefaultAtomicTaskClass());
	}

	@Override
	public AT addExitTask(String id, Class<? extends AT> atomicTaskClass) {
		return addExitTask(AtomicTaskId.fromKey(id), atomicTaskClass);
	}

	@Override
	public AT addExitTask(AtomicTaskId id, Class<? extends AT> atomicTaskClass) {
		return addAtomicTaskTo(getExitTasks(), id, atomicTaskClass);
	}

	@Override
	public void addExitTask(AT exitTask) {
		addAtomicTaskTo(getExitTasks(), exitTask);
	}

	protected AT addAtomicTaskTo(Map<String,AT> atomicTasks, AtomicTaskId id, Class<? extends AT> atomicTaskClass) {
		GenericAtomicTaskNtro<T,AT> atmoicTaskNtro = getGraph().newAtomicTaskInstance(atomicTaskClass);
		atmoicTaskNtro.setId(id);
		atmoicTaskNtro.setParentTask(this);
		
		AT atomicTask = toAtomicTask(atmoicTaskNtro);
		
		addAtomicTaskTo(atomicTasks, atomicTask);

		return atomicTask;
	}

	protected void addAtomicTaskTo(Map<String, AT> atomicTasks, AT atomicTask) {
		atomicTasks.put(atomicTask.id().toKey().toString(), atomicTask);
	}

	protected TaskGraphSearchOptionsBuilderNtro neighborSearchOptions(Direction direction) {

		TaskGraphSearchOptionsBuilderNtro neighborOptions = new TaskGraphSearchOptionsBuilderNtro();

		neighborOptions.internal().setSearchStrategy(SearchStrategy.DEPTH_FIRST_SEARCH);
		neighborOptions.internal().setDirections(new Direction[] {direction});
		neighborOptions.internal().setMaxDistance(1);
		neighborOptions.internal().setSortEdgesByName(false);

		return neighborOptions;
	}

	protected TaskGraphSearchOptions defaultSearchOptions() {
		return new TaskGraphSearchOptionsBuilderNtro();
	}

	protected Stream<AT> atomicTasks(Map<String, AT> atomicTasks){
		return new StreamNtro<AT>() {
			@Override
			public void _forEach(Visitor<AT> visitor) throws Throwable {
				for(AT atomicTask : atomicTasks.values()) {
					visitor.visit(atomicTask);
				}
			}
		};
	}

	protected Stream<AT> atomicTasks(){
		return atomicTasks(getEntryTasks()).append(atomicTasks(getExitTasks()));
	}

	@Override
	public Stream<T> previousTasks() {
		return reachableTasks(neighborSearchOptions(Direction.BACKWARD));
	}

	@Override
	public Stream<AT> entryTasks() {
		return atomicTasks(getEntryTasks());
	}

	@Override
	public Stream<T> subTasks() {
		return reachableTasks(neighborSearchOptions(Direction.DOWN));
	}

	@Override
	public Stream<AT> exitTasks() {
		return atomicTasks(getExitTasks());
	}

	@Override
	public Stream<T> nextTasks() {
		return reachableTasks(neighborSearchOptions(Direction.FORWARD));
	}

	@Override
	public Stream<T> reachableTasks() {
		return reachableTasks(defaultSearchOptions());
	}

	@Override
	public Stream<T> reachableTasks(TaskGraphSearchOptions options) {
		// JSweet: we need to explicitly declare intermediate streams
		Stream<VisitedNode<TaskGraphNode<T,AT>, 
						   TaskGraphEdge<T,AT>,
						   HierarchicalDagSearchOptions>> visitedNodes = getNodeBuilder().node().reachableNodes(options);
		
		return visitedNodes.map(visitedNode -> visitedNode.node().task());
	}
	
	
	@Override
	public TaskTrace newTrace() {
		/*  TODO: create a TaskResultsIterator
		 * 
		 *        that refers to a TaskResultsIterator for each previousTask (and for the parentTask)
		 *        
		 *        AND that fetches from AtomicTask
		 */
		throw new RuntimeException("TODO");
	}
}
