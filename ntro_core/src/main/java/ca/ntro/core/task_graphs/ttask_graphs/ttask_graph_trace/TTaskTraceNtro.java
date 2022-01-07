package ca.ntro.core.task_graphs.ttask_graphs.ttask_graph_trace;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ca.ntro.core.identifyers.Id;
import ca.ntro.core.stream.Stream;
import ca.ntro.core.task_graphs.task_graph_trace.TaskTrace;
import ca.ntro.core.task_graphs.ttask_graphs.generic_ttask_graph.GenericSimpleTaskNtro;
import ca.ntro.core.values.ObjectMap;

public class TTaskTraceNtro 

       implements TaskTrace, 
                  ObjectMap {
	
	private GenericSimpleTaskNtro<?,?,?,?,?> task;
	private TTaskGraphTraceNtro parentTrace;

	private Map<String, TTaskTraceNtro> preconditions = new HashMap<>();
	private List<Object> results = new ArrayList<>();

	private boolean isWaiting = false;
	private boolean hasChangedState = false;

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

	@Override
	public boolean isBlocked() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isInProgress() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isDone() {
		// TODO Auto-generated method stub
		return false;
	}

}
