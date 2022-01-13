package ca.ntro.app.frontend;


import java.util.HashMap;
import java.util.Map;

import ca.ntro.app.Locale;
import ca.ntro.app.NtroApp;
import ca.ntro.app.views.ViewLoaderFx;
import ca.ntro.core.task_graphs.task_graph.AtomicTask;
import ca.ntro.core.task_graphs.task_graph.AtomicTaskCondition;
import ca.ntro.core.task_graphs.task_graph.Task;

import static ca.ntro.app.frontend.tasks.Factory.*;

public class ViewRegistrarFx implements ViewRegistrar, ViewRegistrarAccessor {

	private String cssPath;
	private Map<Locale, String> resourcesPaths = new HashMap<>();
	private Map<Class<? extends View<?>>, String> fxmlPaths = new HashMap<>();
	private Map<Class<? extends View<?>>, View<?>> views = new HashMap<>();


	public <V extends View<?>> void registerView(Class<V> viewClass, String fxmlPath) {
		fxmlPaths.put(viewClass, fxmlPath);
	}

	public void registerDefaultResources(String resourcesPath) {
		resourcesPaths.put(NtroApp.currentLocale(), resourcesPath);
	}

	public void registerResources(Locale locale, String resourcesPath) {
		resourcesPaths.put(locale, resourcesPath);
	}

	public void registerStylesheet(String cssPath) {
		this.cssPath = cssPath;
	}


	@SuppressWarnings("unchecked")
	@Override
	public <V extends View<?>> V view(Class<V> viewClass) {
		return (V) views.get(viewClass);
	}

	@Override
	public void addViewLoaderTasks(FrontendTaskCreatorNtro taskCreator) {
		for(Map.Entry<Class<? extends View<?>>, String> entry : fxmlPaths.entrySet()) {

			Class<? extends View<?>> viewClass = entry.getKey();
			String fxmlPath = entry.getValue();
			
			Task viewLoaderTask = taskCreator.getTaskGraph().addTask(viewLoader(viewClass).id());
			AtomicTask createViewLoader = viewLoaderTask.addEntryTask(viewLoader(viewClass).id().toKey().toString(), AtomicTaskCondition.class);
			
			createViewLoader.execute((inputs, notify) -> {
				ViewLoaderFx<?> viewLoader = new ViewLoaderFx<>();

				viewLoader.setFxmlPath(fxmlPath);
				if(cssPath !=null) {
					viewLoader.setCssPath(cssPath);
				}
				
				String resourcesPath = resourcesPaths.get(NtroApp.currentLocale());
				if(resourcesPath != null) {
					viewLoader.setResourcesPath(resourcesPath);
				}
				
				//viewLoader.createFxmlLoader();
				
				notify.addResult(viewLoader);
			});
		}
	}
}
