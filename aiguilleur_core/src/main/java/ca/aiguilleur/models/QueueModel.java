package ca.aiguilleur.models;

import java.util.ArrayList;
import java.util.List;

import ca.ntro.core.identifyers.ModelId;

public class QueueModel {

	public static ModelId id() {
		return null;
	}

	private List<AppointmentModel> appointments = new ArrayList<>();

	public List<AppointmentModel> getAppointments() {
		return appointments;
	}

	public void setAppointments(List<AppointmentModel> appointments) {
		this.appointments = appointments;
	}
}
