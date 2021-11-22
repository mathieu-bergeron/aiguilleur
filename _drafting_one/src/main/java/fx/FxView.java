package fx;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class FxView implements Initializable {
	
	@FXML
	Pane menuContainer;

	@FXML
	Pane pageContainer;

	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		pageContainer.getChildren().add(new Button("Hello"));
		
	}

}
