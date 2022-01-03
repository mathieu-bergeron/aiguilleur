package ca.ntro.core.task_graphs.generic_task_graph.handlers;

import ca.ntro.core.initialization.Ntro;
import ca.ntro.core.wrappers.future.ExceptionHandler;

public class ExceptionHandlerDefault implements ExceptionHandler {

	@Override
	public void handle(Throwable t) {
		Ntro.exceptions().throwException(t);
	}

}
