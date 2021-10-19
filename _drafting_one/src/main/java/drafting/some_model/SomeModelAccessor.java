package drafting.some_model;

import models.ModelWrapper;

public class SomeModelAccessor extends ModelWrapper<SomeModel>{

	public SomeModelAccessor(SomeModel model) {
		super(model);
	}

	// XXX: getter must be public ONLY in the accessor
	public int getSomeAttribute() {
		return model().getSomeAttribute();
	}
}
