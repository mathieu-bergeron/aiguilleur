package ca.ntro.app.frontend.controllers.tasks;

import ca.ntro.app.frontend.View;
import ca.ntro.app.messages.Message;
import ca.ntro.app.models.ModelUpdates;
import ca.ntro.core.identifyers.ModelId;

public interface TaskResults {

	<V extends View> V getDisplayedView(Class<V> viewClass);
	<V extends View> V getCreatedView(Class<V> viewClass);
	<V extends View> ViewCreator<V> getViewLoader(Class<V> viewClass);


	ModelUpdates getModelUpdates(ModelId id);

	<V extends View> void registerView(Class<V> viewClass, V view);

	<MSG extends Message> MSG getMessage(Class<MSG> messageClass);

}
