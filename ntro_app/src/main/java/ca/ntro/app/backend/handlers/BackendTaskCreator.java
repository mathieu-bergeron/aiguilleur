package ca.ntro.app.backend.handlers;

import ca.ntro.app.messages.Message;
import ca.ntro.app.models.Model;
import ca.ntro.core.wrappers.future.ExceptionHandler;

public interface BackendTaskCreator {

	BackendTaskCreator execute(String string);
	BackendTaskCreator withModel(Class<? extends Model> modelClass);
	BackendTaskCreator when(BackendEvent event);
	BackendTaskCreator and(BackendEvent event);
	BackendTaskCreator onCancelDo(BackendCancelHandler cancelHandler);
	BackendTaskCreator onFailureDo(ExceptionHandler handler);
	
	static BackendEvent message(Class<? extends Message> messageClass) {
		return null;
	}


	static BackendEvent model(Class<? extends Model> modelClass) {
		return null;
	}
	
	
	BackendTaskCreator thenDo(BackendExecutor executor);
	BackendTaskCreator waitFor(BackendEvent msgReceived);

}
