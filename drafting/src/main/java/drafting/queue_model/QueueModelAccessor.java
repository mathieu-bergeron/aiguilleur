package drafting.queue_model;

import drafting.appointment_model.AppointmentModelAccessor;
import models.ModelRegistrar;

public class QueueModelAccessor extends QueueModel {

	// XXX: as getAppointment is an accessor
	//      it returns the accessor subclass of AppointmentModel
	//      which we need to create from a copy of the actual appointment object
	
	private QueueModel model;
	
	public QueueModelAccessor(QueueModel model) {
		this.model = model;
	}

	@Override
	public AppointmentModelAccessor getAppointment(int index) {
		return ModelRegistrar.createAccessor(model.getAppointment(index));
	}

}
