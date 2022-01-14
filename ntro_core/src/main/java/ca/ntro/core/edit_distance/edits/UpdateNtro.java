package ca.ntro.core.edit_distance.edits;

public class UpdateNtro 

       extends EditValueNtro

       implements Update {
	
	
	

	public UpdateNtro(int index, Object value) {
		super(index, value);
	}

	@Override
	public boolean isInsert() {
		return false;
	}

	@Override
	public boolean isUpdate() {
		return true;
	}

	@Override
	public boolean isDelete() {
		return false;
	}

}