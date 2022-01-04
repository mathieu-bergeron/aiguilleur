package ca.ntro.app.backend.handlers;

import ca.ntro.app.messages.Message;
import ca.ntro.app.models.Model;

public interface BackendResults {

	<M extends Model> M getModel(Class<M> modelClass);
	<MSG extends Message> MSG getMessage(Class<MSG> messageClass);

}
