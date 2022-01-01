package ca.ntro.core.task_graphs.generic_task_graph_trace;

import java.util.HashSet;
import java.util.Set;

import ca.ntro.core.stream.Stream;
import ca.ntro.core.task_graphs.generic_task_graph.GenericTaskNtro;
import ca.ntro.core.values.ObjectMap;
import ca.ntro.core.values.ObjectMapNtro;

public class     GenericTaskTraceNtro 

       extends   GenericTraceNtro<ObjectMap>

       implements GenericTaskTrace {

	private GenericTaskNtro<?,?> task;

	private Set<GenericTaskTraceNtro> previousTasksTraces = new HashSet<>();

	private Set<GenericAtomicTaskTraceNtro> parentEntryTasksTraces = new HashSet<>();

	private Set<GenericAtomicTaskTraceNtro> entryTasksTraces = new HashSet<>();

	private Set<GenericTaskTraceNtro> subTasksTraces = new HashSet<>();

	private Set<GenericAtomicTaskTraceNtro> exitTasksTraces = new HashSet<>();

	public GenericTaskNtro<?, ?> getTask() {
		return task;
	}

	public void setTask(GenericTaskNtro<?, ?> task) {
		this.task = task;
	}

	
	public void initialize() {
		task.previousTasks().forEach(previousTask -> {
			
			GenericTaskTraceNtro trace = (GenericTaskTraceNtro) previousTask.newTrace();
			previousTasksTraces.add(trace);
		});
		
		if(task.hasParentTask()) {
			task.parentTask().entryTasks().forEach(parentEntryTask -> {
				parentEntryTasksTraces.add((GenericAtomicTaskTraceNtro) parentEntryTask.newTrace());
			});
		}
	}
	
	protected Stream<GenericTaskTraceNtro> previousTraces(){
		return Stream.fromSet(previousTasksTraces);
	}

	protected Stream<GenericAtomicTaskTraceNtro> parentTraces(){
		return Stream.fromSet(parentEntryTasksTraces);
	}

	protected Stream<GenericAtomicTaskTraceNtro> entryTraces(){
		return Stream.fromSet(entryTasksTraces);
	}

	protected Stream<GenericTaskTraceNtro> subTraces(){
		return Stream.fromSet(subTasksTraces);
	}

	protected Stream<GenericAtomicTaskTraceNtro> exitTraces(){
		return Stream.fromSet(exitTasksTraces);
	}

	
	@Override
	public boolean hasCurrent() {
		return previousTraces().ifAll(trace -> trace.hasCurrent())
				&& parentTraces().ifAll(trace -> trace.hasCurrent())
				&& entryTraces().ifAll(trace -> trace.hasCurrent())
				&& subTraces().ifAll(trace -> trace.hasCurrent())
				&& exitTraces().ifAll(trace -> trace.hasCurrent());
	}

	@Override
	public ObjectMap current() {
		ObjectMapNtro current = new ObjectMapNtro();

		previousTraces().forEach(trace -> {
			if(trace.hasCurrent()) {
				current.addAll(trace.current());
			}
		});
		
		parentTraces().forEach(trace -> {
			if(trace.hasCurrent()) {
				current.registerObject(trace.task().id(), trace.current());
			}

		});
		
		throw new RuntimeException("TODO: complete this");
		//return current;
	}

	@Override
	public void notifyWaitingForResult() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyCurrentResultWasUsed() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyCurrentResultCouldNotBeUsed() {
		// TODO Auto-generated method stub
		
	}
	
}
