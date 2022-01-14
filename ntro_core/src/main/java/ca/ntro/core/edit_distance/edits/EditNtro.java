package ca.ntro.core.edit_distance.edits;

public abstract class EditNtro 

       implements     Edit {

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

	@Override
	public Insert asInsert() {
		return (Insert) this;
	}

	@Override
	public Update asUpdate() {
		return (Update) this;
	}

	@Override
	public Delete asDelete() {
		return (Delete) this;
	}
}
