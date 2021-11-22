package ca.aiguilleur.models;

import ca.ntro.app.models.Model;

public class AppointmentModel implements Model {
	
	private String studentName;

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
}
