package ca.ntro.app.backend.handlers;

import ca.ntro.app.messages.Message;
import ca.ntro.app.models.Model;

public interface HandlerCreator {

	HandlerCreator to(String string);
	HandlerCreator withModel(Class<? extends Model> modelClass);
	HandlerCreator when(BackendEvent event);
	
	static BackendEvent msgReceived(Class<? extends Message> messageClass) {
		return null;
	}
	
	
	HandlerCreator execute(BackendExecutor executor);

}
