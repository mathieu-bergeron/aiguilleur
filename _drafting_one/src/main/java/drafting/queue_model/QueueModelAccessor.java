package drafting.queue_model;

import drafting.appointment_model.AppointmentModelAccessor;
import models.ModelRegistrar;
import models.ModelWrapper;

public class QueueModelAccessor extends ModelWrapper<QueueModel> {

	public QueueModelAccessor(QueueModel model) {
		super(model);
	}

	// XXX: as getAppointment is an accessor
	//      it returns the accessor subclass of AppointmentModel
	//      which we need to create from a copy of the actual appointment object

	public AppointmentModelAccessor getAppointment(int index) {
		return ModelRegistrar.createAccessor(model().getAppointment(index));
	}

}
