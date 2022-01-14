package ca.ntro.core.edit_distance;

public class EditDistance {
	
	private int i;
	private int j;
	private EditType type;
	private int distance = 0;

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

	public int getJ() {
		return j;
	}

	public void setJ(int j) {
		this.j = j;
	}

	public EditType getType() {
		return type;
	}

	public void setType(EditType type) {
		this.type = type;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}
}
