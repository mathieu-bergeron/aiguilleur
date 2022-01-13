package ca.ntro.core.edit_distance;

import ca.ntro.core.edit_distance.edits.Edit;

public class EditDistance {
	
	private Edit edit;
	private int distance = 0;

	public Edit getEdit() {
		return edit;
	}

	public void setEdit(Edit edit) {
		this.edit = edit;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}
}
