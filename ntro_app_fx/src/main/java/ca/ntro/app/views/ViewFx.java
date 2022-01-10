package ca.ntro.app.views;

import ca.ntro.app.frontend.View;
import javafx.scene.Parent;

public class ViewFx implements View {
	
	private Parent rootNode;

	public Parent rootNode() {
		return rootNode;
	}

	public void setRootNode(Parent rootNode) {
		this.rootNode = rootNode;
	}

}
