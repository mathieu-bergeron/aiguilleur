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
	private Map<String, AtomicTaskTraceNtro> traces = new HashMap<>();

	public GenericTaskNtro<?, ?> getTask() {
		return task;
	}

	public void setTask(GenericTaskNtro<?, ?> task) {
		this.task = task;
	}

	public Map<String, AtomicTaskTraceNtro> getTraces() {
		return traces;
	}

	public void setTraces(Map<String, AtomicTaskTraceNtro> traces) {
		this.traces = traces;
	}
	
	
	public TaskTraceNtro() {
	}

	public TaskTraceNtro(GenericTaskNtro<?, ?> task) {
		setTask(task);
	}
	
	
	
	
	
	
	

	@Override
	public boolean hasCurrent() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ObjectMap current() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isWaiting() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void advanceToNext() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean contains(Id id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean contains(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public <O> O get(Class<O> _class, Id id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <O> O get(Class<O> _class, String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object get(Id id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object get(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Stream<String> ids() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Stream<Object> objects() {
		// TODO Auto-generated method stub
		return null;
	}

}
