package ca.ntro.core.task_graphs.ttask_graphs.generic_ttask_graph;

import ca.ntro.core.stream.Stream;
import ca.ntro.core.task_graphs.generic_task_graph.TaskGraphSearchOptions;
import ca.ntro.core.task_graphs.generic_task_graph.TaskId;

public class GenericTTaskNtro<T  extends GenericTTask<T,ST,ET,TG,G>,
                              ST extends GenericSimpleTask<T,ST,ET,TG,G>,
                              ET extends GenericExecutableTask<T,ST,ET,TG,G>,
                              TG extends GenericTaskGroup<T,ST,ET,TG,G>,
                              G  extends GenericTTaskGraph<T,ST,ET,TG,G>> 

       implements GenericTTask<T,ST,ET,TG,G> {

	@Override
	public TaskId id() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public G parentGraph() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasParentTask() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public T parentTask() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isTaskGroup() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isSimpleTask() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public TG asTaskGroup() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ST asSimpleTask() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T addPreviousTask(TaskId id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T addPreviousTask(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addPreviousTask(T previousTask) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public T addNextTask(TaskId id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T addNextTask(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addNextTask(T nextTask) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Stream<T> previousTasks() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Stream<T> nextTasks() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Stream<T> reachableTasks() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Stream<T> reachableTasks(TaskGraphSearchOptions options) {
		// TODO Auto-generated method stub
		return null;
	}
}
	
