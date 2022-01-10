package ca.ntro.app.frontend;


import java.io.IOException;
import java.net.URL;

import ca.ntro.app.views.ViewFx;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class ViewLoaderFx<V extends ViewFx> implements ViewLoader<V> {
	
	private URL xmlFile;
	
	public ViewLoaderFx(String fxmlPath) {

		xmlFile = ViewLoaderFx.class.getResource(fxmlPath);
		
		if(xmlFile == null) {
			throw new RuntimeException("Not found " + fxmlPath);
		}
		
	}

	@Override
	public V createView() {

		FXMLLoader loader = new FXMLLoader(xmlFile);
		
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
		
		view.setRootNode(parent);

		return view;
	}
}
