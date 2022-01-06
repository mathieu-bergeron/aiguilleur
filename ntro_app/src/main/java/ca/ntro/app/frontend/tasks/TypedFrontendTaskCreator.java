package ca.ntro.app.frontend.tasks;

import ca.ntro.app.frontend.handlers.FrontendCancelHandler;
import ca.ntro.core.wrappers.future.ExceptionHandler;

public interface TypedFrontendTaskCreator<R extends Object> {

	TypedFrontendTaskCreator<R> waitFor(TypedFrontendTaskDescriptor<?> task);

	TypedFrontendTaskCreator<R> execute(TypedFrontendExecutor<R> executor);
	TypedFrontendTaskCreator<R> thenExecute(TypedFrontendExecutor<R> executor);

	TypedFrontendTaskCreator<R> onCancel(FrontendCancelHandler cancelHandler);
	TypedFrontendTaskCreator<R> onFailure(ExceptionHandler exceptionHandler);

	TypedFrontendTaskDescriptor<R> getTask();

}
