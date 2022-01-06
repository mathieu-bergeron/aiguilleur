package ca.ntro.app.backend.handlers;

import ca.ntro.app.messages.Message;
import ca.ntro.app.models.Model;
import ca.ntro.core.wrappers.future.ExceptionHandler;

public interface BackendTaskCreator {

	static BackendTaskDescriptor<?> task(String actionName) {
		return null;
	}

	static <MSG extends Message> BackendTaskDescriptor<MSG> message(Class<MSG> messageClass) {
		return null;
	}

	static <M extends Model> BackendTaskDescriptor<M> model(Class<M> modelClass) {
		return null;
	}

	BackendTaskCreator implement(BackendTaskDescriptor<?> event);

	BackendTaskCreator waitFor(BackendTaskDescriptor<?> precondition);

	BackendTaskCreator execute(BackendExecutor executor);
	BackendTaskCreator thenExecute(BackendExecutor executor);


	BackendTaskCreator onCancelDo(BackendCancelHandler cancelHandler);
	BackendTaskCreator onFailureDo(ExceptionHandler handler);

}
