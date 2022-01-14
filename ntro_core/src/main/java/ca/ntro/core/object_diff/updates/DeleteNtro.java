package ca.ntro.core.object_diff.updates;

import ca.ntro.core.path.Path;

public class DeleteNtro 

       extends ObjectUpdateNtro 
       
       implements Delete {

	public DeleteNtro(Path valuePath) {
		super(valuePath);
	}

	@Override
	public boolean isInsert() {
		return false;
	}

	@Override
	public boolean isModify() {
		return false;
	}

	@Override
	public boolean isDelete() {
		return true;
	}
}
