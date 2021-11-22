package ca.aiguilleur.frontend.widgets;

import ca.aiguilleur.models.AppointmentModel;
import ca.ntro.core.path.ValuePath;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class AppointmentWidget extends Pane {
	
	private Text studentName = new Text();

	public AppointmentWidget(AppointmentModel model) {

		studentName.setText(model.getStudentName());
	}

	public void updateValue(ValuePath valuePath, Object newValue) {
		
		if(valuePath.startsWith("studentName")) {
			studentName.setText((String) newValue);
		}

	}

}
