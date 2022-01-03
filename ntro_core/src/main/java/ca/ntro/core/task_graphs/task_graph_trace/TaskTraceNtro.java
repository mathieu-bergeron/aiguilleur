package ca.ntro.core.task_graphs.task_graph_trace;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import ca.ntro.core.identifyers.Id;
import ca.ntro.core.stream.Stream;
import ca.ntro.core.task_graphs.generic_task_graph.AtomicTaskId;
import ca.ntro.core.task_graphs.generic_task_graph.GenericTask;
import ca.ntro.core.task_graphs.generic_task_graph.GenericTaskNtro;
import ca.ntro.core.values.ObjectMap;

public class TaskTraceNtro 

       implements TaskTrace, ObjectMap {

	private GenericTaskNtro<?,?> task;
	private TaskGraphTraceNtro parentTrace;
	private Map<String, AtomicTaskTraceNtro> preconditions = new HashMap<>();
	private Map<String, AtomicTaskTraceNtro> entryTraces = new HashMap<>();
	private Map<String, AtomicTaskTraceNtro> subTraces = new HashMap<>();
	private Map<String, AtomicTaskTraceNtro> exitTraces = new HashMap<>();
	
	private TaskState state = TaskState.BLOCKED;

	public GenericTaskNtro<?, ?> getTask() {
		return task;
	}

	public void setTask(GenericTaskNtro<?, ?> task) {
		this.task = task;
	}

	public Map<String, AtomicTaskTraceNtro> getPreconditions() {
		return preconditions;
	}

	public void setPreconditions(Map<String, AtomicTaskTraceNtro> preconditions) {
		this.preconditions = preconditions;
	}

	public Map<String, AtomicTaskTraceNtro> getEntryTraces() {
		return entryTraces;
	}

	public void setEntryTraces(Map<String, AtomicTaskTraceNtro> entryTraces) {
		this.entryTraces = entryTraces;
	}

	public Map<String, AtomicTaskTraceNtro> getSubTraces() {
		return subTraces;
	}

	public void setSubTraces(Map<String, AtomicTaskTraceNtro> subTraces) {
		this.subTraces = subTraces;
	}

	public Map<String, AtomicTaskTraceNtro> getExitTraces() {
		return exitTraces;
	}

	public void setExitTraces(Map<String, AtomicTaskTraceNtro> exitTraces) {
		this.exitTraces = exitTraces;
	}

	public TaskGraphTraceNtro getParentTrace() {
		return parentTrace;
	}

	public void setParentTrace(TaskGraphTraceNtro parentTrace) {
		this.parentTrace = parentTrace;
	}

	public TaskState getState() {
		return state;
	}

	public void setState(TaskState state) {
		this.state = state;
	}

	private Stream<AtomicTaskTraceNtro> preconditions(){
		return Stream.forMapValues(getPreconditions());
	}

	private Stream<AtomicTaskTraceNtro> entryTraces(){
		return Stream.forMapValues(getEntryTraces());
	}

	private Stream<AtomicTaskTraceNtro> subTraces(){
		return Stream.forMapValues(getSubTraces());
	}

	private Stream<AtomicTaskTraceNtro> exitTraces(){
		return Stream.forMapValues(getExitTraces());
	}
	

	public TaskTraceNtro() {
	}

	public TaskTraceNtro(GenericTaskNtro<?, ?> task, TaskGraphTraceNtro parentTrace) {
		setTask(task);
		setParentTrace(parentTrace);
		initialize();
	}
	

	private void initialize() {
		recursivelyAddPreconditions(getTask(), new HashSet<>());
		recursivelyAddSubTraces(getTask(), new HashSet<>());
		addEntryTraces();
		addExitTraces();
	}

	private void addEntryTraces() {
		getTask().entryTasks().forEach(entryTask -> {
			getEntryTraces().put(entryTask.id().toKey().toString(), (AtomicTaskTraceNtro) entryTask.newTrace(this));
		});
	}

	private void addExitTraces() {
		getTask().exitTasks().forEach(exitTask -> {
			getExitTraces().put(exitTask.id().toKey().toString(), (AtomicTaskTraceNtro) exitTask.newTrace(this));
		});
	}
	
	private void recursivelyAddPreconditions(GenericTask<?,?> cursor,
			                                 Set<String> visitedTasks) {

		cursor.previousTasks().forEach(previousTask -> {
			if(!visitedTasks.contains(previousTask.id().toKey().toString())) {
				visitedTasks.add(previousTask.id().toKey().toString());
				
				previousTask.atomicTasks().forEach(atomicTask -> {
					getPreconditions().put(atomicTask.id().toKey().toString(), (AtomicTaskTraceNtro) atomicTask.newTrace(this));
				});
				
				recursivelyAddPreconditions(previousTask, visitedTasks);
			}
		});

		if(cursor.hasParentTask()) {
			if(!visitedTasks.contains(cursor.parentTask().id().toKey().toString())) {
				visitedTasks.add(cursor.parentTask().id().toKey().toString());
				
				cursor.parentTask().entryTasks().forEach(entryTask -> {
					getPreconditions().put(entryTask.id().toKey().toString(), (AtomicTaskTraceNtro) entryTask.newTrace(this));
				});

				recursivelyAddPreconditions(cursor.parentTask(), visitedTasks);
			}
		}
	}

	private void recursivelyAddSubTraces(GenericTask<?,?> cursor,
			                                 Set<String> visitedTasks) {

		cursor.subTasks().forEach(subTask -> {
			if(!visitedTasks.contains(subTask.id().toKey().toString())) {
				visitedTasks.add(subTask.id().toKey().toString());
				
				subTask.atomicTasks().forEach(atomicTask -> {
					getSubTraces().put(atomicTask.id().toKey().toString(), (AtomicTaskTraceNtro) atomicTask.newTrace(this));
				});

				recursivelyAddSubTraces(subTask, visitedTasks);
			}
		});
		
	}

	@Override
	public boolean hasCurrent() {
		return preconditions().ifAll(trace -> trace.hasCurrent())
				&& entryTraces().ifAll(trace -> trace.hasCurrent())
				&& subTraces().ifAll(trace -> trace.hasCurrent())
				&& exitTraces().ifAll(trace -> trace.hasCurrent());
	}
	
	@Override
	public ObjectMap current() {
		return (ObjectMap) this;
	}

	@Override
	public boolean isWaiting() {
		return preconditions().ifSome(trace -> trace.isWaiting())
				|| entryTraces().ifSome(trace -> trace.isWaiting())
				|| subTraces().ifSome(trace -> trace.isWaiting())
				|| exitTraces().ifSome(trace -> trace.isWaiting());
	}

	@Override
	public boolean hasNext() {
		return preconditions().ifSome(trace -> trace.hasNext())
				|| entryTraces().ifSome(trace -> trace.hasNext())
				|| subTraces().ifSome(trace -> trace.hasNext())
				|| exitTraces().ifSome(trace -> trace.hasNext());
	}

	@Override
	public void advanceToNext() {
		preconditions().forEach(trace -> trace.advanceToNext());
		entryTraces().forEach(trace -> trace.advanceToNext());
		subTraces().forEach(trace -> trace.advanceToNext());
		exitTraces().forEach(trace -> trace.advanceToNext());
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
		
		result = get(id, getPreconditions());
		
		if(result == null) {
			result = get(id, getEntryTraces());
		}

		if(result == null) {
			result = get(id, getSubTraces());
		}

		if(result == null) {
			result = get(id, getExitTraces());
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
		return getState() == TaskState.BLOCKED;
	}

	@Override
	public boolean isInProgress() {
		return getState() == TaskState.EXECUTING_ENTRY_TASKS
				|| getState() == TaskState.EXECUTING_SUB_TASKS
				|| getState() == TaskState.EXECUTING_EXIT_TASKS;
	}

	@Override
	public boolean isDone() {
		return getState() == TaskState.DONE;
	}

	public void notifyNewResult(AtomicTaskId id, Object value) {
		getParentTrace().notifyNewResult(id, value);
	}

	public void notifyClearResults() {
		getParentTrace().notifyClearResults();
		
	}
	
	public boolean recomputeState() {
		boolean stateChanged = false;
		TaskState oldState = getState();

		setState(currentState());
		
		if(oldState != getState()) {
			stateChanged = true;
		}
		
		return stateChanged;
	}

	public TaskState currentState() {
		TaskState currentState = TaskState.BLOCKED;
		
		if(hasCurrent()
				&& !hasNext()
				&& !isWaiting()) {
			
			currentState = TaskState.DONE;

		} else if(hasCurrent() && hasNext() && !isWaiting()) {
			
			currentState = TaskState.HAS_NEXT;

		}else if((subTraces().ifAll(subTrace -> subTrace.hasCurrent()
				&& exitTraces().ifSome(exitTrace -> !exitTrace.hasCurrent() || exitTrace.isWaiting())))) {
			
			currentState = TaskState.EXECUTING_EXIT_TASKS;

		}else if(entryTraces().ifAll(entryTrace -> entryTrace.hasCurrent()
				&& subTraces().ifSome(subTrace -> !subTrace.hasCurrent() || subTrace.isWaiting()))) {
			
			currentState = TaskState.EXECUTING_SUB_TASKS;

		}else if(preconditions().ifAll(precondition -> precondition.hasCurrent()
				&& entryTraces().ifSome(entryTrace -> !entryTrace.hasCurrent() || entryTrace.isWaiting()))) {
			
			currentState = TaskState.EXECUTING_ENTRY_TASKS;
		}

		
		
		return currentState;
	}


	public void silentlyAddResult(AtomicTaskId id, Object value) {
		boolean added = false;
		
		added = silentlyAddResultTo(id, value, getPreconditions());
		
		if(!added) {
			added = silentlyAddResultTo(id, value, getEntryTraces());
		}

		if(!added) {
			added = silentlyAddResultTo(id, value, getSubTraces());
		}

		if(!added) {
			added = silentlyAddResultTo(id, value, getExitTraces());
		}
	}
	
	private boolean silentlyAddResultTo(AtomicTaskId id, Object value, Map<String, AtomicTaskTraceNtro> traces) {
		boolean added = false;
		
		if(traces.containsKey(id.toKey().toString())) {
			traces.get(id.toKey().toString()).silentlyAddResult(value);
		}
		
		
		return added;
	}

	
	
	

}
