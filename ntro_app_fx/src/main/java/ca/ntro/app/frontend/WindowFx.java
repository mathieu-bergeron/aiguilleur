package ca.ntro.app.frontend;

import ca.ntro.app.views.ViewFx;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class WindowFx implements Window {
	
	private Stage primaryStage;
	private Parent parent = (Parent) new DefaultRootView();

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
		Scene rootScene = new Scene(getParent(), width, height);
		getPrimaryStage().setScene(rootScene);
	}

	@Override
	public void installRootView(View view) {
		ViewFx viewFx = (ViewFx) view;
		
		if(viewFx.getParent() != null) {

			setParent(viewFx.getParent());

			Scene rootScene = new Scene(getParent(), primaryStage.getWidth(), primaryStage.getHeight());

			getPrimaryStage().setScene(rootScene);
			
		}else {
			
			throw new RuntimeException("viewFx.getParent() is null");
			
		}

	}

	@Override
	public void show() {
		getPrimaryStage().show();
	}

}