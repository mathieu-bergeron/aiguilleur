package ca.ntro.app.frontend.tasks;

import ca.ntro.app.frontend.View;
import ca.ntro.app.frontend.ViewLoader;
import ca.ntro.app.frontend.events.Event;
import ca.ntro.app.messages.Message;
import ca.ntro.app.models.Model;
import ca.ntro.app.models.ModelUpdates;
import ca.ntro.app.services.Window;
import ca.ntro.core.identifyers.Key;
import ca.ntro.core.initialization.Ntro;
import ca.ntro.core.task_graphs.generic_task_graph.TaskId;
import ca.ntro.core.task_graphs.generic_task_graph.TaskIdNtro;

public interface Factory {

	public static TypedFrontendTaskDescriptor<Window> window() {
		TypedFrontendTaskDescriptorNtro<Window> descriptor = new TypedFrontendTaskDescriptorNtro<>();
		
		descriptor.setId(new TaskIdNtro("window"));
		
		return descriptor;
	}

	public static <O extends Object> TypedFrontendTaskDescriptor<O> task(String taskId) {
		TypedFrontendTaskDescriptorNtro<O> descriptor = new TypedFrontendTaskDescriptorNtro<>();

		descriptor.setId(new TaskIdNtro(taskId));

		return descriptor;
	}

	public static <V extends View> TypedFrontendTaskDescriptor<V> view(Class<V> viewClass) {
		TypedFrontendTaskDescriptorNtro<V> descriptor = new TypedFrontendTaskDescriptorNtro<>();

		descriptor.setId(new TaskIdNtro(Ntro.reflectionService().simpleName(viewClass)));

		return descriptor;
	}

	public static <V extends View> TypedFrontendTaskDescriptor<ViewLoader<V>> viewLoader(Class<V> viewClass) {
		TypedFrontendTaskDescriptorNtro<ViewLoader<V>> descriptor = new TypedFrontendTaskDescriptorNtro<>();

		descriptor.setId(new TaskIdNtro("viewLoader[" + Ntro.reflectionService().simpleName(viewClass)+ "]"));

		return descriptor;
	}

	public static TypedFrontendTaskDescriptor<String> fileLoader(String filePath) {
		TypedFrontendTaskDescriptorNtro<String> descriptor = new TypedFrontendTaskDescriptorNtro<>();

		descriptor.setId(new TaskIdNtro("fileLoader[" + filePath + "]"));

		return descriptor;
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
		TypedFrontendTaskDescriptorNtro<EVT> descriptor = new TypedFrontendTaskDescriptorNtro<>();

		descriptor.setId(new TaskIdNtro("event[" + Ntro.reflectionService().simpleName(eventClass)+ "]"));

		return descriptor;
	}

	public static TypedFrontendTaskDescriptor<?> taskGroup(TypedFrontendTaskDescriptor<?> task) {
		TypedFrontendTaskDescriptorNtro<?> descriptor = new TypedFrontendTaskDescriptorNtro<>();

		descriptor.setId(new TaskIdNtro("[" + task.id().toKey().toString() + "]"));

		return descriptor;
	}

}
