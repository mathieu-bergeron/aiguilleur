package ca.ntro.core.task_graphs.ttask_graphs.generic_ttask_graph;

import ca.ntro.core.task_graphs.handlers.CancelHandler;
import ca.ntro.core.task_graphs.handlers.ExecuteHandler;
import ca.ntro.core.wrappers.future.ExceptionHandler;

public interface GenericExecutableTask<T  extends GenericTTask<T,ST,ET,TG,G>,
                                       ST extends GenericSimpleTask<T,ST,ET,TG,G>,
                                       ET extends GenericExecutableTask<T,ST,ET,TG,G>,
                                       TG extends GenericTaskGroup<T,ST,ET,TG,G>,
                                       G  extends GenericTTaskGraph<T,ST,ET,TG,G>>

       extends   GenericSimpleTask<T,ST,ET,TG,G> {

	void execute(ExecuteHandler executeHandler);
	void cancel(CancelHandler cancelHandler);
	void handleException(ExceptionHandler exceptionHandler);

}
