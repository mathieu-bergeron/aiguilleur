package ca.ntro.app.frontend;


import java.io.IOException;
import java.net.URL;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class ViewLoaderFx<V extends View<?>> implements ViewLoader<V> {
	
	private String fxmlPath;
	private String cssPath;
	private String resourcesPath;

	private FXMLLoader loader;

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
	

	public void createFxmlLoader() {
		
		URL fxmlUrl = urlFromPath(getFxmlPath());
		ResourceBundle resources = loadResourceBundle();
		
		if(resources != null) {

			loader = new FXMLLoader(fxmlUrl, resources);
			
		}else {

			loader = new FXMLLoader(fxmlUrl);
		}
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
		
		Parent parent = null;
		try {

			parent = loader.load();

		} catch (IOException e) {
			throw new RuntimeException(e);
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

		return view;
	}

}
