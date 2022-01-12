package ca.ntro.app.frontend.tasks;


import ca.ntro.app.Locale;
import ca.ntro.app.backend.Session;
import ca.ntro.app.frontend.View;
import ca.ntro.app.frontend.ViewLoader;
import ca.ntro.app.frontend.events.Event;
import ca.ntro.app.messages.Message;
import ca.ntro.app.models.Model;
import ca.ntro.app.models.ModelUpdates;
import ca.ntro.app.services.Window;
import ca.ntro.core.initialization.Ntro;
import ca.ntro.core.task_graphs.generic_task_graph.TaskIdNtro;

public interface Factory {

	/* TODO: session
	 * 
	 * must ask backend (which means asking server for remoteBackend)
	 */
	public static TypedFrontendTaskDescriptor<Session> session() {
		TypedFrontendTaskDescriptorNtro<Session> descriptor = new TypedFrontendTaskDescriptorNtro<>();
		
		descriptor.setId(new TaskIdNtro("session"));
		
		return descriptor;
	}

	/* TODO: locale, to reload views when lang has changed
	 */
	public static TypedFrontendTaskDescriptor<Locale> locale() {
		TypedFrontendTaskDescriptorNtro<Locale> descriptor = new TypedFrontendTaskDescriptorNtro<>();
		
		descriptor.setId(new TaskIdNtro("locale"));
		
		return descriptor;
	}

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

	/* TODO: loading a localized view
	 */
	public static <V extends View> TypedFrontendTaskDescriptor<V> view(Class<V> viewClass, Locale locale) {
		TypedFrontendTaskDescriptorNtro<V> descriptor = new TypedFrontendTaskDescriptorNtro<>();

		descriptor.setId(new TaskIdNtro(Ntro.reflectionService().simpleName(viewClass) + "_" + locale.toString()));

		return descriptor;
	}
	
	/* TODO: load the view of the current locale (or default locale?)
	 */

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
