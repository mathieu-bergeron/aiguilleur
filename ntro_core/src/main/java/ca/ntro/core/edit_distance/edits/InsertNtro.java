package ca.ntro.core.edit_distance.edits;

public class InsertNtro 

       extends EditValueNtro

       implements Insert {
	
	

	public InsertNtro(int index, Object value) {
		super(index, value);
	}

	@Override
	public boolean isInsert() {
		return true;
	}

	@Override
	public boolean isUpdate() {
		return false;
	}

	@Override
	public boolean isDelete() {
		return false;
	}

}
