package ca.ntro.app.backend.handlers;

import ca.ntro.app.messages.Message;
import ca.ntro.app.models.Model;
import ca.ntro.core.wrappers.future.ExceptionHandler;

public interface HandlerCreator {

	HandlerCreator to(String string);
	HandlerCreator withModel(Class<? extends Model> modelClass);
	HandlerCreator when(BackendEvent event);
	HandlerCreator and(BackendEvent event);
	HandlerCreator onCancel(BackendCancelHandler cancelHandler);
	HandlerCreator onFailure(ExceptionHandler handler);
	
	static BackendEvent msgReceived(Class<? extends Message> messageClass) {
		return null;
	}

	static BackendEvent modelLoaded(Class<? extends Model> modelClass) {
		return null;
	}
	
	
	HandlerCreator execute(BackendExecutor executor);

}
