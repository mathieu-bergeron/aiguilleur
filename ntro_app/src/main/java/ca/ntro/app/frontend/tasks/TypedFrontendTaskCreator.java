package ca.ntro.app.frontend.tasks;

import ca.ntro.app.frontend.handlers.FrontendCancelHandler;
import ca.ntro.core.wrappers.future.ExceptionHandler;

public interface TypedFrontendTaskCreator<R extends Object> {

	TypedFrontendTaskCreator<R> waitFor(TypedFrontendTaskDescriptor<?> task);

	TypedFrontendTaskCreator<R> execute(TypedBlockingFrontendExecutor<R> executor);
	TypedFrontendTaskCreator<R> thenExecute(TypedBlockingFrontendExecutor<R> executor);

	TypedFrontendTaskCreator<R> executeAsync(TypedFrontendExecutor<R> executor);
	TypedFrontendTaskCreator<R> thenExecuteAsync(TypedFrontendExecutor<R> executor);

	TypedFrontendTaskCreator<R> onCancel(FrontendCancelHandler cancelHandler);
	TypedFrontendTaskCreator<R> onFailure(ExceptionHandler exceptionHandler);

	TypedFrontendTaskDescriptor<R> getTask();

}
