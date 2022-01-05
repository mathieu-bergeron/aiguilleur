package ca.ntro.app.frontend.controllers.tasks;

import ca.aiguilleur.frontend.root.AiguilleurRootView;
import ca.ntro.app.events.Event;
import ca.ntro.app.frontend.View;
import ca.ntro.app.frontend.handlers.FrontendCancelHandler;
import ca.ntro.app.messages.Message;
import ca.ntro.app.models.Model;
import ca.ntro.core.identifyers.ModelId;
import ca.ntro.core.task_graphs.generic_task_graph.GenericTask;
import ca.ntro.core.wrappers.future.ExceptionHandler;

public interface FrontendTasks {

	public static <V extends View> FrontendTask viewLoaded(Class<V> viewClass) {
		return null;
	}
	
	public static <V extends View> FrontendTask viewCreated(Class<V> viewClass) {
		return null;
	}

	public static FrontendTask windowDisplayed() {
		return null;
	}

	public static <V extends View> FrontendTask viewInstalled(Class<V> viewClass) {
		return null;
	}

	public static <V extends View> String installView(Class<V> viewClass) {
		return null;
	}

	public static FrontendTask modelObserved(ModelId id) {
		return null;
	}

	public static FrontendTask messageReceived(Class<? extends Message> messageClass) {
		return null;
	}

	public static FrontendTask windowCreated() {
		return null;
	}

	static FrontendTask eventTriggered(Class<? extends Event> eventClass) {
		return null;
	}

	FrontendTasks whenExists(FrontendTask task);
	FrontendTasks waitFor(FrontendTask task);

	FrontendTasks withInput(Class<?> _class);

	FrontendTasks and(FrontendTask task);

	FrontendTasks execute(FrontendExecutor executor);
	FrontendTasks onCancel(FrontendCancelHandler cancelHandler);
	FrontendTasks onFailure(ExceptionHandler exceptionHandler);
	FrontendTasks setTaskName(String taskName);

	GenericTask<?,?> toTask();

	<M extends Model> void removeModelObservers(Class<M> modelClass);
	void removeModelObserver(ModelId modelId);

	FrontendTasks create(String taskId);
	
	FrontendTask getTask();

	void whenDisplayed(Class<?> _class);

	FrontendTasks after(String string);

	FrontendTasks create(Object b);

	
	
}
