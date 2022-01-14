package ca.ntro.core.edit_distance.edits;

public class ModifyNtro 

       extends EditValueNtro

       implements Modify {
	
	
	

	public ModifyNtro(int index, Object value) {
		super(index, value);
	}

	@Override
	public boolean isInsert() {
		return false;
	}

	@Override
	public boolean isModify() {
		return true;
	}

	@Override
	public boolean isDelete() {
		return false;
	}

}
