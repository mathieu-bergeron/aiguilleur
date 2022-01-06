package ca.ntro.app.views;

import ca.ntro.app.frontend.View;
import javafx.scene.Parent;

public class ViewFx implements View {
	
	private Parent parent;

	public Parent getParent() {
		return parent;
	}

	public void setParent(Parent parent) {
		this.parent = parent;
	}

}
