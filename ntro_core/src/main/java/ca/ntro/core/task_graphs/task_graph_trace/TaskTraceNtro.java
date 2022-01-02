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
	private Map<String, AtomicTaskTraceNtro> atomicTasksTraces = new HashMap<>();

	public GenericTaskNtro<?, ?> getTask() {
		return task;
	}

	public void setTask(GenericTaskNtro<?, ?> task) {
		this.task = task;
	}

	public Map<String, AtomicTaskTraceNtro> getAtomicTasksTraces() {
		return atomicTasksTraces;
	}

	public void setAtomicTasksTraces(Map<String, AtomicTaskTraceNtro> atomicTasksTraces) {
		this.atomicTasksTraces = atomicTasksTraces;
	}
	
	private Stream<AtomicTaskTraceNtro> atomicTasksTraces(){
		return Stream.forMapValues(getAtomicTasksTraces());
	}
	
	
	

	public TaskTraceNtro() {
	}

	public TaskTraceNtro(GenericTaskNtro<?, ?> task) {
		setTask(task);
		initialize();
	}
	
	private void initialize() {
		recursivelyAddPreconditions(getTask(), new HashSet<>());
		addResultsTasks();
	}

	private void addResultsTasks() {
		getTask().atomicTasks().forEach(atomicTask -> {
			getAtomicTasksTraces().put(atomicTask.id().toKey().toString(), (AtomicTaskTraceNtro) atomicTask.newTrace());
		});
	}
	
	private void recursivelyAddPreconditions(GenericTask<?,?> cursor,
			                                 Set<String> visitedTasks) {

		cursor.previousTasks().forEach(previousTask -> {
			if(!visitedTasks.contains(previousTask.id().toKey().toString())) {
				visitedTasks.add(previousTask.id().toKey().toString());
				
				previousTask.atomicTasks().forEach(atomicTask -> {
					getAtomicTasksTraces().put(atomicTask.id().toKey().toString(), (AtomicTaskTraceNtro) atomicTask.newTrace());
				});
				
				recursivelyAddPreconditions(previousTask, visitedTasks);
			}
		});

		cursor.subTasks().forEach(subTask -> {
			if(!visitedTasks.contains(subTask.id().toKey().toString())) {
				visitedTasks.add(subTask.id().toKey().toString());
				
				subTask.atomicTasks().forEach(atomicTask -> {
					getAtomicTasksTraces().put(atomicTask.id().toKey().toString(), (AtomicTaskTraceNtro) atomicTask.newTrace());
				});

				recursivelyAddPreconditions(subTask, visitedTasks);
			}
		});
		
		if(cursor.hasParentTask()) {
			if(!visitedTasks.contains(cursor.parentTask().id().toKey().toString())) {
				visitedTasks.add(cursor.parentTask().id().toKey().toString());
				
				cursor.parentTask().entryTasks().forEach(entryTask -> {
					getAtomicTasksTraces().put(entryTask.id().toKey().toString(), (AtomicTaskTraceNtro) entryTask.newTrace());
				});

				recursivelyAddPreconditions(cursor.parentTask(), visitedTasks);
			}
		}
	}

	@Override
	public boolean hasCurrent() {
		return atomicTasksTraces().ifAll(trace -> trace.hasCurrent());
	}
	
	@Override
	public ObjectMap current() {
		return (ObjectMap) this;
	}

	@Override
	public boolean isWaiting() {
		return atomicTasksTraces().ifSome(trace -> trace.isWaiting());
	}

	@Override
	public boolean hasNext() {
		return atomicTasksTraces().ifSome(trace -> trace.hasNext());
	}

	@Override
	public void advanceToNext() {
		atomicTasksTraces().forEach(trace -> trace.advanceToNext());
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
		
		AtomicTaskTraceNtro trace = getAtomicTasksTraces().get(id);
		
		if(trace != null 
				&& trace.hasCurrent()) {
			
			result = trace.current();
		}
		
		return result;
	}
	
	@Override
	public Stream<String> ids() {
		return Stream.forMapKeys(getAtomicTasksTraces());
	}

	@Override
	public Stream<Object> objects() {
		return atomicTasksTraces().reduceToStream((trace, visitor) -> {
			if(trace.hasCurrent()) {
				visitor.visit(trace.current());
			}
		});
	}

}
