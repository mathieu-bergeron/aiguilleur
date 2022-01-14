package ca.ntro.core.object_diff.updates;

import ca.ntro.core.path.Path;

public class ModifyNtro 

       extends ValueUpdateNtro 
       
       implements Modify {


	public ModifyNtro(Path valuePath, Object value) {
		super(valuePath, value);
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
