package ca.aiguilleur.frontend.views;

import java.net.URL;
import java.util.ResourceBundle;

import ca.aiguilleur.frontend.queue.QueueView;
import ca.aiguilleur.frontend.widgets.AppointmentList;
import ca.ntro.app.models.ModelUpdates;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class QueueViewFx implements QueueView, Initializable {
	
	@FXML
	AppointmentList appointments;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	@Override
	public void displayModelUpdates(ModelUpdates updates) {

		updates.forEachUpdate((updateType, valuePath, newValue) -> {

			if(valuePath.startsWith("appointements")) {
				
				appointments.displayModelUpdate(updateType, valuePath.subPath(1), newValue);
			}
		});
	}
}
