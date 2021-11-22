package ca.ntro.app.frontend;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import ca.ntro.app.views.FxRootView;
import ca.ntro.app.views.FxView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class ViewRegistrarFx implements ViewRegistrar<FxView>, ViewRegistrarAccessor<FxRootView, FxView> {

	private FxRootView rootView;
	private Scene rootScene;
	
	private Map<Class<? extends NtroView>, Scene> scenes = new HashMap<>();
	private Map<Class<? extends NtroView>, FxView> views = new HashMap<>();

	public Scene rootScene() {
		return rootScene;
	}

	public Scene scene(Class<? extends NtroView> viewClass) {
		return scenes.get(viewClass);
	}
	
	public void registerRootView(String fxmlPath, 
			                     int width, 
			                     int height) {

		URL xmlFile = ViewRegistrarFx.class.getResource(fxmlPath);
		
		if(xmlFile == null) {
			throw new RuntimeException("Not found " + fxmlPath);
		}
		
		FXMLLoader loader = new FXMLLoader(xmlFile);

		rootView = (FxRootView) loader.getController();
		
		Parent parent;
		try {

			parent = loader.load();

		} catch (IOException e) {

			throw new RuntimeException("Cannot load " + fxmlPath);
		}
		
		rootScene = new Scene(parent, width, height);
	}

	public void registerView(Class<? extends NtroView> viewClass, String fxmlPath) {
		
	}

	@Override
	public FxRootView rootView() {
		return rootView;
	}

	@Override
	public FxView view(Class<? extends NtroView> viewClass) {
		return views.get(viewClass);
	}
}
