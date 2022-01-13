package ca.ntro.app.views;

import ca.ntro.app.frontend.View;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;

public abstract class ViewFx implements View<Pane>, Initializable {
	
	private Pane pane;

	void setPane(Pane pane) {
		this.pane = pane;
	}

	@Override
	public Pane rootNode() {
		return pane;
	}

}
