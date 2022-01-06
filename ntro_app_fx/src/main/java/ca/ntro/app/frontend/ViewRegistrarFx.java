package ca.ntro.app.frontend;


import java.util.HashMap;
import java.util.Map;

import ca.ntro.app.views.ViewFx;
import ca.ntro.core.task_graphs.task_graph.AtomicTask;
import ca.ntro.core.task_graphs.task_graph.AtomicTaskCondition;
import ca.ntro.core.task_graphs.task_graph.Task;
import javafx.scene.Scene;

import static ca.ntro.app.frontend.tasks.Factory.*;

public class ViewRegistrarFx implements ViewRegistrar<ViewFx>, ViewRegistrarAccessor<ViewFx> {

	private Scene rootScene;
	
	private Map<Class<? extends View>, Scene> scenes = new HashMap<>();
	private Map<Class<? extends View>, ViewFx> views = new HashMap<>();

	private Map<Class<? extends View>, String> fxmlFiles = new HashMap<>();

	public Scene rootScene() {
		return rootScene;
	}

	public Scene scene(Class<? extends View> viewClass) {
		return scenes.get(viewClass);
	}
	
	/*
	public void registerRootView(String fxmlPath, 
			                     int width, 
			                     int height) {

		URL xmlFile = ViewRegistrarFx.class.getResource(fxmlPath);
		
		if(xmlFile == null) {
			throw new RuntimeException("Not found " + fxmlPath);
		}
		
		FXMLLoader loader = new FXMLLoader(xmlFile);

		rootView = (RootViewFx) loader.getController();
		
		Parent parent;
		try {

			parent = loader.load();

		} catch (IOException e) {

			throw new RuntimeException(e);
		}
		
		rootScene = new Scene(parent, width, height);
	}
	*/

	public void registerView(Class<? extends View> viewClass, String fxmlPath) {
		fxmlFiles.put(viewClass, fxmlPath);
	}

	@Override
	public ViewFx view(Class<? extends View> viewClass) {
		return views.get(viewClass);
	}

	@Override
	public void addViewLoaderTasks(FrontendTaskCreatorNtro taskCreator) {
		for(Map.Entry<Class<? extends View>, String> entry : fxmlFiles.entrySet()) {

			Class<? extends View> viewClass = entry.getKey();
			String fxmlPath = entry.getValue();
			
			Task viewLoaderTask = taskCreator.getTaskGraph().addTask(viewLoader(viewClass).id());
			AtomicTask createViewLoader = viewLoaderTask.addEntryTask(viewLoader(viewClass).id().toKey().toString(), AtomicTaskCondition.class);
			
			createViewLoader.execute((inputs, notify) -> {
				notify.addResult(new ViewLoaderFx<>(fxmlPath));
			});
		}
	}
}
