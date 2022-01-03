package ca.ntro.core.task_graphs.task_graph_trace;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import ca.ntro.core.identifyers.Id;
import ca.ntro.core.stream.Stream;
import ca.ntro.core.task_graphs.generic_task_graph.GenericTask;
import ca.ntro.core.task_graphs.generic_task_graph.GenericTaskNtro;
import ca.ntro.core.values.ObjectMap;

public class TaskTraceNtro 

       implements TaskTrace, ObjectMap {

	private GenericTaskNtro<?,?> task;
	private TaskGraphTraceNtro parentTrace;
	private Map<String, AtomicTaskTraceNtro> beforeEntry = new HashMap<>();
	private Map<String, AtomicTaskTraceNtro> beforeSubTasks = new HashMap<>();
	private Map<String, AtomicTaskTraceNtro> beforeExit = new HashMap<>();
	private Map<String, AtomicTaskTraceNtro> done = new HashMap<>();

	public GenericTaskNtro<?, ?> getTask() {
		return task;
	}

	public void setTask(GenericTaskNtro<?, ?> task) {
		this.task = task;
	}

	public Map<String, AtomicTaskTraceNtro> getBeforeEntry() {
		return beforeEntry;
	}

	public void setBeforeEntry(Map<String, AtomicTaskTraceNtro> beforeEntry) {
		this.beforeEntry = beforeEntry;
	}

	public Map<String, AtomicTaskTraceNtro> getBeforeSubTasks() {
		return beforeSubTasks;
	}

	public void setBeforeSubTasks(Map<String, AtomicTaskTraceNtro> beforeSubTasks) {
		this.beforeSubTasks = beforeSubTasks;
	}

	public Map<String, AtomicTaskTraceNtro> getBeforeExit() {
		return beforeExit;
	}

	public void setBeforeExit(Map<String, AtomicTaskTraceNtro> beforeExit) {
		this.beforeExit = beforeExit;
	}

	public Map<String, AtomicTaskTraceNtro> getDone() {
		return done;
	}

	public void setDone(Map<String, AtomicTaskTraceNtro> done) {
		this.done = done;
	}

	public TaskGraphTraceNtro getParentTrace() {
		return parentTrace;
	}

	public void setParentTrace(TaskGraphTraceNtro parentTrace) {
		this.parentTrace = parentTrace;
	}

	private Stream<AtomicTaskTraceNtro> beforeEntry(){
		return Stream.forMapValues(getBeforeEntry());
	}

	private Stream<AtomicTaskTraceNtro> beforeSubTasks(){
		return Stream.forMapValues(getBeforeSubTasks());
	}

	private Stream<AtomicTaskTraceNtro> beforeExit(){
		return Stream.forMapValues(getBeforeExit());
	}

	private Stream<AtomicTaskTraceNtro> done(){
		return Stream.forMapValues(getDone());
	}
	

	public TaskTraceNtro() {
	}

	public TaskTraceNtro(GenericTaskNtro<?, ?> task, TaskGraphTraceNtro parentTrace) {
		setTask(task);
		setParentTrace(parentTrace);
		initialize();
	}
	

	private void initialize() {
		recursivelyBuildBeforeEntry(getTask(), new HashSet<>());
		recursivelyBuildBeforeExit(getTask(), new HashSet<>());
		buildBeforeSubTasks();
		buildDone();
	}

	private void buildBeforeSubTasks() {
		getTask().entryTasks().forEach(entryTask -> {
			getBeforeSubTasks().put(entryTask.id().toKey().toString(), (AtomicTaskTraceNtro) entryTask.newTrace(this));
		});
	}

	private void buildDone() {
		getTask().exitTasks().forEach(exitTask -> {
			getDone().put(exitTask.id().toKey().toString(), (AtomicTaskTraceNtro) exitTask.newTrace(this));
		});
	}
	
	private void recursivelyBuildBeforeEntry(GenericTask<?,?> cursor,
			                                 Set<String> visitedTasks) {

		cursor.previousTasks().forEach(previousTask -> {
			if(!visitedTasks.contains(previousTask.id().toKey().toString())) {
				visitedTasks.add(previousTask.id().toKey().toString());
				
				previousTask.atomicTasks().forEach(atomicTask -> {
					getBeforeEntry().put(atomicTask.id().toKey().toString(), (AtomicTaskTraceNtro) atomicTask.newTrace(this));
				});
				
				recursivelyBuildBeforeEntry(previousTask, visitedTasks);
			}
		});

		if(cursor.hasParentTask()) {
			if(!visitedTasks.contains(cursor.parentTask().id().toKey().toString())) {
				visitedTasks.add(cursor.parentTask().id().toKey().toString());
				
				cursor.parentTask().entryTasks().forEach(entryTask -> {
					getBeforeEntry().put(entryTask.id().toKey().toString(), (AtomicTaskTraceNtro) entryTask.newTrace(this));
				});

				recursivelyBuildBeforeEntry(cursor.parentTask(), visitedTasks);
			}
		}
	}

	private void recursivelyBuildBeforeExit(GenericTask<?,?> cursor,
			                                 Set<String> visitedTasks) {

		cursor.subTasks().forEach(subTask -> {
			if(!visitedTasks.contains(subTask.id().toKey().toString())) {
				visitedTasks.add(subTask.id().toKey().toString());
				
				subTask.atomicTasks().forEach(atomicTask -> {
					getBeforeExit().put(atomicTask.id().toKey().toString(), (AtomicTaskTraceNtro) atomicTask.newTrace(this));
				});

				recursivelyBuildBeforeExit(subTask, visitedTasks);
			}
		});
		
	}

	@Override
	public boolean hasCurrent() {
		return beforeEntry().ifAll(trace -> trace.hasCurrent())
				&& beforeSubTasks().ifAll(trace -> trace.hasCurrent())
				&& beforeExit().ifAll(trace -> trace.hasCurrent())
				&& done().ifAll(trace -> trace.hasCurrent());
	}
	
	@Override
	public ObjectMap current() {
		return (ObjectMap) this;
	}

	@Override
	public boolean isWaiting() {
		return beforeEntry().ifSome(trace -> trace.isWaiting())
				|| beforeSubTasks().ifSome(trace -> trace.isWaiting())
				|| beforeExit().ifSome(trace -> trace.isWaiting())
				|| done().ifSome(trace -> trace.isWaiting());
	}

	@Override
	public boolean hasNext() {
		return beforeEntry().ifSome(trace -> trace.hasNext())
				|| beforeSubTasks().ifSome(trace -> trace.hasNext())
				|| beforeExit().ifSome(trace -> trace.hasNext())
				|| done().ifSome(trace -> trace.hasNext());
	}

	@Override
	public void advanceToNext() {
		beforeEntry().forEach(trace -> trace.advanceToNext());
		beforeSubTasks().forEach(trace -> trace.advanceToNext());
		beforeExit().forEach(trace -> trace.advanceToNext());
		done().forEach(trace -> trace.advanceToNext());
	}

	@Override
	public boolean contains(Id id) {
		return contains(id.toKey().toString());
	}

	@Override
	public boolean contains(String id) {
		return get(id) != null;
	}

	@Override
	public <O> O get(Class<O> _class, Id id) {
		return get(_class, id.toKey().toString());
	}

	@Override
	public <O> O get(Class<O> _class, String id) {
		return (O) get(id);
	}

	@Override
	public Object get(Id id) {
		return get(id.toKey().toString());
	}

	@Override
	public Object get(String id) {
		Object result = null;
		
		result = get(id, getBeforeEntry());
		
		if(result == null) {
			result = get(id, getBeforeSubTasks());
		}

		if(result == null) {
			result = get(id, getBeforeExit());
		}

		if(result == null) {
			result = get(id, getDone());
		}
		
		return result;
	}
	
	private Object get(String id, Map<String, AtomicTaskTraceNtro> traces) {
		Object result = null;

		AtomicTaskTraceNtro trace = traces.get(id);
		
		if(trace != null 
				&& trace.hasCurrent()) {
			
			result = trace.current();
		}
		
		return result;
	}
	
	@Override
	public Stream<String> ids() {
		throw new RuntimeException("TODO");
	}

	@Override
	public Stream<Object> objects() {
		throw new RuntimeException("TODO");
	}
	
	
	
	@Override
	public boolean isBlocked() {
		return !readyForEntryTasks();
	}

	private boolean readyForEntryTasks() {
		return beforeEntry().ifAll(trace -> trace.hasCurrent());
	}

	private boolean entryTasksDone() {
		return beforeSubTasks().ifAll(trace -> trace.hasCurrent());
	}

	@Override
	public boolean isInProgress() {
		return !isBlocked() && !isDone();
	}

	@Override
	public boolean isDone() {
		return hasCurrent()
				&& !hasNext()
				&& !isWaiting();
	}

	public void notifyNewResult() {
		
	}

}
