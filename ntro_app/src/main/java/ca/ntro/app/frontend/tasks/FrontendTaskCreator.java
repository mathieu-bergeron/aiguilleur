package ca.ntro.app.frontend.tasks;

import ca.ntro.app.frontend.handlers.FrontendCancelHandler;
import ca.ntro.core.wrappers.future.ExceptionHandler;

public interface FrontendTaskCreator {

	<R extends Object> TypedFrontendTaskCreator<R> create(TypedFrontendTaskDescriptor<R> task);

	FrontendTaskCreator implement(TypedFrontendTaskDescriptor<?> task);

	FrontendTaskCreator define(TypedFrontendTaskDescriptor<?> task);

	FrontendTaskCreator waitFor(TypedFrontendTaskDescriptor<?> task);

	FrontendTaskCreator execute(BlockingFrontendExecutor executor);
	FrontendTaskCreator thenExecute(BlockingFrontendExecutor executor);

	FrontendTaskCreator executeAsync(FrontendExecutor executor);
	FrontendTaskCreator thenExecuteAsync(FrontendExecutor executor);

	FrontendTaskCreator onCancel(FrontendCancelHandler cancelHandler);
	FrontendTaskCreator onFailure(ExceptionHandler exceptionHandler);

	TypedFrontendTaskDescriptor<?> getTask();

}
