package ca.ntro.app.frontend;

import ca.ntro.app.services.Window;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class WindowFx implements Window {
	
	private Stage primaryStage;
	private Parent parent = (Parent) new DefaultRootView();
	
	private double width = 600;
	private double height = 400;

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	public Parent getParent() {
		return parent;
	}

	public void setParent(Parent parent) {
		this.parent = parent;
	}
	
	
	
	

	public WindowFx() {
	}

	public WindowFx(Stage primaryStage) {
		setPrimaryStage(primaryStage);
	}
	
	

	@Override
	public void resize(int width, int height) {

		Scene existingScene = getPrimaryStage().getScene();
		if(existingScene != null) {
			
			getPrimaryStage().setWidth(width);
			getPrimaryStage().setHeight(height);

			this.width = width;
			this.height = height;

			
		}else {

			setRootScene(width, height);
			
			this.width = getPrimaryStage().getWidth();
			this.height = getPrimaryStage().getHeight();
		}
	}

	private void setRootScene(double width, double height) {

		Scene rootScene = new Scene(getParent(), width, height);
		getPrimaryStage().setScene(rootScene);
	}
	

	@Override
	public void registerRootView(View<?> rootView) {
		if(rootView.rootNode() != null) {

			setParent((Parent) rootView.rootNode());
			
			double sceneWidth = width;
			double sceneHeight = height;
			
			Scene existingScene = getPrimaryStage().getScene();
			if(existingScene != null) {
				this.width = getPrimaryStage().getWidth();
				this.height = getPrimaryStage().getHeight();
				
				sceneWidth = existingScene.getWidth();
				sceneHeight = existingScene.getHeight();
			}

			setRootScene(sceneWidth, sceneHeight);

		}else {
			
			throw new RuntimeException("[WindowFx.installRootView] rootView.rootNode() is null");
		}

	}

	@Override
	public void show() {
		getPrimaryStage().setWidth(width);
		getPrimaryStage().setHeight(height);
		getPrimaryStage().show();
		getPrimaryStage().setIconified(false);
	}

	@Override
	public void fullscreen(boolean isFullScreen) {
		getPrimaryStage().setFullScreen(true);
	}

}
