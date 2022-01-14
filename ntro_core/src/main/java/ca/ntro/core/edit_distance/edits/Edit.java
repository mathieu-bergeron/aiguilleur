package ca.ntro.core.edit_distance.edits;

public interface Edit {
	
	boolean isInsert();
	Insert  asInsert();
	
	boolean isUpdate();
	Update  asUpdate();

	boolean isDelete();
	Delete  asDelete();

	int index();

}
