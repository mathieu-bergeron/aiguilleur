package ca.aiguilleur.frontend.views;

import java.net.URL;
import java.util.ResourceBundle;

import ca.aiguilleur.frontend.events.EvtShowGameView;
import ca.aiguilleur.frontend.queue.QueueView;
import ca.ntro.app.models.ModelUpdates;
import ca.ntro.core.initialization.Ntro;
import ca.ntro.core.path.ValuePath;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class QueueViewFx 

       implements QueueView<VBox>, Initializable {
	
	@FXML
	private VBox rootNode;
	
	@FXML
	private Button showGameViewButton;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Ntro.asserter().assertNotNull("rootNode", rootNode);
		Ntro.asserter().assertNotNull("showGameViewButton", showGameViewButton);
	}

	@Override
	public VBox rootNode() {
		return rootNode;
	}

	@Override
	public void displayModelUpdates(ModelUpdates updates) {

		updates.forEachUpdate((updateType, valueId, newValue) -> {
			
			ValuePath valuePath = valueId.getPath();

			if(valuePath.startsWith("appointements")) {
				
				//appointments.displayModelUpdate(updateType, valuePath.subPath(1), newValue);
			}
		});
	}

	@Override
	public void installShowGameViewEvent(EvtShowGameView event) {
		showGameViewButton.setOnAction(fxmlEvent -> {

			event.trigger();
		});
	}

}
