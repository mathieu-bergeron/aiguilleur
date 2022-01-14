package ca.ntro.core.edit_distance.edits;

public interface Edit {
	
	boolean isInsert();
	Insert  asInsert();
	
	boolean isModify();
	Modify  asModify();

	boolean isDelete();
	Delete  asDelete();

	int index();

}
