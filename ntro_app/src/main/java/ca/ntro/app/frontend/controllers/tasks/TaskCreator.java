package ca.ntro.app.frontend.controllers.tasks;

import ca.ntro.app.frontend.View;
import ca.ntro.app.messages.Message;
import ca.ntro.core.graphs.task_graph.Task;
import ca.ntro.core.identifyers.ModelId;

public interface TaskCreator {

	public static ControllerEvent viewLoaded(Class<? extends View> viewClass) {
		return null;
	}
	
	public static ControllerEvent viewCreated(Class<? extends View> viewClass) {
		return null;
	}

	public static ControllerEvent viewDisplayed(Class<? extends View> viewClass) {
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
}
