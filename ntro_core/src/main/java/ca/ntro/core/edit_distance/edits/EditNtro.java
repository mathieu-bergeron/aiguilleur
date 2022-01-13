package ca.ntro.core.edit_distance.edits;

public class EditNtro implements Edit {

	private int index;

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	
	public EditNtro(int index) {
		setIndex(index);
	}
	
	

	@Override
	public int index() {
		return getIndex();
	}

}
