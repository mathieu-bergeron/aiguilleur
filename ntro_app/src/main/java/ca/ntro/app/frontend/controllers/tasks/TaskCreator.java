package ca.ntro.app.frontend.controllers.tasks;

import ca.ntro.app.frontend.View;
import ca.ntro.app.messages.Message;
import ca.ntro.app.models.Model;
import ca.ntro.core.identifyers.ModelId;
import ca.ntro.core.task_graphs.generic_task_graph.GenericTask;

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

	TaskCreator execute(TaskExecutor executor);
	TaskCreator onCancel();
	TaskCreator onFailure(Throwable t);
	TaskCreator setTaskName(String taskName);

	GenericTask<?,?> toTask();

	<M extends Model> void removeModelObservers(Class<M> modelClass);
	void removeModelObserver(ModelId modelId);

	TaskCreator to(String taskId);
}
