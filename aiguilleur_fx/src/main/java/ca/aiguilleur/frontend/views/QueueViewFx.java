package ca.aiguilleur.frontend.views;

import java.net.URL;
import java.util.ResourceBundle;

import ca.aiguilleur.frontend.queue.QueueView;
import ca.ntro.app.models.ModelUpdates;
import javafx.fxml.Initializable;

public class QueueViewFx implements QueueView, Initializable {

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	@Override
	public void displayModelUpdates(ModelUpdates updates) {

		updates.forEachUpdate((updateType, valuePath, newValue) -> {
			
			
		});
	}
}
