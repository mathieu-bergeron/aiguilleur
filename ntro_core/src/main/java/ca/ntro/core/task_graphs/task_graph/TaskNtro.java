package ca.ntro.core.task_graphs.task_graph;

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

public abstract class TaskNtro<T  extends Task<T,AT>, 
                               AT extends AtomicTask<T,AT>>

	   implements     Task<T,AT> {
	
	private TaskId id;
	private TaskGraphNtro<T,AT> graph;
	private HierarchicalDagNodeBuilder<TaskGraphNode<T,AT>,TaskGraphEdge<T,AT>> nodeBuilder;

	private Map<String, AT> entryTasks = new HashMap<>();
	private Map<String, AT> exitTasks = new HashMap<>();
	
	public TaskId getId() {
		return id;
	}

	public void setId(TaskId id) {
		this.id = id;
	}

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

	public TaskNtro(){
		super();
	}

	public TaskNtro(String id){
		setId(new TaskIdNtro(id));
	}

	public TaskNtro(TaskId id){
		setId(id);
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
		return entryTasks().ifAll(entryTask -> entryTask.isDone());
	}

	protected boolean areExitTasksDone() {
		return exitTasks().ifAll(exitTask -> exitTask.isDone());
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
	private HierarchicalDagNodeBuilder<TaskGraphNode<T,AT>,TaskGraphEdge<T,AT>> toNode(T task) {
		return ((TaskNtro<T,AT>) task).getNodeBuilder();
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
	private AT toAtomicTask(AtomicTaskNtro<T,AT> atomicTaskNtro) {
		return (AT) atomicTaskNtro;
	}

	@Override
	public AT addEntryTask(String id) {
		return addEntryTask(AtomicTaskId.fromKey(new Key(id)));
	}

	@Override
	public AT addEntryTask(AtomicTaskId id) {
		AtomicTaskNtro<T,AT> entryTaskNtro = getGraph().newAtomicTaskInstance();
		entryTaskNtro.setId(id);
		
		AT entryTask = toAtomicTask(entryTaskNtro);
		
		addEntryTask(entryTask);

		return entryTask;
	}

	@Override
	public void addEntryTask(AT entryTask) {
		getEntryTasks().put(entryTask.id().toKey().toString(), entryTask);
	}

	@Override
	public AT addExitTask(String id) {
		return addExitTask(AtomicTaskId.fromKey(new Key(id)));
	}

	@Override
	public AT addExitTask(AtomicTaskId id) {
		AtomicTaskNtro<T,AT> exitTaskNtro = getGraph().newAtomicTaskInstance();
		exitTaskNtro.setId(id);
		
		AT exitTask = toAtomicTask(exitTaskNtro);
		
		addExitTask(exitTask);

		return exitTask;
	}

	@Override
	public void addExitTask(AT exitTask) {
		getExitTasks().put(exitTask.id().toKey().toString(), exitTask);
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
}
