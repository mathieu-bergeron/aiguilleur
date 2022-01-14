package ca.ntro.core.object_diff.updates;

import ca.ntro.core.path.Path;

public class InsertNtro 

       extends ValueUpdateNtro 
       
       implements Insert {


	public InsertNtro(Path valuePath, Object value) {
		super(valuePath, value);
	}

	@Override
	public boolean isInsert() {
		return true;
	}

	@Override
	public boolean isModify() {
		return false;
	}

	@Override
	public boolean isDelete() {
		return false;
	}

}
