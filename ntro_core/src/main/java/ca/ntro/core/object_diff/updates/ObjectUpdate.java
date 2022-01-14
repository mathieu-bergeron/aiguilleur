package ca.ntro.core.object_diff.updates;

import ca.ntro.core.path.Path;

public interface ObjectUpdate {

	boolean isInsert();
	Insert  asInsert();
	
	boolean isModify();
	Modify  asModify();

	boolean isDelete();
	Delete  asDelete();
	
	Path valuePath();

}
