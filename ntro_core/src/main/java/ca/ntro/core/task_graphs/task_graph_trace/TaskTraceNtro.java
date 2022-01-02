package ca.ntro.core.task_graphs.task_graph_trace;

import java.util.HashMap;
import java.util.Map;

import ca.ntro.core.identifyers.Id;
import ca.ntro.core.stream.Stream;
import ca.ntro.core.task_graphs.generic_task_graph.GenericTaskNtro;
import ca.ntro.core.values.ObjectMap;

public class TaskTraceNtro 

       implements TaskTrace, ObjectMap {

	private GenericTaskNtro<?,?> task;

	private Map<String, TaskTraceNtro> previousTasksTraces = new HashMap<>();
	private Map<String, AtomicTaskTraceNtro> parentEntryTasksTraces = new HashMap<>();

	private Map<String, AtomicTaskTraceNtro> atomicTasksTraces = new HashMap<>();
	private Map<String, TaskTraceNtro> subTasksTraces = new HashMap<>();

	private Map<String, TaskTraceNtro> nextTasksTraces = new HashMap<>();
	
	private boolean hasCurrent = false;
	private boolean isWaiting = false;
	private boolean hasNext = false;

	public GenericTaskNtro<?, ?> getTask() {
		return task;
	}

	public void setTask(GenericTaskNtro<?, ?> task) {
		this.task = task;
	}

	public Map<String, TaskTraceNtro> getPreviousTasksTraces() {
		return previousTasksTraces;
	}

	public void setPreviousTasksTraces(Map<String, TaskTraceNtro> previousTasksTraces) {
		this.previousTasksTraces = previousTasksTraces;
	}

	public Map<String, AtomicTaskTraceNtro> getParentEntryTasksTraces() {
		return parentEntryTasksTraces;
	}

	public void setParentEntryTasksTraces(Map<String, AtomicTaskTraceNtro> parentEntryTasksTraces) {
		this.parentEntryTasksTraces = parentEntryTasksTraces;
	}

	public Map<String, AtomicTaskTraceNtro> getAtomicTasksTraces() {
		return atomicTasksTraces;
	}

	public void setAtomicTasksTraces(Map<String, AtomicTaskTraceNtro> atomicTasksTraces) {
		this.atomicTasksTraces = atomicTasksTraces;
	}

	public Map<String, TaskTraceNtro> getSubTasksTraces() {
		return subTasksTraces;
	}

	public void setSubTasksTraces(Map<String, TaskTraceNtro> subTasksTraces) {
		this.subTasksTraces = subTasksTraces;
	}

	public boolean getHasCurrent() {
		return hasCurrent;
	}

	public void setHasCurrent(boolean hasCurrent) {
		this.hasCurrent = hasCurrent;
	}

	public boolean getHasNext() {
		return hasNext;
	}

	public void setHasNext(boolean hasNext) {
		this.hasNext = hasNext;
	}

	public boolean getIsWaiting() {
		return isWaiting;
	}

	public void setIsWaiting(boolean isWaiting) {
		this.isWaiting = isWaiting;
	}

	private Stream<TaskTraceNtro> previousTasksTraces(){
		return Stream.forMapValues(getPreviousTasksTraces());
	}

	private Stream<TaskTraceNtro> subTasksTraces(){
		return Stream.forMapValues(getSubTasksTraces());
	}

	private Stream<AtomicTaskTraceNtro> parentEntryTasksTraces(){
		return Stream.forMapValues(getParentEntryTasksTraces());
	}

	private Stream<AtomicTaskTraceNtro> atomicTasksTraces(){
		return Stream.forMapValues(getAtomicTasksTraces());
	}
	
	
	

	public TaskTraceNtro() {
	}

	public TaskTraceNtro(GenericTaskNtro<?, ?> task) {
		setTask(task);
	}

	private void recomputeState() {
		boolean oldHasCurrent = getHasCurrent();
		boolean oldIsWaiting = getIsWaiting();
		boolean oldHasNext = getHasNext();
		
		setHasCurrent(hasCurrentNow());

		setIsWaiting(isWaitingNow());
		
		setHasNext(hasNextNow());

		if(oldHasCurrent != getHasCurrent()
				|| oldIsWaiting != getIsWaiting()
				|| oldHasNext != getHasNext()) {

			onStateChanged();
		}
	}

	private boolean hasNextNow() {
		return ifAllPreconditionsHaveCurrent() && ifSomeResultHasNext();
	}

	private boolean isWaitingNow() {
		return ifSomePreconditionsIsWaiting() || ifSomeResultIsWaiting();
	}

	private boolean hasCurrentNow() {
		return ifAllPreconditionsHaveCurrent() && ifAllResultsHaveCurrent();
	}


	private boolean ifAllPreconditionsHaveCurrent() {
		return previousTasksTraces().ifAll(trace -> trace.hasCurrent())
				&& parentEntryTasksTraces().ifAll(trace -> trace.hasCurrent());
	}

	private boolean ifAllResultsHaveCurrent() {
		return atomicTasksTraces().ifAll(trace -> trace.hasCurrent())
				&& subTasksTraces().ifAll(trace -> trace.hasCurrent());
	}

	private boolean ifSomePreconditionsIsWaiting() {
		return previousTasksTraces().ifSome(trace -> trace.isWaiting())
				|| parentEntryTasksTraces().ifSome(trace -> trace.isWaiting());
	}

	private boolean ifSomeResultIsWaiting() {
		return atomicTasksTraces().ifSome(trace -> trace.isWaiting())
				|| subTasksTraces().ifSome(trace -> trace.isWaiting());
	}

	private boolean ifSomeResultHasNext() {
		return atomicTasksTraces().ifSome(trace -> trace.hasNext())
				|| subTasksTraces().ifSome(trace -> trace.hasNext());
	}

	private void onStateChanged() {
		// TODO: call every "forward" taskTraces so they can recompute their state
		throw new RuntimeException("TODO");
	}
	
	
	@Override
	public boolean hasCurrent() {
		return getHasCurrent();
	}
	
	@Override
	public ObjectMap current() {
		return (ObjectMap) this;
	}

	@Override
	public boolean isWaiting() {
		return getIsWaiting();
	}

	@Override
	public boolean hasNext() {
		return getHasNext();
	}

	@Override
	public void advanceToNext() {
		advanceResultsToNext();
		recomputeState();
	}

	private void advanceResultsToNext() {
		atomicTasksTraces().forEach(trace -> trace.advanceToNext());
		subTasksTraces().forEach(trace -> trace.advanceToNext());
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
		
		result = getFromTaskTraces(previousTasksTraces(), id);
		
		if(result == null) {
			result = getFromAtomicTasksTraces(getParentEntryTasksTraces(), id);
		}

		if(result == null) {
			result = getFromAtomicTasksTraces(getAtomicTasksTraces(), id);
		}

		if(result == null) {
			result = getFromTaskTraces(subTasksTraces(), id);
		}
		
		return result;
	}
	
	private Object getFromTaskTraces(Stream<TaskTraceNtro> traces, String id) {
		return traces.findFirst(trace -> trace.contains(id)).get(id);
	}

	private Object getFromAtomicTasksTraces(Map<String, AtomicTaskTraceNtro> traces, String id) {
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
		Stream<String> result = previousTasksTraces().reduceToStream((trace, visitor) -> {
			trace.ids().forEach(visitor);
		});

		result.append(Stream.forMapKeys(getParentEntryTasksTraces()));

		result.append(Stream.forMapKeys(getAtomicTasksTraces()));

		result.append(subTasksTraces().reduceToStream((trace, visitor) -> {
			trace.ids().forEach(visitor);
		}));

		return result;
	}

	@Override
	public Stream<Object> objects() {
		Stream<Object> result = previousTasksTraces().reduceToStream((trace, visitor) -> {
			trace.objects().forEach(visitor);
		});
		
		result.append(parentEntryTasksTraces().reduceToStream((atomicTrace, visitor) -> {
			if(atomicTrace.hasCurrent()) {
				visitor.visit(atomicTrace.current());
			}
		}));

		result.append(atomicTasksTraces().reduceToStream((atomicTrace, visitor) -> {
			if(atomicTrace.hasCurrent()) {
				visitor.visit(atomicTrace.current());
			}
		}));
		
		result.append(subTasksTraces().reduceToStream((trace, visitor) -> {
			trace.objects().forEach(visitor);
		}));
		
		return result;
	}

}
