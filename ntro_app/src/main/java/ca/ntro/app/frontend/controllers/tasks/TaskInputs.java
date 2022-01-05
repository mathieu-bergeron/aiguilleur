package ca.ntro.app.frontend.controllers.tasks;

import ca.ntro.app.frontend.View;
import ca.ntro.app.frontend.Window;
import ca.ntro.app.messages.Message;
import ca.ntro.app.models.ModelUpdates;
import ca.ntro.core.identifyers.ModelId;

public interface TaskInputs {
	
	public static Class<? extends Window> window(){
		return Window.class;
	}

	public static <V extends View> Class<V> view(Class<V> viewClass){
		return viewClass;
	}
	
	<O extends Object> O get(Class<O> _class);
	

	<V extends View> V getView(Class<V> viewClass);
	<V extends View> V getCreatedView(Class<V> viewClass);
	<V extends View> ViewLoader<V> getViewLoader(Class<V> viewClass);


	ModelUpdates getModelUpdates(ModelId id);

	<MSG extends Message> MSG getMessage(Class<MSG> messageClass);

}
