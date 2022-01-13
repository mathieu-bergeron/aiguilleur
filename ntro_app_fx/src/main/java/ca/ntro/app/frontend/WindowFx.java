package ca.ntro.app.frontend;

import ca.ntro.app.services.Window;
import ca.ntro.app.views.ViewFx;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class WindowFx implements Window {
	
	private Stage primaryStage;
	private Parent parent = (Parent) new DefaultRootView();
	
	private boolean windowShown = false;

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
		if(!windowShown) {
			Scene rootScene = new Scene(getParent(), width, height);
			getPrimaryStage().setScene(rootScene);
		}
	}

	@Override
	public void installRootView(View<?> view) {
		
		if(view.rootNode() != null) {

			setParent((Parent) view.rootNode());

			Scene rootScene = new Scene(getParent(), primaryStage.getWidth(), primaryStage.getHeight());

			getPrimaryStage().setScene(rootScene);
			
		}else {
			
			throw new RuntimeException("[WindowFx.installRootView] view.rootNode() is null");
			
		}

	}

	@Override
	public void show() {
		if(!windowShown) {
			getPrimaryStage().show();
			windowShown = true;
		}
	}

	@Override
	public void fullscreen(boolean isFullScreen) {
		getPrimaryStage().setFullScreen(true);
	}

}
