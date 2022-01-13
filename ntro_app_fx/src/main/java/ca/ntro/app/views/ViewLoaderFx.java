package ca.ntro.app.views;


import java.io.IOException;
import java.net.URL;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import ca.ntro.app.frontend.View;
import ca.ntro.app.frontend.ViewLoader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;

public class ViewLoaderFx<V extends View<?>> implements ViewLoader<V> {
	
	private String fxmlPath;
	private String cssPath;
	private String resourcesPath;


	public String getFxmlPath() {
		return fxmlPath;
	}

	public void setFxmlPath(String fxmlPath) {
		this.fxmlPath = fxmlPath;
	}

	public String getCssPath() {
		return cssPath;
	}

	public void setCssPath(String cssPath) {
		this.cssPath = cssPath;
	}

	public String getResourcesPath() {
		return resourcesPath;
	}

	public void setResourcesPath(String resourcesPath) {
		this.resourcesPath = resourcesPath;
	}

	private URL urlFromPath(String path) {
		URL url = ViewLoaderFx.class.getResource(path);
		
		if(url == null) {
			throw new RuntimeException("Not found " + path);
		}

		return url;
	}

	private String resourceNameFromPath(String path) {
		String resourceName = path.replace("/", ".");

		if(resourceName.startsWith(".")) {
			resourceName = resourceName.substring(1);
		}

		if(resourceName.endsWith(".properties")) {
			int index = resourceName.lastIndexOf(".properties");
			resourceName = resourceName.substring(0,index);
		}

		return resourceName;
	}
	

	private FXMLLoader createFxmlLoader() {
		
		FXMLLoader loader = null;
		
		URL fxmlUrl = urlFromPath(getFxmlPath());
		ResourceBundle resources = loadResourceBundle();
		
		if(resources != null) {

			loader = new FXMLLoader(fxmlUrl, resources);
			
		}else {

			loader = new FXMLLoader(fxmlUrl);
		}
		
		return loader;
	}
	
	public ResourceBundle loadResourceBundle() {
		ResourceBundle resources = null;
		
		if(getResourcesPath() != null) {
			
			try {
				
				resources = ResourceBundle.getBundle(resourceNameFromPath(getResourcesPath()));
				
				if(resources == null) {
					throw new RuntimeException("Resources not found: " + getResourcesPath());
				}

			}catch(MissingResourceException e) {
				
				throw new RuntimeException("Resources not found: " + getResourcesPath());
			}
		}
		
		return resources;
	}


	@SuppressWarnings("unchecked")
	@Override
	public V createView() {

		FXMLLoader loader = createFxmlLoader();
		
		Parent parent = null;
		try {

			parent = loader.load();

		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		if(getCssPath() != null) {
			URL cssUrl = urlFromPath(getCssPath());
			
			if(cssUrl == null) {
				throw new RuntimeException(".css file not found: " + getCssPath());
			}

			parent.getStylesheets().add(cssUrl.toExternalForm());
		}
		
		V view = null;
		try {
			
			view = (V) loader.getController();

		}catch(Exception e) {

			throw new RuntimeException("Error with fx:controller", e);
		}
		
		if(view == null) {
			throw new RuntimeException("Error with fx:controller (view == null)");
		}

		if(!(view instanceof ViewFx)) {
			throw new RuntimeException("Error with fx:controller (view !instanceof ViewFx)");
		}
		
		((ViewFx) view).setPane((Pane) parent);
		

		return view;
	}

}
