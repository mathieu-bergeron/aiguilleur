package ca.ntro.core.task_graphs.generic_task_graph_trace;

import java.util.HashSet;
import java.util.Set;

import ca.ntro.core.stream.Stream;
import ca.ntro.core.task_graphs.generic_task_graph.GenericTaskNtro;
import ca.ntro.core.values.ObjectMap;
import ca.ntro.core.values.ObjectMapNtro;

public class     TaskTraceNtro 

       extends   GenericTraceNtro<ObjectMap>

       implements TaskTrace {

	private GenericTaskNtro<?,?> task;

	private Set<TaskTraceNtro> previousTasksTraces = new HashSet<>();

	private Set<AtomicTaskTraceNtro> parentEntryTasksTraces = new HashSet<>();

	private Set<AtomicTaskTraceNtro> entryTasksTraces = new HashSet<>();

	private Set<TaskTraceNtro> subTasksTraces = new HashSet<>();

	private Set<AtomicTaskTraceNtro> exitTasksTraces = new HashSet<>();

	public GenericTaskNtro<?, ?> getTask() {
		return task;
	}

	public void setTask(GenericTaskNtro<?, ?> task) {
		this.task = task;
	}

	
	public void initialize() {
		task.previousTasks().forEach(previousTask -> {
			
			TaskTraceNtro trace = (TaskTraceNtro) previousTask.newTrace();
			previousTasksTraces.add(trace);
		});
		
		if(task.hasParentTask()) {
			task.parentTask().entryTasks().forEach(parentEntryTask -> {
				parentEntryTasksTraces.add((AtomicTaskTraceNtro) parentEntryTask.newTrace());
			});
		}
	}
	
	protected Stream<TaskTraceNtro> previousTraces(){
		return Stream.fromSet(previousTasksTraces);
	}

	protected Stream<AtomicTaskTraceNtro> parentTraces(){
		return Stream.fromSet(parentEntryTasksTraces);
	}

	protected Stream<AtomicTaskTraceNtro> entryTraces(){
		return Stream.fromSet(entryTasksTraces);
	}

	protected Stream<TaskTraceNtro> subTraces(){
		return Stream.fromSet(subTasksTraces);
	}

	protected Stream<AtomicTaskTraceNtro> exitTraces(){
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
	
}
