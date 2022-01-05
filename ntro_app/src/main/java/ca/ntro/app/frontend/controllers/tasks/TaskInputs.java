package ca.ntro.app.frontend.controllers.tasks;

import ca.ntro.app.frontend.View;
import ca.ntro.app.messages.Message;
import ca.ntro.app.models.ModelUpdates;
import ca.ntro.core.identifyers.ModelId;

public interface TaskInputs {

	<V extends View> V getDisplayedView(Class<V> viewClass);
	<V extends View> V getCreatedView(Class<V> viewClass);
	<V extends View> ViewCreator<V> getViewLoader(Class<V> viewClass);


	ModelUpdates getModelUpdates(ModelId id);

	<MSG extends Message> MSG getMessage(Class<MSG> messageClass);

}
