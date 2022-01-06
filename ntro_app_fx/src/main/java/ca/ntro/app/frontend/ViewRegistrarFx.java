package ca.ntro.app.frontend;

import java.util.HashMap;
import java.util.Map;

import ca.ntro.app.views.ViewFx;
import javafx.scene.Scene;

public class ViewRegistrarFx implements ViewRegistrar<ViewFx>, ViewRegistrarAccessor<ViewFx> {

	private Scene rootScene;
	
	private Map<Class<? extends View>, Scene> scenes = new HashMap<>();
	private Map<Class<? extends View>, ViewFx> views = new HashMap<>();

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
		
	}

	@Override
	public ViewFx view(Class<? extends View> viewClass) {
		return views.get(viewClass);
	}

	@Override
	public void addViewLoaderTasks(FrontendTaskCreatorNtro taskCreator) {
		// TODO Auto-generated method stub
		
	}
}
