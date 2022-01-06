package ca.ntro.app.frontend.tasks;

import ca.ntro.app.frontend.handlers.FrontendCancelHandler;
import ca.ntro.core.wrappers.future.ExceptionHandler;

public interface FrontendTaskCreator {

	<R extends Object> TypedFrontendTaskCreator<R> create(TypedFrontendTaskDescriptor<R> task);

	FrontendTaskCreator implement(TypedFrontendTaskDescriptor<?> task);

	FrontendTaskCreator waitFor(TypedFrontendTaskDescriptor<?> task);

	FrontendTaskCreator execute(FrontendExecutor executor);
	FrontendTaskCreator thenExecute(FrontendExecutor executor);
	FrontendTaskCreator thenExecuteBlocking(BlockingFrontendExecutor executor);

	FrontendTaskCreator onCancel(FrontendCancelHandler cancelHandler);
	FrontendTaskCreator onFailure(ExceptionHandler exceptionHandler);

	TypedFrontendTaskDescriptor<?> getTask();

}
