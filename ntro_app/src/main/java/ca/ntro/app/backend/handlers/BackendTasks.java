package ca.ntro.app.backend.handlers;

import ca.ntro.app.events.Event;
import ca.ntro.app.messages.Message;
import ca.ntro.app.models.Model;
import ca.ntro.core.wrappers.future.ExceptionHandler;

public interface BackendTasks {

	BackendTasks to(String string);
	BackendTasks withModel(Class<? extends Model> modelClass);
	BackendTasks when(BackendEvent event);
	BackendTasks and(BackendEvent event);
	BackendTasks onCancel(BackendCancelHandler cancelHandler);
	BackendTasks onFailure(ExceptionHandler handler);
	
	static BackendEvent msgReceived(Class<? extends Message> messageClass) {
		return null;
	}


	static BackendEvent modelLoaded(Class<? extends Model> modelClass) {
		return null;
	}
	
	
	BackendTasks execute(BackendExecutor executor);

}
