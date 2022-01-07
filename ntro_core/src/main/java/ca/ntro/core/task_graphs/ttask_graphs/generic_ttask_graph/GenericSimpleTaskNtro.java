package ca.ntro.core.task_graphs.ttask_graphs.generic_ttask_graph;

import ca.ntro.core.task_graphs.task_graph_trace.TaskGraphTrace;
import ca.ntro.core.task_graphs.task_graph_trace.TaskTrace;

public class GenericSimpleTaskNtro<T  extends GenericTTask<T,ST,ET,TG,G>,
                                   ST extends GenericSimpleTask<T,ST,ET,TG,G>,
                                   ET extends GenericExecutableTask<T,ST,ET,TG,G>,
                                   TG extends GenericTaskGroup<T,ST,ET,TG,G>,
                                   G  extends GenericTTaskGraph<T,ST,ET,TG,G>> 

       extends GenericTTaskNtro<T,ST,ET,TG,G>

       implements GenericSimpleTask<T,ST,ET,TG,G> {

	@Override
	public boolean isExecutableTask() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ET asExecutableTask() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TaskTrace newTrace(TaskGraphTrace parentTrace) {
		// TODO Auto-generated method stub
		return null;
	}
}
	
