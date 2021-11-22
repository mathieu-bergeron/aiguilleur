package ca.ntro.app.frontend.controllers.tasks;

import ca.ntro.app.frontend.View;
import ca.ntro.app.messages.Message;
import ca.ntro.app.models.Model;
import ca.ntro.core.graphs.task_graph.Task;
import ca.ntro.core.identifyers.ModelId;

public interface TaskCreator {

	public static <V extends View> ControllerEvent viewLoaded(Class<V> viewClass) {
		return null;
	}
	
	public static <V extends View> ControllerEvent viewCreated(Class<V> viewClass) {
		return null;
	}

	public static <V extends View> ControllerEvent viewDisplayed(Class<V> viewClass) {
		return null;
	}

	public static ControllerEvent modelObserved(ModelId id) {
		return null;
	}

	public static ControllerEvent messageReceived(Class<? extends Message> messageClass) {
		return null;
	}

	TaskCreator when(ControllerEvent task);
	TaskCreator and(ControllerEvent task);

	Task execute(TaskExecutor executor);

	<M extends Model> void removeModelObservers(Class<M> modelClass);
	void removeModelObserver(ModelId modelId);
}
