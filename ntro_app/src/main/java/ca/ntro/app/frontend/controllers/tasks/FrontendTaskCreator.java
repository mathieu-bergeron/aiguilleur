package ca.ntro.app.frontend.controllers.tasks;

import ca.ntro.app.events.Event;
import ca.ntro.app.frontend.View;
import ca.ntro.app.frontend.Window;
import ca.ntro.app.frontend.handlers.FrontendCancelHandler;
import ca.ntro.app.messages.Message;
import ca.ntro.app.models.Model;
import ca.ntro.app.models.ModelUpdates;
import ca.ntro.core.identifyers.ModelId;
import ca.ntro.core.task_graphs.generic_task_graph.GenericTask;
import ca.ntro.core.wrappers.future.ExceptionHandler;

public interface FrontendTaskCreator {

	public static FrontendTaskDescriptor<Window> window() {
		return null;
	}

	public static <V extends View> FrontendTaskDescriptor<?> task(String taskId) {
		return null;
	}

	public static <V extends View> FrontendTaskDescriptor<V> view(Class<V> viewClass) {
		return null;
	}

	public static <V extends View> FrontendTaskDescriptor<ViewLoader<V>> viewLoader(Class<V> viewClass) {
		return null;
	}

	public static <M extends Model, MU extends ModelUpdates> FrontendTaskDescriptor<MU> modelUpdates(Class<M> modelClass, String modelId) {
		return null;
	}

	public static <M extends Model, MU extends ModelUpdates> FrontendTaskDescriptor<MU> modelUpdates(Class<M> modelClass) {
		return null;
	}

	public static <MSG extends Message> FrontendTaskDescriptor<MSG> message(Class<MSG> messageClass) {
		return null;
	}

	public static <EVT extends Event> FrontendTaskDescriptor<EVT> event(Class<EVT> eventClass) {
		return null;
	}

	FrontendTaskCreator create(FrontendTaskDescriptor<?> task);
	FrontendTaskCreator implement(FrontendTaskDescriptor<?> task);

	FrontendTaskCreator waitFor(FrontendTaskDescriptor<?> task);

	FrontendTaskCreator execute(FrontendExecutor executor);
	FrontendTaskCreator thenExecute(FrontendExecutor executor);

	FrontendTaskCreator onCancel(FrontendCancelHandler cancelHandler);
	FrontendTaskCreator onFailure(ExceptionHandler exceptionHandler);

	FrontendTaskDescriptor<?> getTask();

}
