package ca.aiguilleur.frontend.views;

import java.net.URL;
import java.util.ResourceBundle;

import ca.ntro.app.views.RootViewFx;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class AiguilleurRootView implements RootViewFx, Initializable {
	
	@FXML
	Pane menuContainer;

	@FXML
	Pane pageContainer;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		pageContainer.getChildren().add(new Button("Hello world!"));
	}
}

