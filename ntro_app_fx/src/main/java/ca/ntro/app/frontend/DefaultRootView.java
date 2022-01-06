package ca.ntro.app.frontend;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class DefaultRootView extends VBox {
	
	public DefaultRootView() {
		super();
		
		HBox hbox = new HBox();
		hbox.setAlignment(Pos.CENTER);
		hbox.getChildren().add(new Label("[NtroAppFx default view]"));

		getChildren().add(hbox);
		
		VBox.setVgrow(hbox, Priority.ALWAYS);
	}

}
