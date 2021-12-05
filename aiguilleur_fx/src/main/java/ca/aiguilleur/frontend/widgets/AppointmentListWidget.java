package ca.aiguilleur.frontend.widgets;

import ca.aiguilleur.models.AppointmentModel;
import ca.ntro.app.models.UpdateType;
import ca.ntro.core.path.ValuePath;
import javafx.scene.layout.Pane;

public class AppointmentListWidget extends Pane {

	public void displayModelUpdate(UpdateType updateType, ValuePath valuePath, Object newValue) {

		int index = Integer.valueOf(valuePath.name(0));
		
		if(updateType == UpdateType.SET) {
			
			AppointmentWidget appointment = (AppointmentWidget) getChildren().get(index);
			
			appointment.updateValue(valuePath.subPath(1), newValue);

		}else if(updateType == UpdateType.DELETE) {
			
			getChildren().remove(index);

		}else if(updateType == UpdateType.INSERT){
			
			AppointmentWidget appointment = new AppointmentWidget((AppointmentModel) newValue);
			
			getChildren().add(index, appointment);
		}
	}

}
