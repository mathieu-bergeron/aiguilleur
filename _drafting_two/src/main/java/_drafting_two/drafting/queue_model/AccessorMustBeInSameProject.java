package _drafting_two.drafting.queue_model;

import drafting.appointment_model.AppointmentModelAccessor;
import drafting.queue_model.QueueModel;
import models.ModelRegistrar;
import models.ModelWrapper;

public class AccessorMustBeInSameProject extends ModelWrapper<QueueModel> {

	public AccessorMustBeInSameProject(QueueModel model) {
		super(model);
	}

	public AppointmentModelAccessor getAppointment(int index) {
		//return ModelRegistrar.createAccessor(model().getAppointment(index));
		return null;
	}

}
