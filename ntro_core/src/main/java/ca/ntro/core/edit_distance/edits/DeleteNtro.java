package ca.ntro.core.edit_distance.edits;

public class DeleteNtro 
       
       extends EditNtro

       implements Delete {
	


	public DeleteNtro(int index) {
		super(index);
	}

	@Override
	public boolean isInsert() {
		return false;
	}

	@Override
	public boolean isUpdate() {
		return false;
	}

	@Override
	public boolean isDelete() {
		return true;
	}
	

}
