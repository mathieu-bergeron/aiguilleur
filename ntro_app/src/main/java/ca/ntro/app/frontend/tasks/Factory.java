package ca.ntro.app.frontend.tasks;

import ca.ntro.app.events.Event;
import ca.ntro.app.frontend.View;
import ca.ntro.app.frontend.ViewLoader;
import ca.ntro.app.frontend.Window;
import ca.ntro.app.messages.Message;
import ca.ntro.app.models.Model;
import ca.ntro.app.models.ModelUpdates;

public interface Factory {

	public static TypedFrontendTaskDescriptor<Window> window() {
		return null;
	}

	public static <V extends View> TypedFrontendTaskDescriptor<?> task(String taskId) {
		return null;
	}

	public static <V extends View> TypedFrontendTaskDescriptor<V> view(Class<V> viewClass) {
		return null;
	}

	public static <V extends View> TypedFrontendTaskDescriptor<ViewLoader<V>> viewLoader(Class<V> viewClass) {
		return null;
	}

	public static <M extends Model, MU extends ModelUpdates> TypedFrontendTaskDescriptor<MU> modelUpdates(Class<M> modelClass, String modelId) {
		return null;
	}

	public static <M extends Model, MU extends ModelUpdates> TypedFrontendTaskDescriptor<MU> modelUpdates(Class<M> modelClass) {
		return null;
	}

	public static <MSG extends Message> TypedFrontendTaskDescriptor<MSG> message(Class<MSG> messageClass) {
		return null;
	}

	public static <EVT extends Event> TypedFrontendTaskDescriptor<EVT> event(Class<EVT> eventClass) {
		return null;
	}

}
