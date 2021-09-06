package drafting.queue_model;

import drafting.appointment_model.AppointmentModel;
import drafting.collections.NtroList;
import models.Accessor;

public class QueueModel {
	
	private NtroList<AppointmentModel> appointments = new NtroList<>();
	
	// TODO: in the Accessor, that HAS TO return 
	//       an accessor to a copy of the appointment
	@Accessor
	protected AppointmentModel getAppointment(int index) {
		return appointments.get(index);
	}
}
